package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.model.* ;
import fr.univlille.labyrinth.view.LabyrinthScene;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class LabyrinthModeLibreController {

    @FXML
    private BorderPane pane1;

    //private final GameMode gameMode = new GameMode();

    @FXML
    public void initialize() {
        FreeMode gameMode = new FreeMode();
        gameMode.start();

        LabyrinthScene labyrinth = new LabyrinthScene(gameMode.getCurrentMaze());
        gameMode.getCurrentMaze().add(labyrinth);
        labyrinth.setControler(new LabyrinthControler(gameMode));

        labyrinth.update(gameMode.getCurrentMaze());
        pane1.setCenter(labyrinth.getCompletePane());
        pane1.requestFocus();

        labyrinth.getControler().movePlayer(Direction.RIGHT);
        labyrinth.getControler().movePlayer(Direction.DOWN);
        labyrinth.getControler().movePlayer(Direction.UP);
        labyrinth.getControler().movePlayer(Direction.RIGHT);

    }

    @FXML
    public void movement(KeyEvent e){
        System.out.println(e);
//if (e.getCode().equals(KeyCode.DOWN)) controler.movePlayer(Direction.DOWN);
//        else if (x.getCode().equals(KeyCode.UP)) controler.movePlayer(Direction.UP);
//        else if (x.getCode().equals(KeyCode.LEFT)) controler.movePlayer(Direction.LEFT);
//        else if (x.getCode().equals(KeyCode.RIGHT)) controler.movePlayer(Direction.RIGHT);
    }
}
