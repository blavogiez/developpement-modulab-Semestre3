package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.model.* ;
import fr.univlille.labyrinth.view.LabyrinthGridView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

// Controller for the FreeMode maze interface
// FXML base that contains a GridPane (LabyrinthGridView) which observes the Maze
public class LabyrinthModeLibreController {

    @FXML
    private BorderPane pane1;

    //private final GameMode gameMode = new GameMode();

    private LabyrinthGridView labyrinth;

    @FXML
    public void initialize() {
        FreeMode gameMode = new FreeMode();
        gameMode.start();

        labyrinth = new LabyrinthGridView(gameMode.getCurrentMaze());
        gameMode.getCurrentMaze().add(labyrinth);
        labyrinth.setControler(new LabyrinthControler(gameMode));

        labyrinth.update(gameMode.getCurrentMaze());

        pane1.setCenter(labyrinth.getGridPane());

        pane1.requestFocus();

        labyrinth.update(gameMode.getCurrentMaze());

    }

    @FXML
    public void movement(KeyEvent e){
        System.out.println(e.getCode());
        if (e.getCode().equals(KeyCode.S)) labyrinth.getControler().movePlayer(Direction.DOWN);
        else if (e.getCode().equals(KeyCode.Z)) labyrinth.getControler().movePlayer(Direction.UP);
        else if (e.getCode().equals(KeyCode.Q)) labyrinth.getControler().movePlayer(Direction.LEFT);
        else if (e.getCode().equals(KeyCode.D)) labyrinth.getControler().movePlayer(Direction.RIGHT);
    }

}
