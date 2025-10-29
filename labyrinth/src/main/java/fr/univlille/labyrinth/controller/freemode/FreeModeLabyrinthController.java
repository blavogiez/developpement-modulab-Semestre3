package fr.univlille.labyrinth.controller.freemode;

import java.io.IOException;

import fr.univlille.labyrinth.App;
import fr.univlille.labyrinth.controller.LabyrinthController;
import fr.univlille.labyrinth.model.gamemode.FreeMode;
import fr.univlille.labyrinth.view.LabyrinthGridView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller des labyrinthes spécifiquement du mode libre
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class FreeModeLabyrinthController extends LabyrinthController<FreeMode> {
    @FXML
    private Button bouttonRetour;

    private LabyrinthGridView labyrinth;

    @Override
    protected void initializeGameMode() {
        gameMode = new FreeMode();
        gameMode.createMaze();

        labyrinth = new LabyrinthGridView(gameMode.getCurrentMaze());
        gameMode.getCurrentMaze().add(labyrinth);

        pane1.setCenter(labyrinth.getGrid());
        labyrinth.update(gameMode.getCurrentMaze());
    }

    @Override
    protected void handleVictory() {
        try {
            App.goTo("freemode/FreeModeLabyrinth.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @throws IOException Renvoie une IOException si la scène est inaccessible.
     */
    @FXML
    private void goToRetour() throws IOException {
        App.goTo("freemode/FreeMode.fxml");
    }

}
