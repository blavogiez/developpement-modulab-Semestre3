package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class ProfilExistant {
    private Button bouttonNouvellePartie;
    @FXML
    private Button bouttonReprendre;
    @FXML
    private Button bouttonRetour;

    @FXML
    private void goToNewProgression() throws IOException { //nouvelle partie
        Main.goTo("Progression.fxml");
    }



    @FXML
    private void goToProgression() throws IOException { //reprendre partie
        Main.goTo("Progression.fxml");
    }

    @FXML
    private void goToProgressionEntreNom() throws IOException {
        Main.goTo("ProgressionEntreNom.fxml");
    }
}
