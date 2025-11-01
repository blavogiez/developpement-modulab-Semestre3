package fr.univlille.labyrinth.controller.freemode;

import fr.univlille.labyrinth.App;
import fr.univlille.labyrinth.utils.ResizeUtil;
import fr.univlille.labyrinth.model.gamemode.FreeMode;
import fr.univlille.labyrinth.model.gamemode.GameMode;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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
    private Label labelDistance;

    @FXML
    private Label labelWallSlider;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField heightField;

    @FXML
    private TextField widthField;

    @FXML
    private TextField distanceField;

    @FXML
    private Slider wallPercentageSlider;

    @FXML
    private VBox menu;

    // mettre par défaut les valeurs des champs aux dernieres valeurs du mode libre (reprise)
    @FXML
    public void initialize() {
        heightField.setText("" + FreeMode.mazeHeight);
        widthField.setText("" + FreeMode.mazeWidth);
        distanceField.setText("" + FreeMode.distanceBetweenEntryAndExit);
        wallPercentageSlider.setValue(FreeMode.mazeWallPercentage);
        hideUselessFields();
        resize();
    }

    /**
     * @throws IOException Renvoie une IOException si la scène est inaccessible.
     */
    @FXML
    private void goToModeLaby() throws IOException {
        int width, height, distance ;
        try {
            width = Integer.parseInt(widthField.getText());
            height = Integer.parseInt(heightField.getText());
            distance = Integer.parseInt(distanceField.getText());
        } catch (NumberFormatException e) {
            showError();
            return;
        }

        if (!GameMode.areDimensionsCorrect(width, height)) {
            showError();
            return;
        }

        FreeMode.mazeWidth = width;
        FreeMode.mazeHeight = height;
        FreeMode.mazeWallPercentage = wallPercentageSlider.getValue();

        int maxPossibleDistance = FreeMode.getMaxDistanceBetweenEntryAndExit() ;
        FreeMode.distanceBetweenEntryAndExit = distance > maxPossibleDistance ? maxPossibleDistance : distance ;

        App.goTo("freemode/FreeModeLabyrinth.fxml");
    }

    private void showError() {
        errorLabel.setVisible(true);
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> errorLabel.setVisible(false));
        pause.play();
    }

    /**
     * @throws IOException Renvoie une IOException si la scène est inaccessible.
     */
    @FXML
    private void goToRetour() throws IOException {
        App.goTo("freemode/FreeModeAlgorithmSelection.fxml");
    }

    private void resize(){
        menu.widthProperty().addListener((o, oldW, newW) -> ResizeUtil.resizePanesInPane(menu));
        menu.heightProperty().addListener((o, oldH, newH) -> ResizeUtil.resizePanesInPane(menu));

        for (Node ligne : menu.getChildren()) {
            if(ligne instanceof Pane pane){
                pane.widthProperty().addListener((o, oldW, newW) -> ResizeUtil.resizeControlsInPane( pane));
                pane.heightProperty().addListener((o, oldH, newH) -> ResizeUtil.resizeControlsInPane( pane));
            }
        }
    }

    private void hideUselessFields(){
        if(FreeMode.algorithm.isPerfect()){      
            labelWallSlider.setVisible(false);  
            wallPercentageSlider.setVisible(false);
        }else {
            labelDistance.setVisible(false);
            distanceField.setVisible(false);
        }
    }
}
