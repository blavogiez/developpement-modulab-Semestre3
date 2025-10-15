package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.HelloApplication;
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
        HelloApplication.goTo("Progression.fxml");
    }



    @FXML
    private void goToProgression() throws IOException { //reprendre partie
        HelloApplication.goTo("Progression.fxml");
    }

    @FXML
    private void goToProgressionEntreNom() throws IOException {
        HelloApplication.goTo("ProgressionEntreNom.fxml");
    }
}
