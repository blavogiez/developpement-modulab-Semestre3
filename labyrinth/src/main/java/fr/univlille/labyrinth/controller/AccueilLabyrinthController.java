package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.Main;

import fr.univlille.labyrinth.view.Redimension;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Controller du menu des choix du mode de jeu
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class AccueilLabyrinthController  {
    @FXML
    private Button bouttonModeLibre;
    @FXML
    private Button bouttonModeProgression;
    @FXML
    private Button bouttonQuitter;
    @FXML
    private VBox menuBoutons;



    @FXML
    public void initialize()
    {
        menuBoutons.widthProperty().addListener((o, oldW, newW) -> Redimension.redimensionnerVboxControles(menuBoutons));
        menuBoutons.heightProperty().addListener((o, oldH, newH) -> Redimension.redimensionnerVboxControles(menuBoutons));
    }

    /**
     * @throws IOException Renvoie l'erreur émise si la scène est introuvable
     */
    @FXML
    private void goToModeLibre() throws IOException {
        Main.goTo("ModeLibre.fxml");
    }

    /** 
     * @throws IOException Renvoie l'erreur émise si la scène est introuvable
     */
    @FXML
    private void goToModeProgression() throws IOException {
        Main.goTo("ProgressionEntreNom.fxml");
    }

    /** 
     * @throws IOException Renvoie l'erreur émise si la scène est introuvable
     */
    @FXML
    private void goToQuitter() throws IOException {
        Main.goTo("PortailLabyrinth.fxml");
    }
}