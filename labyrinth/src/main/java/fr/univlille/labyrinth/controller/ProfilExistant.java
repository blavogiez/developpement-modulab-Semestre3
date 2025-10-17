package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.Main;
import fr.univlille.labyrinth.model.Player;
import fr.univlille.labyrinth.model.PlayerDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class ProfilExistant {
    @FXML
    private Button bouttonNouvellePartie;
    @FXML
    private Button bouttonReprendre;
    @FXML
    private Button bouttonRetour;

    @FXML
    private void goToNewProgression() throws IOException {
        String playerName = ProgressionEntreNomController.playerName;
        Player newPlayer = new Player(playerName);
        PlayerDatabase.savePlayer(newPlayer);
        Main.goTo("Progression.fxml");
    }



    @FXML
    private void goToProgression() throws IOException {
        Main.goTo("Progression.fxml");
    }

    @FXML
    private void goToProgressionEntreNom() throws IOException {
        Main.goTo("ProgressionEntreNom.fxml");
    }
}
