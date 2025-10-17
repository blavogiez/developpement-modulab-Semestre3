package fr.univlille.labyrinth.view;

import fr.univlille.labyrinth.model.Maze;
import fr.univlille.labyrinth.model.Observer;
import fr.univlille.labyrinth.model.Position;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


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
        pane = new GridPane();
        GridPane.setHgrow(pane, Priority.ALWAYS);
        GridPane.setVgrow(pane, Priority.ALWAYS);

        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setAlignment(Pos.CENTER);
        pane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        NumberBinding gridSize = Bindings.min(
                Bindings.min(pane.widthProperty().subtract(40), pane.heightProperty().subtract(100)),
                700.0
        );
        grid.prefWidthProperty().bind(gridSize);
        grid.prefHeightProperty().bind(gridSize);
        grid.setMaxSize(700, 700);
        grid.setMinSize(0, 0);
        grid.setAlignment(Pos.CENTER);


        Label label = new Label(maze.getWidth()+" x " + maze.getHeight());
        label.setFont(Font.font("Lexend", FontWeight.BOLD,44));
        label.setUnderline(true);

        GridPane.setHalignment(label, HPos.CENTER);
        GridPane.setValignment(label, VPos.CENTER);



        pane.add(label,1,0);
        pane.add(grid,0,1,2,3);

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

    public GridPane getCompletePane() {
        return this.pane;
    }
}
