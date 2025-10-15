package fr.univlille.labyrinth.controller;

import java.io.IOException;

import fr.univlille.labyrinth.HelloApplication;
import fr.univlille.labyrinth.model.FreeMode;
import fr.univlille.labyrinth.view.LabyrinthScene;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ModeLibreController {
    private FreeMode gameMode ;

    @FXML
    private Button bouttonValider;

    @FXML
    private Button bouttonQuitter;

    @FXML
    private Label l;

    @FXML
    private void goToModeLaby() throws IOException {
        gameMode = new FreeMode() ;
        gameMode.start();

        System.out.println(gameMode.getCurrentMaze());
        // HelloApplication.goTo("ModeLibre.fxml");
        LabyrinthScene labyrinthScene = new LabyrinthScene(gameMode.getCurrentMaze()) ;
        gameMode.getCurrentMaze().add(labyrinthScene);
        labyrinthScene.setControler(new LabyrinthControler(gameMode));

        HelloApplication.goTo(labyrinthScene);
        labyrinthScene.update(gameMode.getCurrentMaze());
    }

    @FXML
    private void goToAccueil() throws IOException {
        HelloApplication.goTo("AccueilLabyrinth.fxml");
    }

    @FXML
    private void test(){
        l.setText("sdsfdfd");
    }
}
