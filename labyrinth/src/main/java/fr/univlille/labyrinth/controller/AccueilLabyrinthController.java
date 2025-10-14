package fr.univlille.labyrinth.controller;


import fr.univlille.labyrinth.HelloApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AccueilLabyrinthController  {
    @FXML
    private Button bouttonModeLibre;
    @FXML
    private Button bouttonModeProgression;
    @FXML
    private Button bouttonQuitter;

    @FXML
    private void goToModeLibre() throws IOException {
        Stage stage = (Stage) HelloApplication.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/fr/univlille/labyrinth/ModeLibre.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void goToModeProgression() throws IOException {
        Stage stage = (Stage) HelloApplication.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/fr/univlille/labyrinth/ModeLibre.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}