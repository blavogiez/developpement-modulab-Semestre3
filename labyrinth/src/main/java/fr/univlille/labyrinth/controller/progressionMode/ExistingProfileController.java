package fr.univlille.labyrinth.controller.progressionmode;

import fr.univlille.labyrinth.Main;
import fr.univlille.labyrinth.model.save.Player;
import fr.univlille.labyrinth.model.save.PlayerDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class ExistingProfileController {
    @FXML
    private Button bouttonNouvellePartie;
    @FXML
    private Button bouttonReprendre;
    @FXML
    private Button bouttonRetour;

    /** 
     * @throws IOException
     */
    @FXML
    private void goToNewProgression() throws IOException {
        String playerName = PlayerNameEntryController.playerName;
        Player newPlayer = new Player(playerName);
        PlayerDatabase.savePlayer(newPlayer);
        Main.goTo("progressionmode/LevelSelection.fxml");
    }



    /** 
     * @throws IOException
     */
    @FXML
    private void goToProgression() throws IOException {
        Main.goTo("progressionmode/LevelSelection.fxml");
    }

    /** 
     * @throws IOException
     */
    @FXML
    private void goToProgressionEntreNom() throws IOException {
        Main.goTo("progressionmode/PlayerNameEntry.fxml");
    }
}
