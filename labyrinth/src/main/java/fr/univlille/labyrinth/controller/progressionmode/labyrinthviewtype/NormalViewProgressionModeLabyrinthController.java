package fr.univlille.labyrinth.controller.progressionmode.labyrinthviewtype;

import fr.univlille.labyrinth.model.gamemode.ProgressionMode;
import fr.univlille.labyrinth.controller.progressionmode.ProgressionModeLabyrinthController;

import fr.univlille.labyrinth.view.LabyrinthGridView;
import javafx.scene.layout.GridPane;



public class NormalViewProgressionModeLabyrinthController extends ProgressionModeLabyrinthController {

    private LabyrinthGridView labyrinth;

    /**
     * Renvoie la gridPane du labyrinthe
     */
    @Override
    protected GridPane setupViews(ProgressionMode gameMode) {
        labyrinth = new LabyrinthGridView(gameMode.getCurrentMaze());
        gameMode.getCurrentMaze().add(labyrinth);
        labyrinth.update(gameMode.getCurrentMaze());
        return labyrinth.getGrid();
    }

    @Override
    protected String getViewSuffix() {
        return "";
    }
}
