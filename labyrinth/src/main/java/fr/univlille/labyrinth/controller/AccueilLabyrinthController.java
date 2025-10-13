package fr.univlille.labyrinth.controller;


import fr.univlille.labyrinth.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AccueilLabyrinthController  {
    @FXML
    private Button b1;

    @FXML
    private void changerPage() throws IOException {
        Stage stage = (Stage) HelloApplication.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/fr/univlille/labyrinth/modeLibre.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}