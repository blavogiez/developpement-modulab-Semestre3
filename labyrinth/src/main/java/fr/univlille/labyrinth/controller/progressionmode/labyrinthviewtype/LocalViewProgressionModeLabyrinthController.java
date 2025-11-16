package fr.univlille.labyrinth.controller.progressionmode.labyrinthviewtype;

import fr.univlille.labyrinth.controller.progressionmode.ProgressionModeLabyrinthController;
import fr.univlille.labyrinth.model.gamemode.ProgressionMode;
import fr.univlille.labyrinth.view.labyrinth.HiddenPlayerNormalLabyrinthCanvasView;
import fr.univlille.labyrinth.view.labyrinth.LocalLabyrinthCanvasView;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

/**
 * Controller du labyrinthe spécifiquement en mode progression sur les étapes 3 et 5.
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class LocalViewProgressionModeLabyrinthController extends ProgressionModeLabyrinthController {
    private final int HBOX_SPACING=150;

    private HiddenPlayerNormalLabyrinthCanvasView fullMazeView;
    private LocalLabyrinthCanvasView localView;

    @Override
    protected Node setupViews(ProgressionMode gameMode) {
        fullMazeView = new HiddenPlayerNormalLabyrinthCanvasView(gameMode.getCurrentMaze());
        localView = new LocalLabyrinthCanvasView(gameMode.getCurrentMaze());

        gameMode.getCurrentMaze().add(fullMazeView);
        gameMode.getCurrentMaze().add(localView);

        HBox viewContainer = new HBox(HBOX_SPACING);
        viewContainer.setAlignment(Pos.CENTER);
        viewContainer.getChildren().addAll(fullMazeView.getView(), localView.getView());

        return viewContainer;
    }

    @Override
    protected String getViewSuffix() {
        return ", vue limitée";
    }
}
