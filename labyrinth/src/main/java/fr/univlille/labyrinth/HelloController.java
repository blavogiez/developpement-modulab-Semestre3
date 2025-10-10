package fr.univlille.labyrinth;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.stage.Stage;

import java.io.IOException;


public class HelloController {
    @FXML
    private Button b1;

    @FXML
    private void changerPage() throws IOException {
        Stage stage = (Stage) b1.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("modeLibre.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
