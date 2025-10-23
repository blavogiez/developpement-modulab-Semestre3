package fr.univlille.labyrinth.controller.progressionmode;

import fr.univlille.labyrinth.Main;
import fr.univlille.labyrinth.model.save.Player;
import fr.univlille.labyrinth.model.save.PlayerDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;


/**
 * Controller du menu qui apparaît lorsqu'un profil existe déjà.
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class ExistingProfileController {

    @FXML
    private Button bouttonNouvellePartie;
    @FXML
    private Button bouttonReprendre;
    @FXML
    private Button bouttonRetour;

    /**
     * @throws IOException Renvoie une IOException si la scène est inaccessible.
     */
    @FXML
    private void goToNewProgression() throws IOException {
        String playerName = PlayerNameEntryController.playerName;
        Player newPlayer = new Player(playerName);
        PlayerDatabase.savePlayer(newPlayer);
        Main.goTo("progressionmode/LevelSelection.fxml");
    }



    /**
     * @throws IOException Renvoie une IOException si la scène est inaccessible.
     */
    @FXML
    private void goToProgression() throws IOException {
        Main.goTo("progressionMode/LevelSelection.fxml");
    }

    /**
     * @throws IOException Renvoie une IOException si la scène est inaccessible.
     */
    @FXML
    private void goToProgressionEntreNom() throws IOException {
        Main.goTo("progressionMode/PlayerNameEntry.fxml");
    }
}
