package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ModeLibreController {
    @FXML
    private Button bouttonValider;

    @FXML
    private Button bouttonQuitter;

    @FXML
    private Label l;

    @FXML
    private void goToModeLaby() throws IOException {
        HelloApplication.goTo("ModeLibre.fxml");
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
