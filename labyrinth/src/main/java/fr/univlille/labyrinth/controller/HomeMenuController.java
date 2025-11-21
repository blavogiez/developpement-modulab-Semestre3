package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.App;
import fr.univlille.labyrinth.view.utils.ResizeUtil;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
    private VBox menuBoutons;
    @FXML
    private HBox menuLogo;
    @FXML
    private BorderPane homePane;
    @FXML
    private ImageView logo;


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
        homePane.widthProperty().addListener((o, oldW, newW) -> ResizeUtil.resizePaneInPane(homePane, menuLogo,homePane.getWidth(),homePane.getHeight()*0.4,0,0,0,0));
        homePane.heightProperty().addListener((o, oldH, newH) -> ResizeUtil.resizePaneInPane(homePane, menuLogo,homePane.getWidth(),homePane.getHeight()*0.4,0,0,0,0));

        menuBoutons.widthProperty().addListener((o, oldW, newW) -> ResizeUtil.resizeControlsToParentSize(menuBoutons));
        menuBoutons.heightProperty().addListener((o, oldH, newH) -> ResizeUtil.resizeControlsToParentSize(menuBoutons));
        menuLogo.widthProperty().addListener((o, oldW, newW) -> ResizeUtil.resizeImageViewToParentSize(menuLogo,logo,1,1));
        menuLogo.heightProperty().addListener((o, oldH, newH) -> ResizeUtil.resizeImageViewToParentSize(menuLogo,logo,1,1));
    }
}
