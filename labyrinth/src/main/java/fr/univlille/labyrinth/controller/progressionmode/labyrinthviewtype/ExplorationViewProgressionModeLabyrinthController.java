package fr.univlille.labyrinth.controller.progressionmode.labyrinthviewtype;

import fr.univlille.labyrinth.controller.progressionmode.ProgressionModeLabyrinthController;
import fr.univlille.labyrinth.model.gamemode.ProgressionMode;
import fr.univlille.labyrinth.view.labyrinth.ExplorationLabyrinthCanvasView;
import fr.univlille.labyrinth.view.labyrinth.LocalLabyrinthCanvasView;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;



public class ExplorationViewProgressionModeLabyrinthController extends ProgressionModeLabyrinthController{



    /** 
     * @param gameMode Mode de jeu
     * @return Node
     */
    @Override
    protected Node setupViews(ProgressionMode gameMode) {
        ExplorationLabyrinthCanvasView explorationView = new ExplorationLabyrinthCanvasView(gameMode.getCurrentMaze());
        LocalLabyrinthCanvasView localView = new LocalLabyrinthCanvasView(gameMode.getCurrentMaze());

        gameMode.getCurrentMaze().add(explorationView);
        gameMode.getCurrentMaze().add(localView);

        final int INTERSPACING = 150;

        HBox viewContainer = new HBox(INTERSPACING);
        viewContainer.setAlignment(Pos.CENTER);
        viewContainer.getChildren().addAll(explorationView.getView(), localView.getView());

        return viewContainer;
    }

    /** 
     * @return String
     */
    @Override
    protected String getViewSuffix() {
        return ", vue limitée";
    }
}
