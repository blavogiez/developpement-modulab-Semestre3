package fr.univlille.labyrinth.controller.freemode;

import java.io.IOException;

import fr.univlille.labyrinth.App;
import fr.univlille.labyrinth.controller.AppState;
import fr.univlille.labyrinth.controller.LabyrinthController;
import fr.univlille.labyrinth.controller.PopupVictoryController;
import fr.univlille.labyrinth.model.gamemode.FreeMode;
import fr.univlille.labyrinth.view.labyrinth.LabyrinthCanvasView;
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

    private LabyrinthCanvasView labyrinth;

    @Override
    protected void initializeGameMode() {
        gameMode = new FreeMode(AppState.getInstance().getFreeModeConfig());
        gameMode.createMaze();

        labyrinth = new LabyrinthCanvasView(gameMode.getCurrentMaze());
        gameMode.getCurrentMaze().add(labyrinth);

        pane1.setCenter(labyrinth.getView());
        labyrinth.update(gameMode.getCurrentMaze());
    }

    @Override
    public void handleVictory() {
        try {
            PopupVictoryController.openPopup("toi");
            App.goTo("freemode/FreeModeLabyrinth.fxml");
        } catch (Exception e) {
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
