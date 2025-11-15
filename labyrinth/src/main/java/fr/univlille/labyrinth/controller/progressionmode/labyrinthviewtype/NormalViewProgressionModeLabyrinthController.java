package fr.univlille.labyrinth.controller.progressionmode.labyrinthviewtype;

import fr.univlille.labyrinth.model.gamemode.ProgressionMode;
import fr.univlille.labyrinth.controller.progressionmode.ProgressionModeLabyrinthController;
import fr.univlille.labyrinth.view.labyrinth.LabyrinthCanvasView;
import javafx.scene.layout.Pane;



public class NormalViewProgressionModeLabyrinthController extends ProgressionModeLabyrinthController {

    private LabyrinthCanvasView labyrinth;

    /**
     * Renvoie la gridPane du labyrinthe
     */
    @Override
    protected Pane setupViews(ProgressionMode gameMode) {
        labyrinth = new LabyrinthCanvasView(gameMode.getCurrentMaze());
        gameMode.getCurrentMaze().add(labyrinth);
        labyrinth.update(gameMode.getCurrentMaze());
        return labyrinth.getView();
    }

    @Override
    protected String getViewSuffix() {
        return "";
    }
}
