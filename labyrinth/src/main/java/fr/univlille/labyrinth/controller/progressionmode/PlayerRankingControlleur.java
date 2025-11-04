package fr.univlille.labyrinth.controller.progressionmode;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import fr.univlille.labyrinth.App;
import fr.univlille.labyrinth.model.save.Player;
import fr.univlille.labyrinth.model.save.PlayerDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class PlayerRankingControlleur {

    private List<Player> ranking= PlayerDatabase.loadAllPlayers();

    @FXML
    private ListView<Player> classement;

    @FXML
    public void initialize(){
        Collections.sort(ranking);
        classement.getItems().setAll(ranking);
    }

    /**
     * @throws IOException Renvoie une IOException si la scène est inaccessible.
     */
    @FXML
    private void goToLevelSelection() throws IOException {
        App.goTo("progressionmode/LevelSelection.fxml");
    }
    
}
