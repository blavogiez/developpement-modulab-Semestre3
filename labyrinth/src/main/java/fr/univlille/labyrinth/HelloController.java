package fr.univlille.labyrinth;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label dd;

    @FXML
    protected void onHelloButtonClick() {
        dd.setText("Welcome to JavaFX Application!");
    }
}
