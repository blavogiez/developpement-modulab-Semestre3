package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.Main;
import fr.univlille.labyrinth.model.FreeMode;
import fr.univlille.labyrinth.utils.Redimension;
import fr.univlille.labyrinth.model.gamemode.FreeMode;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ModeLibreController {
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
        menu.widthProperty().addListener((o, oldW, newW) -> Redimension.redimensionnerVboxControles(menu));
        menu.heightProperty().addListener((o, oldH, newH) -> Redimension.redimensionnerVboxControles(menu));

    }

    /** 
     * @throws IOException
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
        Main.goTo("LabyrinthModeLibre.fxml");
    }

    /** 
     * @throws IOException
     */
    @FXML
    private void goToAccueil() throws IOException {
        Main.goTo("AccueilLabyrinth.fxml");
    }
}
