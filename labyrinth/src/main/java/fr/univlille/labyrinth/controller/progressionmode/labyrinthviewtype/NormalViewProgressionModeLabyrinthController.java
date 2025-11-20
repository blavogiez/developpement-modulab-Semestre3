package fr.univlille.labyrinth.controller.progressionmode.labyrinthviewtype;

import fr.univlille.labyrinth.model.gamemode.ProgressionMode;
import fr.univlille.labyrinth.controller.progressionmode.ProgressionModeLabyrinthController;
import fr.univlille.labyrinth.view.labyrinth.LabyrinthCanvasView;
import javafx.scene.layout.Pane;



public class NormalViewProgressionModeLabyrinthController extends ProgressionModeLabyrinthController {


    /**
     * Renvoie la gridPane du labyrinthe
     */
    @Override
    protected Pane setupViews(ProgressionMode gameMode) {
        LabyrinthCanvasView labyrinth;
        labyrinth = new LabyrinthCanvasView(gameMode.getCurrentMaze());
        gameMode.getCurrentMaze().add(labyrinth);
        labyrinth.update(gameMode.getCurrentMaze());
        return labyrinth.getView();
    }

    /** 
     * @return String
     */
    @Override
    protected String getViewSuffix() {
        return "";
    }
}
