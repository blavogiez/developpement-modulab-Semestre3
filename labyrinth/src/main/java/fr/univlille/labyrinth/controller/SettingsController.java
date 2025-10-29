package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.App;
import fr.univlille.labyrinth.app.SceneNavigator;
import fr.univlille.labyrinth.app.ThemeManager;
import javafx.fxml.FXML;

import java.io.IOException;
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
        ThemeManager.setDarkMode(!ThemeManager.isDarkMode());
        SceneNavigator.getPrimaryStage().getScene().getStylesheets().set(0, App.class.getResource(ThemeManager.getThemeCss()).toExternalForm());
    }

    /**
     * @throws IOException Renvoie une IOException si la scène est inaccessible.
     */
    @FXML
    private void goToAccueil() throws IOException {
        App.goTo("HomeMenu.fxml");
    }
}
