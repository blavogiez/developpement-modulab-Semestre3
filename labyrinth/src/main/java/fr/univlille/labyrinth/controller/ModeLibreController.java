package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.Main;
import fr.univlille.labyrinth.model.FreeMode;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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

    // mettre par défaut les valeurs des champs aux dernieres valeurs du mode libre (reprise)
    @FXML
    public void initialize() {
        heightField.setText("" + FreeMode.mazeHeight);
        widthField.setText("" + FreeMode.mazeWidth);
        wallPercentageField.setText("" + FreeMode.mazeWallPercentage);
    }

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
            FreeMode.mazeWallPercentage = Double.parseDouble(wallPercentageField.getText());
        } catch (NumberFormatException e) {
            FreeMode.mazeWallPercentage = 0.0;
        }
        Main.goTo("LabyrinthModeLibre.fxml");
    }

    @FXML
    private void goToAccueil() throws IOException {
        Main.goTo("AccueilLabyrinth.fxml");
    }
}
