package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.Main;
import fr.univlille.labyrinth.model.PlayerDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ProgressionEntreNomController {
    public static String playerName = "toto";

    @FXML
    private TextField nameField;

    @FXML
    private void goToProgression() throws IOException {
        String name = nameField.getText();
        if (name != null && !name.trim().isEmpty()) {
            playerName = name.trim();
        }
        if (PlayerDatabase.playerExists(playerName)) {
            Main.goTo("ProfilExistant.fxml");
        } else {
            Main.goTo("Progression.fxml");
        }
    }

    @FXML
    private void goToAccueil() throws IOException {
        Main.goTo("AccueilLabyrinth.fxml");
    }
}
