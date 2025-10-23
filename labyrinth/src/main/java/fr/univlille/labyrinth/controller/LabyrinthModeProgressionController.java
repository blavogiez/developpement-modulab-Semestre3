package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.model.gamemode.ProgressionMode;
import fr.univlille.labyrinth.view.LabyrinthGridView;
import javafx.scene.layout.GridPane;


/**
 * Controller des labyrinthes spécifiquement du mode libre
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class LabyrinthModeProgressionController extends AbstractLabyrinthProgressionController {

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
