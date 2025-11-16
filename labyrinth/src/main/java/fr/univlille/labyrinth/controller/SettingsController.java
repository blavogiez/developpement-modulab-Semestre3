package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.App;
import fr.univlille.labyrinth.app.SceneNavigator;
import fr.univlille.labyrinth.app.Settings;
import fr.univlille.labyrinth.app.SettingsManager;
import fr.univlille.labyrinth.app.ThemeManager;
import fr.univlille.labyrinth.utils.ResizeUtil;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.io.IOException;
/**
 * Controller des paramètres
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class SettingsController {

    @FXML
    private VBox menu;

    /**
     * Change de thème si le button est cliqué.
     */
    @FXML
    private void toggleButton(){
        ThemeManager.setDarkMode(!ThemeManager.isDarkMode());
        ThemeManager.updateTheme(SceneNavigator.getPrimaryStage().getScene());
        SettingsManager.save();
    }

    @FXML
    private void toggleAnimation(){
        Settings settings = SettingsManager.get();
        settings.setAnimationEnabled(!settings.isAnimationEnabled());
        SettingsManager.save();
    }

    /**
     * @throws IOException Renvoie une IOException si la scène est inaccessible.
     */
    @FXML
    private void initialize(){
        resize();
    }

    /** 
     * @throws IOException
     */
    @FXML
    private void goToAccueil() throws IOException {
        App.goTo("HomeMenu.fxml");
    }

    private void resize(){
        menu.widthProperty().addListener((o, oldW, newW) -> ResizeUtil.resizeControlsToParentSize(menu));
        menu.heightProperty().addListener((o, oldH, newH) -> ResizeUtil.resizeControlsToParentSize(menu));
    }
}
