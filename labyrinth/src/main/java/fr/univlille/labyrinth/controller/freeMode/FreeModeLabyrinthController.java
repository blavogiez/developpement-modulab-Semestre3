package fr.univlille.labyrinth.controller.freemode;

import java.io.IOException;

import fr.univlille.labyrinth.Main;
import fr.univlille.labyrinth.model.Observer;
import fr.univlille.labyrinth.model.gamemode.FreeMode;
import fr.univlille.labyrinth.model.gamemode.GameMode;
import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.parcours.BreadthFirstSearch;
import fr.univlille.labyrinth.utils.Timer;
import fr.univlille.labyrinth.utils.TimerFX;
import fr.univlille.labyrinth.view.LabyrinthGridView;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

//ça serait bien que les controller étende au moins un controller qui possèdent les méthodes en commun...
/**
 * Controller des labyrinthes spécifiquement du mode libre
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class FreeModeLabyrinthController implements Observer<GameMode> {
    @FXML
    private BorderPane pane1;

    @FXML
    private Button bouttonRetour;

    @FXML
    private Label chronoLabel;

    @FXML
    private Label mazeInfoLabel;

    private LabyrinthGridView labyrinth;
    private FreeMode gameMode;
    private Timer chrono;
    private Timeline chronoTimeline;

    /**
     * Initialise la scène
     */
    @FXML
    public void initialize() {
        gameMode = new FreeMode();
        gameMode.createMaze();

        String info = "Dimensions : " + FreeMode.mazeWidth + "*" + FreeMode.mazeHeight;
        info += ", Pourcentage : " + (int)(FreeMode.mazeWallPercentage * 100) + "%";
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
        chrono=new Timer();
        chrono.start();
        chronoTimeline = TimerFX.initChrono(chrono, chronoLabel);

        gameMode.addVictoryObserver(this);
    }

    /**
     * Déplace le joueur en fonction du bouton pressé
     */
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

    /**
     * Exécuté lorsque le joueur arrive sur la case d'arrivée.
     */
    @Override
    public void update(GameMode gameMode) {
        chrono.stop();
        if (chronoTimeline != null) chronoTimeline.stop();
        try {
            Main.goTo("freemode/FreeModeLabyrinth.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @throws IOException Renvoie une IOException si la scène est inaccessible.
     */
    @FXML
    private void goToAccueil() throws IOException {
        Main.goTo("GameModeSelection.fxml");
    }

}
