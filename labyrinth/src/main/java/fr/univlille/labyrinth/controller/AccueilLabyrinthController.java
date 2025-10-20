package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AccueilLabyrinthController  {
    @FXML
    private Button bouttonModeLibre;
    @FXML
    private Button bouttonModeProgression;
    @FXML
    private Button bouttonQuitter;

    /** 
     * @throws IOException
     */
    @FXML
    private void goToModeLibre() throws IOException {
        Main.goTo("ModeLibre.fxml");
    }

    /** 
     * @throws IOException
     */
    @FXML
    private void goToModeProgression() throws IOException {
        Main.goTo("ProgressionEntreNom.fxml");
    }

    /** 
     * @throws IOException
     */
    @FXML
    private void goToQuitter() throws IOException {
        Main.goTo("PortailLabyrinth.fxml");
    }
}