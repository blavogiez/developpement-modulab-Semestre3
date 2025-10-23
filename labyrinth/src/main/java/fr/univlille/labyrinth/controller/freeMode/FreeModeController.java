package fr.univlille.labyrinth.controller.freemode;

import fr.univlille.labyrinth.Main;
import fr.univlille.labyrinth.utils.ResizeUtil;
import fr.univlille.labyrinth.model.gamemode.FreeMode;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Controller du menu mode libre
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class FreeModeController {
    @FXML
    private Button bouttonValider;

    @FXML
    private Button bouttonQuitter;

    @FXML
    private Label l;

    @FXML
    private TextField heightField;

    @FXML
    private TextField widthField;

    @FXML
    private Slider wallPercentageSlider;

    @FXML
    private VBox menu;

    // mettre par défaut les valeurs des champs aux dernieres valeurs du mode libre (reprise)
    @FXML
    public void initialize() {
        heightField.setText("" + FreeMode.mazeHeight);
        widthField.setText("" + FreeMode.mazeWidth);
        wallPercentageSlider.setValue(FreeMode.mazeWallPercentage);
        menu.widthProperty().addListener((o, oldW, newW) -> ResizeUtil.redimensionnerVboxControles(menu));
        menu.heightProperty().addListener((o, oldH, newH) -> ResizeUtil.redimensionnerVboxControles(menu));

    }

    /**
     * @throws IOException Renvoie une IOException si la scène est inaccessible.
     */
    @FXML
    private void goToModeLaby() throws IOException {
        try {
            FreeMode.mazeHeight = Integer.parseInt(heightField.getText());
        } catch (NumberFormatException e) {
            FreeMode.mazeHeight = 20;
        }
        try {
            FreeMode.mazeWidth = Integer.parseInt(widthField.getText());
        } catch (NumberFormatException e) {
            FreeMode.mazeWidth = 20;
        }
        FreeMode.mazeWallPercentage = wallPercentageSlider.getValue();
        Main.goTo("freemode/FreeModeLabyrinth.fxml");
    }

    /**
     * @throws IOException Renvoie une IOException si la scène est inaccessible.
     */
    @FXML
    private void goToAccueil() throws IOException {
        Main.goTo("GameModeSelection.fxml");
    }
}
