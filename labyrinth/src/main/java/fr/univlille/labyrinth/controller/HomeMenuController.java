package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.Main;
import fr.univlille.labyrinth.utils.ResizeUtil;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class HomeMenuController {
    @FXML
    private Button bouttonJouer;
    @FXML
    private Button bouttonQuitter;
    @FXML
    private Button bouttonParametres;
    @FXML
    private VBox menuBoutons;


    /** 
     * @throws IOException
     */

    @FXML
    public void initialize()
    {
        menuBoutons.widthProperty().addListener((o, oldW, newW) -> ResizeUtil.redimensionnerVboxControles(menuBoutons));
        menuBoutons.heightProperty().addListener((o, oldH, newH) -> ResizeUtil.redimensionnerVboxControles(menuBoutons));
    }
    @FXML
    private void goToJouer() throws IOException {
        Main.goTo("GameModeSelection.fxml");
    }

    @FXML
    private void goToQuitter(){
        Platform.exit();
    }

    /** 
     * @throws IOException
     */
    @FXML
    private void goToParametres() throws IOException {
        Main.goTo("Settings.fxml");
    }
}
