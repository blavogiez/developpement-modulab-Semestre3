package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class ProgressionController {
    @FXML
    private Button bouttonWorld1Challenge1;
    @FXML
    private Button bouttonWorld1Challenge2;
    @FXML
    private Button bouttonWorld1Challenge3;
    @FXML
    private Button bouttonWorld2Challenge1;
    @FXML
    private Button bouttonWorld2Challenge2;
    @FXML
    private Button bouttonWorld2Challenge3;
    @FXML
    private Button bouttonWorld3Challenge1;
    @FXML
    private Button bouttonWorld3Challenge2;
    @FXML
    private Button bouttonWorld3Challenge3;

    @FXML
    private void goToWorld1Challenge1() throws IOException {
        Main.goTo("LabyrinthModeLibre.fxml");
    }

    @FXML
    private void goToWorld1Challenge2() throws IOException {
        Main.goTo("LabyrinthModeLibre.fxml");
    }

    @FXML
    private void goToWorld1Challenge3() throws IOException {
        Main.goTo("LabyrinthModeLibre.fxml");
    }

    @FXML
    private void goToWorld2Challenge1() throws IOException {
        Main.goTo("LabyrinthModeLibre.fxml");
    }

    @FXML
    private void goToWorld2Challenge2() throws IOException {
        Main.goTo("LabyrinthModeLibre.fxml");
    }

    @FXML
    private void goToWorld2Challenge3() throws IOException {
        Main.goTo("LabyrinthModeLibre.fxml");
    }

    @FXML
    private void goToWorld3Challenge1() throws IOException {
        Main.goTo("LabyrinthModeLibre.fxml");
    }

    @FXML
    private void goToWorld3Challenge2() throws IOException {
        Main.goTo("LabyrinthModeLibre.fxml");
    }

    @FXML
    private void goToWorld3Challenge3() throws IOException {
        Main.goTo("LabyrinthModeLibre.fxml");
    }

    @FXML
    private void goToAccueil() throws IOException {
        Main.goTo("AccueilLabyrinth.fxml");
    }
}
