package fr.univlille.labyrinth.view;

import fr.univlille.labyrinth.model.Maze;
import fr.univlille.labyrinth.model.Observer;
import fr.univlille.labyrinth.model.Position;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;


/**
 * LabyrinthGridView est la vue de grille du Labyrinthe. Elle montre l'avancement au joueur. C'est une vue.
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class LabyrinthGridView implements Observer<Maze> {

    private static GridPane pane;
    protected static GridPane grid = new GridPane();

    /**
     * Cette méthode permet de générer cette scène
     *
     * @param maze l'observable que cette vue observe.
     */
    public LabyrinthGridView(Maze maze){

        NumberBinding gridSize = Bindings.min(
                Bindings.min(grid.widthProperty().subtract(40), grid.heightProperty().subtract(100)),
                700.0
        );
        grid.prefWidthProperty().bind(gridSize);
        grid.prefHeightProperty().bind(gridSize);
        grid.setMaxSize(700, 700);
        grid.setMinSize(0, 0);
        grid.setAlignment(Pos.CENTER);

        update(maze);
    }

    /**
     * Met à jour la méthode.
     *
     * @param maze l'observable que cette vue observe.
     */
    @Override
    public void update(Maze maze) {
        grid.getChildren().clear();
        boolean[][] mazeGrid = maze.getGrid();

        for (int l = 0; l < mazeGrid.length; l++){
            for (int c = 0; c < mazeGrid[0].length; c++) {

                Rectangle rect = new Rectangle();

                NumberBinding size = Bindings.min(
                        grid.widthProperty().divide(mazeGrid[0].length),
                        grid.heightProperty().divide(mazeGrid.length)
                );
                rect.widthProperty().bind(size);
                rect.heightProperty().bind(size);

                if (maze.getPlayerPosition().getX() == l && maze.getPlayerPosition().getY() == c){
                    rect.setFill(Paint.valueOf("#FF0000"));
                } else if (maze.getExitPosition().equals(new Position(l, c))) {
                    rect.setFill(Paint.valueOf("#00FF00"));
                } else if (mazeGrid[l][c]){
                    rect.setFill(Paint.valueOf("#FFFFFF"));
                } else {
                    rect.setFill(Paint.valueOf("#000000"));
                }

                grid.add(rect, l, c);
            }
        }
    }

    public GridPane getGrid() {
        return this.grid;
    }
}
