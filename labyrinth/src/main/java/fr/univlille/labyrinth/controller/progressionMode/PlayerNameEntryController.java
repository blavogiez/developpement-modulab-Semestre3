package fr.univlille.labyrinth.controller.progressionmode;

import fr.univlille.labyrinth.Main;
import fr.univlille.labyrinth.utils.ResizeUtil;
import fr.univlille.labyrinth.model.save.PlayerDatabase;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class PlayerNameEntryController {
    // nom rentré par le joueur
    // + valeur par défaut si rien n'est entré
    public static String playerName = "toto";

    @FXML
    private TextField nameField;

    @FXML
    private VBox menu;

    /** 
     * @throws IOException
     */
    @FXML
    public void initialize(){
        menu.widthProperty().addListener((o, oldW, newW) -> ResizeUtil.redimensionnerVboxControles(menu));
        menu.heightProperty().addListener((o, oldH, newH) -> ResizeUtil.redimensionnerVboxControles(menu));
    }
    @FXML
    private void goToProgression() throws IOException {
        // Collecte de la saisie de l'utilisateur + traitement
        String name = nameField.getText();
        if (name != null && !name.trim().isEmpty()) {
            playerName = name.trim();
        }
        if (PlayerDatabase.playerExists(playerName)) {
            Main.goTo("progressionmode/ExistingProfile.fxml");
        } else {
            Main.goTo("progressionmode/LevelSelection.fxml");
        }
    }

    /** 
     * @throws IOException
     */
    @FXML
    private void goToAccueil() throws IOException {
        Main.goTo("GameModeSelection.fxml");
    }
}
