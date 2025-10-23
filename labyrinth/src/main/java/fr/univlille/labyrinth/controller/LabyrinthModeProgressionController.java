package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.Main;
import fr.univlille.labyrinth.model.*;
import fr.univlille.labyrinth.model.gamemode.ProgressionMode;
import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.save.Challenge;
import fr.univlille.labyrinth.model.save.Player;
import fr.univlille.labyrinth.parcours.BreadthFirstSearch;
import fr.univlille.labyrinth.utils.Chronometre;
import fr.univlille.labyrinth.utils.ChronometreFX;
import fr.univlille.labyrinth.view.LabyrinthGridView;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class LabyrinthModeProgressionController implements VictoryObserver {

    @FXML
    private BorderPane pane1;

    @FXML
    private Button bouttonRetour;

    @FXML
    private Label challengeInfoLabel;

    @FXML
    private Label mazeInfoLabel;

    @FXML
    private Label chronoLabel;

    private LabyrinthGridView labyrinth;

    private ProgressionMode gameMode;

    private Chronometre chrono;
    private Timeline chronoTimeline;    
    
    @FXML
    public void initialize() {
        Challenge selectedChallenge = AppState.getInstance().getSelectedChallenge();
        int selectedLevelIndex = AppState.getInstance().getSelectedLevelIndex();
        int selectedChallengeIndex = AppState.getInstance().getSelectedChallengeIndex();
        Player currentPlayer = AppState.getInstance().getCurrentPlayer();

        challengeInfoLabel.setText("Étape " + (selectedLevelIndex + 1) + ", Défi " + (selectedChallengeIndex + 1) + ", vue limitée");
        
        String info = "Dimensions : " + selectedChallenge.getWidth() + "*" + selectedChallenge.getHeight() ;
        info += ", Pourcentage : " + (int)(selectedChallenge.getWallPercentage() * 100) + "%" ;
        info += ", Distance entrée/sortie : " + selectedChallenge.getDistanceBetweenEntryAndExit();
        mazeInfoLabel.setText(info);

        gameMode = new ProgressionMode(currentPlayer, selectedChallenge);
        gameMode.createMaze(selectedChallenge);
        info += " (effective : " + BreadthFirstSearch.calculateDistance(gameMode.getCurrentMaze().getGrid(), gameMode.getCurrentMaze().getEntryPosition(), gameMode.getCurrentMaze().getExitPosition()) + ")" ;
        mazeInfoLabel.setText(info);

        labyrinth = new LabyrinthGridView(gameMode.getCurrentMaze());
        gameMode.getCurrentMaze().add(labyrinth);

        pane1.setCenter(labyrinth.getGrid());
        pane1.requestFocus();
        pane1.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            try {
                movement(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        labyrinth.update(gameMode.getCurrentMaze());
        chrono=new Chronometre();
        chrono.start();
        chronoTimeline = ChronometreFX.initChrono(chrono, chronoLabel);

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
    public void onVictory() {
        chrono.stop();
        if (chronoTimeline != null) chronoTimeline.stop();
        try {
            goToProgression();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void goToProgression() throws IOException {
        Main.goTo("Progression.fxml");
    }
}
