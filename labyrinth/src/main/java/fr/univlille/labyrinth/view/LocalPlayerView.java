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
 * vue locale 3x3 autour du joueur avec portée de 1 case
 */
public class LocalPlayerView implements Observer<Maze> {
    protected GridPane grid = new GridPane();

    public LocalPlayerView(Maze maze){
        // taille responsive avec maximum 200x200
        grid.setPrefSize(200, 200);
        grid.setMaxSize(200, 200);
        grid.setMinSize(50, 50);
        grid.setAlignment(Pos.CENTER);

        update(maze);
    }

    /** 
     * @param maze
     */
    // affiche une grille 3x3 autour du joueur
    @Override
    public void update(Maze maze) {
        grid.getChildren().clear();
        boolean[][] mazeGrid = maze.getGrid();
        Position playerPos = maze.getPlayerPosition();

        // vue locale de 3x3 (portée de 1 case autour du joueur)
        for (int dl = -1; dl <= 1; dl++) {
            for (int dc = -1; dc <= 1; dc++) {
                Rectangle rect = new Rectangle();

                // taille fixe pour chaque case de la vue locale
                NumberBinding size = Bindings.min(
                        grid.widthProperty().divide(3),
                        grid.heightProperty().divide(3)
                );
                rect.widthProperty().bind(size);
                rect.heightProperty().bind(size);

                int absL = playerPos.getX() + dl;
                int absC = playerPos.getY() + dc;

                // position du joueur
                if (dl == 0 && dc == 0) {
                    rect.setFill(Paint.valueOf(PLAYER));
                }
                // hors limites du labyrinthe
                else if (absL < 0 || absL >= mazeGrid.length || absC < 0 || absC >= mazeGrid[0].length) {
                    rect.setFill(Paint.valueOf(OUT_OF_BOUNDS));
                }
                // sortie visible dans la zone
                else if (maze.getExitPosition().equals(new Position(absL, absC))) {
                    rect.setFill(Paint.valueOf(EXIT));
                }
                // passage ou mur
                else if (mazeGrid[absL][absC]) {
                    rect.setFill(Paint.valueOf(PATH));
                } else {
                    rect.setFill(Paint.valueOf(WALL));
                }

                // positionner dans la grille locale (meme convention que labyrinthgridview)
                grid.add(rect, dl + 1, dc + 1);
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
