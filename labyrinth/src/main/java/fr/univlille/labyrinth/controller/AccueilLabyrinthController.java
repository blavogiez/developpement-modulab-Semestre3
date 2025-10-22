package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.Main;

import fr.univlille.labyrinth.view.Redimension;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class AccueilLabyrinthController  {
    @FXML
    private Button bouttonModeLibre;
    @FXML
    private Button bouttonModeProgression;
    @FXML
    private Button bouttonQuitter;
    @FXML
    private VBox menuBoutons;

    /** 
     * @throws IOException
     */

    @FXML
    public void initialize()
    {
        menuBoutons.widthProperty().addListener((o, oldW, newW) -> Redimension.redimensionnerVboxControles(menuBoutons));
        menuBoutons.heightProperty().addListener((o, oldH, newH) -> Redimension.redimensionnerVboxControles(menuBoutons));
    }

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