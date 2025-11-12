package fr.univlille.labyrinth.controller.progressionmode.labyrinthviewtype;

import fr.univlille.labyrinth.model.gamemode.GameMode;
import fr.univlille.labyrinth.model.gamemode.ProgressionMode;

import java.io.IOException;

import fr.univlille.labyrinth.controller.progressionmode.ProgressionModeLabyrinthController;
import fr.univlille.labyrinth.view.labyrinth.NormalLabyrinthCanvasView;
import javafx.scene.layout.Pane;



public class NormalViewProgressionModeLabyrinthController extends ProgressionModeLabyrinthController {

    private NormalLabyrinthCanvasView labyrinth;

    /**
     * Renvoie la gridPane du labyrinthe
     */
    @Override
    protected Pane setupViews(ProgressionMode gameMode) {
        labyrinth = new NormalLabyrinthCanvasView(gameMode.getCurrentMaze());
        gameMode.getCurrentMaze().add(labyrinth);
        labyrinth.update(gameMode.getCurrentMaze());
        return labyrinth.getView();
    }

    @Override
    protected String getViewSuffix() {
        return "";
    }
}
