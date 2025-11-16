package fr.univlille.labyrinth.controller.progressionmode.labyrinthviewtype;

import fr.univlille.labyrinth.controller.progressionmode.ProgressionModeLabyrinthController;
import fr.univlille.labyrinth.model.gamemode.ProgressionMode;
import fr.univlille.labyrinth.view.labyrinth.ExplorationLabyrinthCanvasView;
import fr.univlille.labyrinth.view.labyrinth.LocalLabyrinthCanvasView;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;



public class ExplorationViewProgressionModeLabyrinthController extends ProgressionModeLabyrinthController{
    private final int HBOX_SPACING=150;

    private ExplorationLabyrinthCanvasView explorationView;
    private LocalLabyrinthCanvasView localView;

    @Override
    protected Node setupViews(ProgressionMode gameMode) {
        explorationView = new ExplorationLabyrinthCanvasView(gameMode.getCurrentMaze());
        localView = new LocalLabyrinthCanvasView(gameMode.getCurrentMaze());

        gameMode.getCurrentMaze().add(explorationView);
        gameMode.getCurrentMaze().add(localView);

        HBox viewContainer = new HBox(HBOX_SPACING);
        viewContainer.setAlignment(Pos.CENTER);
        viewContainer.getChildren().addAll(explorationView.getView(), localView.getView());

        return viewContainer;
    }

    @Override
    protected String getViewSuffix() {
        return ", vue limitée";
    }
}
