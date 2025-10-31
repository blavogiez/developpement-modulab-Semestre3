package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.App;

import fr.univlille.labyrinth.utils.ResizeUtil;
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
public class GameModeSelectionController  {

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
        menuBoutons.widthProperty().addListener((o, oldW, newW) -> ResizeUtil.resizeControlsInPane(menuBoutons));
        menuBoutons.heightProperty().addListener((o, oldH, newH) -> ResizeUtil.resizeControlsInPane(menuBoutons));
    }

    /**
     * @throws IOException Renvoie l'erreur émise si la scène est introuvable
     */
    @FXML
    private void goToModeLibre() throws IOException {
        App.goTo("freemode/FreeModeAlgorithmSelection.fxml");
    }

    /** 
     * @throws IOException Renvoie l'erreur émise si la scène est introuvable
     */
    @FXML
    private void goToModeProgression() throws IOException {
        App.goTo("progressionmode/PlayerNameEntry.fxml");
    }

    /** 
     * @throws IOException Renvoie l'erreur émise si la scène est introuvable
     */
    @FXML
    private void goToQuitter() throws IOException {
        App.goTo("HomeMenu.fxml");
    }
}