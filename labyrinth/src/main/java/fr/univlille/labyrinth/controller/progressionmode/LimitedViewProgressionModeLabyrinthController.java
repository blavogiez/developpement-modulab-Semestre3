package fr.univlille.labyrinth.controller.progressionmode;

import fr.univlille.labyrinth.model.gamemode.ProgressionMode;

import fr.univlille.labyrinth.view.LimitedLabyrinthGridView;
import fr.univlille.labyrinth.view.LocalPlayerView;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

/**
 * Controller du labyrinthe spécifiquement en mode progression sur les étapes 3.
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class LimitedViewProgressionModeLabyrinthController extends AbstractProgressionModeLabyrinthController {

    private LimitedLabyrinthGridView fullMazeView;
    private LocalPlayerView localView;

    @Override
    protected Node setupViews(ProgressionMode gameMode) {
        fullMazeView = new LimitedLabyrinthGridView(gameMode.getCurrentMaze());
        localView = new LocalPlayerView(gameMode.getCurrentMaze());

        // pas besoin de la mettre en observeur car elle ne se fait qu'une fois (pas de pos du joueur affichée !)
        fullMazeView.update(gameMode.getCurrentMaze());
        //gameMode.getCurrentMaze().add(fullMazeView);
        gameMode.getCurrentMaze().add(localView);

        HBox viewContainer = new HBox(150);
        viewContainer.setAlignment(Pos.CENTER);
        viewContainer.getChildren().addAll(fullMazeView.getGrid(), localView.getGrid());

        return viewContainer;
    }

    @Override
    protected String getViewSuffix() {
        return ", vue limitée";
    }
}
