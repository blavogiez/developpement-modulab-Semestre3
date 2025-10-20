package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class PortailLabyrinthController {
    @FXML
    private Button bouttonJouer;
    @FXML
    private Button bouttonQuitter;
    @FXML
    private Button bouttonParametres;


    /** 
     * @throws IOException
     */
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
    // a faire (normal que ca marche pas)
    @FXML
    private void goToParametres() throws IOException {
        Main.goTo("Parametres.fxml");
    }
}
