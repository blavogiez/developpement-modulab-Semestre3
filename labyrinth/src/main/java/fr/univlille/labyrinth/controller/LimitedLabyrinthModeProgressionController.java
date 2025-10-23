package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.model.ProgressionMode;
import fr.univlille.labyrinth.view.LimitedLabyrinthGridView;
import fr.univlille.labyrinth.view.LocalPlayerView;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class LimitedLabyrinthModeProgressionController extends AbstractLabyrinthProgressionController {

    private LimitedLabyrinthGridView fullMazeView;
    private LocalPlayerView localView;

    @Override
    protected Node setupViews(ProgressionMode gameMode) {
        fullMazeView = new LimitedLabyrinthGridView(gameMode.getCurrentMaze());
        localView = new LocalPlayerView(gameMode.getCurrentMaze());

        gameMode.getCurrentMaze().add(fullMazeView);
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
