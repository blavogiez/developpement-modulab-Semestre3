package fr.univlille.labyrinth.controller.freemode;

import java.io.IOException;

import fr.univlille.labyrinth.App;
import fr.univlille.labyrinth.controller.AppState;
import fr.univlille.labyrinth.controller.LabyrinthController;
import fr.univlille.labyrinth.controller.VictoryNotification;
import fr.univlille.labyrinth.model.gamemode.FreeMode;
import fr.univlille.labyrinth.model.gamemode.manager.MazeManager;
import fr.univlille.labyrinth.model.gamemode.victory.FreeModeVictoryHandler;
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
    public void initialize() {
        super.initialize();
        String winner = VictoryNotification.getPendingWinner();
        if (winner != null) {
            VictoryNotification.show(pane1, winner);
        }
    }

    @Override
    protected void initializeGameMode() {
        MazeManager mazeManager = new MazeManager();
        FreeModeVictoryHandler victoryHandler = new FreeModeVictoryHandler();

        gameMode = new FreeMode(mazeManager, victoryHandler, AppState.getInstance().getFreeModeConfig());
        gameMode.createMaze();

        labyrinth = new LabyrinthCanvasView(gameMode.getCurrentMaze());
        gameMode.getCurrentMaze().add(labyrinth);

        pane1.setCenter(labyrinth.getView());
        labyrinth.update(gameMode.getCurrentMaze());
    }

    @Override
    public void handleVictory() {
        try {
            VictoryNotification.setPendingWinner("toi");
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
