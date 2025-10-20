package fr.univlille.labyrinth.view;

import fr.univlille.labyrinth.model.Maze;
import fr.univlille.labyrinth.model.Observer;
import fr.univlille.labyrinth.model.Position;

import static fr.univlille.labyrinth.view.GameColors.*;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;


/**
 * LimitedLabyrinthGridView est la vue de grille du Labyrinthe sert à la vue locale au joueur comme demandée dans le sujet. C'est une vue ne montrant pas la position du joueur !
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class LimitedLabyrinthGridView implements Observer<Maze> {
    protected GridPane grid = new GridPane();

    /**
     * Cette méthode permet de générer cette scène
     *
     * @param maze l'observable que cette vue observe.
     */
    public LimitedLabyrinthGridView(Maze maze){
        // taille fixe pour éviter les bindings circulaires
        grid.setPrefSize(500, 500);
        grid.setMaxSize(700, 700);
        grid.setMinSize(100, 100);
        grid.setAlignment(Pos.CENTER);

        update(maze);
    }

    /**
     * Met à jour la méthode.
     *
     * @param maze l'observable que cette vue observe.
     */
    // affiche le labyrinthe complet sans la position du joueur
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

                if (maze.getExitPosition().equals(new Position(l, c))) {
                    rect.setFill(Paint.valueOf(EXIT));
                } else if (mazeGrid[l][c]){
                    rect.setFill(Paint.valueOf(PATH));
                } else {
                    rect.setFill(Paint.valueOf(WALL));
                }
                grid.add(rect, l, c);
            }
        }
    }

    /** 
     * @return GridPane
     */
    public GridPane getGrid() {
        return grid;
    }
}
