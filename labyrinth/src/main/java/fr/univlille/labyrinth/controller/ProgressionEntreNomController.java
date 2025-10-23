package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.Main;
import fr.univlille.labyrinth.model.PlayerDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ProgressionEntreNomController {
    // nom rentré par le joueur
    // + valeur par défaut si rien n'est entré
    public static String playerName = "toto";

    @FXML
    private TextField nameField;

    /** 
     * @throws IOException
     */
    @FXML
    private void goToProgression() throws IOException {
        // Collecte de la saisie de l'utilisateur + traitement
        String name = nameField.getText();
        if (name != null && !name.trim().isEmpty()) {
            playerName = name.trim();
        }
        if (PlayerDatabase.playerExists(playerName)) {
            Main.goTo("ProfilExistantController.fxml");
        } else {
            Main.goTo("Progression.fxml");
        }
    }

    /** 
     * @throws IOException
     */
    @FXML
    private void goToAccueil() throws IOException {
        Main.goTo("AccueilLabyrinth.fxml");
    }
}
