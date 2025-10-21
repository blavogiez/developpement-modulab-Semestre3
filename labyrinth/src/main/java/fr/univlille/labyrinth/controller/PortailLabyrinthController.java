package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.Main;
import fr.univlille.labyrinth.view.Redimension;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class PortailLabyrinthController {
    @FXML
    private Button bouttonJouer;
    @FXML
    private Button bouttonQuitter;
    @FXML
    private Button bouttonParametres;

    @FXML
    private VBox MenuBoutons;


    /** 
     * @throws IOException
     */

    @FXML
    public void initialize()
    {
        MenuBoutons.widthProperty().addListener((o, oldW, newW) -> Redimension.redimensionnerBouton(MenuBoutons));
        MenuBoutons.heightProperty().addListener((o, oldH, newH) -> Redimension.redimensionnerBouton(MenuBoutons));
    }
    @FXML
    private void goToJouer() throws IOException {
        Main.goTo("AccueilLabyrinth.fxml");
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
        Main.goTo("Parametres.fxml");
    }
}
