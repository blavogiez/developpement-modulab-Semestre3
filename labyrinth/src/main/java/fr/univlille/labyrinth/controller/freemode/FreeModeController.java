package fr.univlille.labyrinth.controller.freemode;

import fr.univlille.labyrinth.App;
import fr.univlille.labyrinth.utils.ResizeUtil;
import fr.univlille.labyrinth.model.gamemode.FreeMode;
import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
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
    private Button bouttonValider;

    @FXML
    private Button bouttonQuitter;

    @FXML
    private Label l;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField heightField;

    @FXML
    private TextField widthField;

    @FXML
    private Slider wallPercentageSlider;

    @FXML
    private VBox menu;

    @FXML
    private HBox line1;

    // mettre par défaut les valeurs des champs aux dernieres valeurs du mode libre (reprise)
    @FXML
    public void initialize() {
        heightField.setText("" + FreeMode.mazeHeight);
        widthField.setText("" + FreeMode.mazeWidth);
        wallPercentageSlider.setValue(FreeMode.mazeWallPercentage);
        resize();

    }

    /**
     * @throws IOException Renvoie une IOException si la scène est inaccessible.
     */
    @FXML
    private void goToModeLaby() throws IOException {
        int width, height;
        try {
            width = Integer.parseInt(widthField.getText());
            height = Integer.parseInt(heightField.getText());
        } catch (NumberFormatException e) {
            showError();
            return;
        }

        // valeurs interdites !
        if (!((width >= 3 && height >= 4) || (width >= 4 && height >= 3))) {
            showError();
            return;
        }

        // les valeurs sont autorisées donc on les bouge dans FreeMode (pour pouvoir les reprendre après de façon fluide !)
        FreeMode.mazeWidth = width;
        FreeMode.mazeHeight = height;
        FreeMode.mazeWallPercentage = wallPercentageSlider.getValue();
        App.goTo("freemode/FreeModeLabyrinth.fxml");
    }

    private void showError() {
        // montre le label d'erreur pendant 2 secondes
        errorLabel.setVisible(true);
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> errorLabel.setVisible(false));
        pause.play();
    }

    /**
     * @throws IOException Renvoie une IOException si la scène est inaccessible.
     */
    @FXML
    private void goToAccueil() throws IOException {
        App.goTo("GameModeSelection.fxml");
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
}
