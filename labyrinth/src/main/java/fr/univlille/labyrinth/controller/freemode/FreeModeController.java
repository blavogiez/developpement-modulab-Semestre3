package fr.univlille.labyrinth.controller.freemode;

import java.io.IOException;

import fr.univlille.labyrinth.App;
import fr.univlille.labyrinth.controller.AppState;
import fr.univlille.labyrinth.model.gamemode.GameMode;
import fr.univlille.labyrinth.model.gamemode.config.FreeModeConfig;
import fr.univlille.labyrinth.view.utils.ResizeUtil;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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

    @FXML
    private HBox distanceBox ;

    @FXML
    private HBox wallBox ;

    // mettre par défaut les valeurs des champs aux dernières valeurs du mode libre (reprise)
    @FXML
    public void initialize() {
        FreeModeConfig config = AppState.getInstance().getFreeModeConfig();
        heightField.setText("" + config.getHeight());
        widthField.setText("" + config.getWidth());
        distanceField.setText("" + config.getDistanceBetweenEntryAndExit());
        wallPercentageSlider.setValue(config.getWallPercentage());
        hideUselessFields();
        resize();
    }

    /**
     * @throws IOException Renvoie une IOException si la scène est inaccessible.
     */
    @FXML
    private void goToModeLaby() throws IOException {
        int width;
        int height;
        int distance ;
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

        FreeModeConfig config = AppState.getInstance().getFreeModeConfig();
        config.setWidth(width);
        config.setHeight(height);
        config.setWallPercentage(wallPercentageSlider.getValue());
        config.setDistanceBetweenEntryAndExit(distance);

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

    /** 
     * @throws IOException
     */
    @FXML
    private void goToComponentConfiguration() throws IOException {
        App.goTo("freemode/FreeModeComponentConfiguration.fxml");
    }

    private void resize(){
        menu.widthProperty().addListener((o, oldW, newW) -> ResizeUtil.resizePanesInPane(menu));
        menu.heightProperty().addListener((o, oldH, newH) -> ResizeUtil.resizePanesInPane(menu));

        for (Node ligne : menu.getChildren()) {
            if(ligne instanceof Pane pane){
                pane.widthProperty().addListener((o, oldW, newW) -> ResizeUtil.resizeControlsToParentSize( pane,0.3,0.8,0,0,0,0));
                pane.heightProperty().addListener((o, oldH, newH) -> ResizeUtil.resizeControlsToParentSize( pane,0.3,0.8,0,0,0,0));
                for (Node controls : pane.getChildren()) {
                    if(controls instanceof Label label){
                        pane.widthProperty().addListener((o, oldW, newW) -> ResizeUtil.resizeControlToParentSize( pane,label,0.9,0.9));
                        pane.heightProperty().addListener((o, oldH, newH) -> ResizeUtil.resizeControlToParentSize( pane,label,0.9,0.9));
                    }
                }
            }
        }
    }

    private void hideUselessFields() {
        FreeModeConfig config = AppState.getInstance().getFreeModeConfig();
        if (config.isPerfectAlgorithm()) {
            menu.getChildren().remove(wallBox);
        } else {
            menu.getChildren().remove(distanceBox);
        }
    }
}
