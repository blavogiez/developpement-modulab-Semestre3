package fr.univlille.labyrinth.controller.progressionmode;

import java.io.IOException;

import fr.univlille.labyrinth.Main;
import fr.univlille.labyrinth.controller.AppState;
import fr.univlille.labyrinth.model.Observer;
import fr.univlille.labyrinth.model.gamemode.GameMode;
import fr.univlille.labyrinth.model.gamemode.ProgressionMode;
import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.save.Challenge;
import fr.univlille.labyrinth.model.save.Player;
import fr.univlille.labyrinth.parcours.BreadthFirstSearch;
import fr.univlille.labyrinth.utils.Timer;
import fr.univlille.labyrinth.utils.TimerFX;
import fr.univlille.labyrinth.view.LabyrinthGridView;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

abstract class AbstractProgressionModeLabyrinthController implements Observer<GameMode> {

    @FXML
    protected BorderPane pane1;

    @FXML
    protected Button bouttonRetour;

    @FXML
    protected Label challengeInfoLabel;

    @FXML
    protected Label mazeInfoLabel;

    @FXML
    protected Label chronoLabel;

    protected ProgressionMode gameMode;
    protected Timer chrono;
    protected Timeline chronoTimeline;

    protected abstract Node setupViews(ProgressionMode gameMode);
    protected abstract String getViewSuffix();

    @FXML
    public void initialize() {
        Challenge selectedChallenge = AppState.getInstance().getSelectedChallenge();
        int selectedLevelIndex = AppState.getInstance().getSelectedLevelIndex();
        int selectedChallengeIndex = AppState.getInstance().getSelectedChallengeIndex();
        Player currentPlayer = AppState.getInstance().getCurrentPlayer();

        challengeInfoLabel.setText("Étape " + (selectedLevelIndex + 1) + ", Défi " + (selectedChallengeIndex + 1) + getViewSuffix());

        String info = "Dimensions : " + selectedChallenge.getWidth() + "*" + selectedChallenge.getHeight() ;
        info += ", Pourcentage : " + (int)(selectedChallenge.getWallPercentage() * 100) + "%" ;
        info += ", Distance entrée/sortie : " + selectedChallenge.getDistanceBetweenEntryAndExit();
        mazeInfoLabel.setText(info);

        gameMode = new ProgressionMode(currentPlayer, selectedChallenge);
        gameMode.createMaze(selectedChallenge);
        info += " (effective : " + BreadthFirstSearch.calculateDistance(gameMode.getCurrentMaze().getGrid(), gameMode.getCurrentMaze().getEntryPosition(), gameMode.getCurrentMaze().getExitPosition()) + ")" ;
        mazeInfoLabel.setText(info);

        pane1.setCenter(setupViews(gameMode));

        pane1.requestFocus();
        pane1.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            try {
                movement(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        chrono = new Timer();
        chrono.start();
        chronoTimeline = TimerFX.initChrono(chrono, chronoLabel);

        gameMode.setChronometre(chrono);
        gameMode.addVictoryObserver(this);
    }

    @FXML
    public void movement(KeyEvent e) throws IOException {
        Direction direction = null;
        KeyCode code = e.getCode();

        if (code == KeyCode.S || code == KeyCode.DOWN) direction = Direction.DOWN;
        else if (code == KeyCode.Z || code == KeyCode.UP) direction = Direction.UP;
        else if (code == KeyCode.Q || code == KeyCode.LEFT) direction = Direction.LEFT;
        else if (code == KeyCode.D || code == KeyCode.RIGHT) direction = Direction.RIGHT;

        if (direction != null) {
            gameMode.movePlayerPosition(direction);
            e.consume();
        }
    }

    @Override
    public void update(GameMode gameMode) {
        chrono.stop();
        if (chronoTimeline != null) chronoTimeline.stop();
        try {
            goToProgression();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToProgression() throws IOException {
        Main.goTo("progressionmode/LevelSelection.fxml");
    }
}

public class ProgressionModeLabyrinthController extends AbstractProgressionModeLabyrinthController {

    private LabyrinthGridView labyrinth;

    @Override
    protected Node setupViews(ProgressionMode gameMode) {
        labyrinth = new LabyrinthGridView(gameMode.getCurrentMaze());
        gameMode.getCurrentMaze().add(labyrinth);
        labyrinth.update(gameMode.getCurrentMaze());
        return labyrinth.getGrid();
    }

    @Override
    protected String getViewSuffix() {
        return "";
    }
}
