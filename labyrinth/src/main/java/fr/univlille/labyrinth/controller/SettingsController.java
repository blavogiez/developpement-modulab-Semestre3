package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.Main;
import javafx.fxml.FXML;

import java.io.IOException;

import static fr.univlille.labyrinth.Main.getTheme;


/**
 * Controller des paramètres
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class SettingsController {


    /**
     * Change de thème si le button est cliqué.
     */
    @FXML
    private void toggleButton(){
        Main.setDarkMode(!Main.getDarkMode());
        Main.getPrimaryStage().getScene().getStylesheets().set(0,Main.class.getResource(getTheme()).toExternalForm());
    }

    /**
     * @throws IOException Renvoie une IOException si la scène est inaccessible.
     */
    @FXML
    private void goToAccueil() throws IOException {
        Main.goTo("HomeMenu.fxml");
    }
}
