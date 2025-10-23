package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.Main;
import fr.univlille.labyrinth.model.* ;
import fr.univlille.labyrinth.model.gamemode.FreeMode;
import fr.univlille.labyrinth.model.maze.Direction;
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

//ça serait bien que les controller étende au moins un controller qui possèdent les méthodes en commun...
/**
 * Controller des labyrinthes spécifiquement du mode libre
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class LabyrinthModeLibreController implements VictoryObserver {

    @FXML
    private BorderPane pane1;

    @FXML
    private Button bouttonRetour;

    @FXML
    private Label chronoLabel;

    private LabyrinthGridView labyrinth;
    private FreeMode gameMode;
    private Chronometre chrono;
    private Timeline chronoTimeline;

    /**
     * Initialise la scène
     */
    @FXML
    public void initialize() {
        gameMode = new FreeMode();
        gameMode.createMaze();

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
    public void onVictory() {
        chrono.stop();
        if (chronoTimeline != null) chronoTimeline.stop();
        try {
            Main.goTo("LabyrinthModeLibre.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @throws IOException Renvoie une IOException si la scène est inaccessible.
     */
    @FXML
    private void goToAccueil() throws IOException {
        Main.goTo("AccueilLabyrinth.fxml");
    }

}
