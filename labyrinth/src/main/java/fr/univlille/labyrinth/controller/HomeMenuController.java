package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.App;
import fr.univlille.labyrinth.utils.ResizeUtil;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Controller du menu principal
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
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
     * Initialisation de la scène
     */
    @FXML
    public void initialize()
    {
        resize();
    }

    /**
     * @throws IOException Renvoie une IOException si la scène est inaccessible.
     */
    @FXML
    private void goToJouer() throws IOException {
        App.goTo("GameModeSelection.fxml");
    }

    @FXML
    private void goToQuitter(){
        Platform.exit();
    }

    /**
     * @throws IOException Renvoie une IOException si la scène est inaccessible.
     */
    @FXML
    private void goToParametres() throws IOException {
        App.goTo("Settings.fxml");
    }

    public void resize(){
        menuBoutons.widthProperty().addListener((o, oldW, newW) -> ResizeUtil.resizeControlsInPane(menuBoutons));
        menuBoutons.heightProperty().addListener((o, oldH, newH) -> ResizeUtil.resizeControlsInPane(menuBoutons));
    }
}
