package fr.univlille.labyrinth.controller.progressionmode.labyrinthviewtype;

import fr.univlille.labyrinth.controller.progressionmode.ProgressionModeLabyrinthController;
import fr.univlille.labyrinth.model.gamemode.ProgressionMode;
import fr.univlille.labyrinth.view.labyrinth.ExplorationLabyrinthCanvasView;
import javafx.scene.layout.Pane;



public class ExplorationViewProgressionModeLabyrinthController extends ProgressionModeLabyrinthController {

    private ExplorationLabyrinthCanvasView labyrinth;

    /**
     * Renvoie la gridPane du labyrinthe
     */
    @Override
    protected Pane setupViews(ProgressionMode gameMode) {
        labyrinth = new ExplorationLabyrinthCanvasView(gameMode.getCurrentMaze());
        gameMode.getCurrentMaze().add(labyrinth);
        labyrinth.update(gameMode.getCurrentMaze());
        return labyrinth.getView();
    }

    @Override
    protected String getViewSuffix() {
        return ", vue d'exploration";
    }
}
