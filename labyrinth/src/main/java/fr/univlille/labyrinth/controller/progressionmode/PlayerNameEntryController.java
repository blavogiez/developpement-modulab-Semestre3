package fr.univlille.labyrinth.controller.progressionmode;

import fr.univlille.labyrinth.App;
import fr.univlille.labyrinth.utils.ResizeUtil;
import fr.univlille.labyrinth.model.save.PlayerDatabase;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class PlayerNameEntryController {
    public static String playerName = "toto";

    @FXML
    private TextField nameField;

    @FXML
    private VBox menu;

    /**
     * @throws IOException Renvoie une IOException si la scène est inaccessible.
     */
    @FXML
    public void initialize(){
        menu.widthProperty().addListener((o, oldW, newW) -> ResizeUtil.resizeControlsInPane(menu));
        menu.heightProperty().addListener((o, oldH, newH) -> ResizeUtil.resizeControlsInPane(menu));
    }

    @FXML
    private void goToProgression() throws IOException {
        String name = nameField.getText().trim();
        if (name.isEmpty()) return;
        playerName = name;
        if (PlayerDatabase.playerExists(playerName)) {
            App.goTo("progressionmode/ExistingProfile.fxml");
        } else {
            App.goTo("progressionmode/LevelSelection.fxml");
        }
    }

    /**
     * @throws IOException Renvoie une IOException si la scène est inaccessible.
     */
    @FXML
    private void goToAccueil() throws IOException {
        App.goTo("GameModeSelection.fxml");
    }
}
