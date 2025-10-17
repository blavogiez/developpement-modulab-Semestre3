package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.Main;
import fr.univlille.labyrinth.model.* ;
import fr.univlille.labyrinth.view.LabyrinthGridView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    private LabyrinthGridView labyrinth;
    private FreeMode gameMode;

    @FXML
    public void initialize() {
        gameMode = new FreeMode();
        gameMode.start();

        labyrinth = new LabyrinthGridView(gameMode.getCurrentMaze());
        gameMode.getCurrentMaze().add(labyrinth);

        pane1.setCenter(labyrinth.getGrid());
        pane1.requestFocus();
        labyrinth.update(gameMode.getCurrentMaze());
    }

    @FXML
    public void movement(KeyEvent e) throws IOException {
        System.out.println(e.getCode());
        if (e.getCode().equals(KeyCode.S)) gameMode.movePlayerPosition(Direction.DOWN);
        else if (e.getCode().equals(KeyCode.Z)) gameMode.movePlayerPosition(Direction.UP);
        else if (e.getCode().equals(KeyCode.Q)) gameMode.movePlayerPosition(Direction.LEFT);
        else if (e.getCode().equals(KeyCode.D)) gameMode.movePlayerPosition(Direction.RIGHT);

        if (gameMode.isPlayerAtEnd()) {
            Main.goTo("LabyrinthModeLibre.fxml");
        }
    }

    @FXML
    private void goToAccueil() throws IOException {
        Main.goTo("AccueilLabyrinth.fxml");
    }

}
