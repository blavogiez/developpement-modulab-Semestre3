package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.Main;
import fr.univlille.labyrinth.model.* ;
import fr.univlille.labyrinth.utils.ChronoUtil;
import fr.univlille.labyrinth.view.LabyrinthGridView;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

// Controller for the FreeMode maze interface
// FXML base that contains a GridPane (LabyrinthGridView) which observes the Maze
public class LabyrinthModeLibreController {

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

    @FXML
    public void initialize() {
        gameMode = new FreeMode();
        gameMode.createMaze();

        labyrinth = new LabyrinthGridView(gameMode.getCurrentMaze());
        gameMode.getCurrentMaze().add(labyrinth);

        pane1.setCenter(labyrinth.getGrid());
        pane1.requestFocus();
        labyrinth.update(gameMode.getCurrentMaze());
        chrono=new Chronometre();
        chrono.start();
        chronoTimeline = ChronoUtil.initChrono(chrono, chronoLabel);
    }

    /** 
     * @param e
     * @throws IOException
     */
    @FXML
    public void movement(KeyEvent e) throws IOException {
        if (e.getCode().equals(KeyCode.S) || e.getCode().equals(KeyCode.DOWN)) gameMode.movePlayerPosition(Direction.DOWN);
        else if (e.getCode().equals(KeyCode.Z)|| e.getCode().equals(KeyCode.UP)) gameMode.movePlayerPosition(Direction.UP);
        else if (e.getCode().equals(KeyCode.Q)|| e.getCode().equals(KeyCode.RIGHT)) gameMode.movePlayerPosition(Direction.LEFT);
        else if (e.getCode().equals(KeyCode.D)|| e.getCode().equals(KeyCode.LEFT)) gameMode.movePlayerPosition(Direction.RIGHT);

        if (gameMode.isPlayerAtEnd()) {
            chrono.stop();
            if (chronoTimeline != null) chronoTimeline.stop();
            Main.goTo("LabyrinthModeLibre.fxml");
        }
    }

    /** 
     * @throws IOException
     */
    @FXML
    private void goToAccueil() throws IOException {
        Main.goTo("AccueilLabyrinth.fxml");
    }

}
