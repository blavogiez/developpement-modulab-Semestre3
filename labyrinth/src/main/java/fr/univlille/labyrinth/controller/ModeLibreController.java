package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.HelloApplication;

import fr.univlille.labyrinth.model.FreeMode;
import fr.univlille.labyrinth.model.Maze;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private TextField heightField;

    @FXML
    private TextField widthField;

    @FXML
    private TextField wallPercentageField;

    @FXML
    private void goToModeLaby() throws IOException {
        try {
            FreeMode.mazeHeight = Integer.parseInt(heightField.getText());
        } catch (NumberFormatException e) {
            FreeMode.mazeHeight = 20;
        }
        try {
            FreeMode.mazeWidth = Integer.parseInt(widthField.getText());
        } catch (NumberFormatException e) {
            FreeMode.mazeWidth = 20;
        }
        try {
            FreeMode.mazeWallPercentage = Integer.parseInt(wallPercentageField.getText());
        } catch (NumberFormatException e) {
            FreeMode.mazeWallPercentage = 80;
        }
        HelloApplication.goTo("LabyrinthModeLibre.fxml");
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
