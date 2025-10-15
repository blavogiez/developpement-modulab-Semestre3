package fr.univlille.labyrinth.view;

import fr.univlille.labyrinth.HelloApplication;
import fr.univlille.labyrinth.controller.LabyrinthControler;
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
 * LabyrinthScene est la scène de Labyrinthe. Elle montre l'avancement au joueur. C'est une vue.
 *
 * @author Angel
 * @version 0.0
 * @since 0.0
 */
public class LabyrinthScene implements Observer<Maze> {


//    private static GridPane pane;
    protected static GridPane grid;

    public static GridPane generateGrid(Maze maze){
        grid = new GridPane();
        NumberBinding db = Bindings.min( HelloApplication.getPrimaryStage().heightProperty().divide(1.2), HelloApplication.getPrimaryStage().widthProperty().divide(1.2));
        grid.prefHeightProperty().bind(db);
        grid.prefWidthProperty().bind(db);
        grid.setAlignment(Pos.CENTER);

        GridPane.setHgrow(grid, Priority.ALWAYS);
        GridPane.setVgrow(grid, Priority.ALWAYS);

        return grid;
    }
    protected LabyrinthControler controler;

    /**
     * Cette méthode permet d'accorder un controler à cette vue
     *
     * @param controler le controleur mis en place de type LabyrinthControler.
     */
    public void setControler(LabyrinthControler controler) {
        this.controler = controler;
    }

    public LabyrinthControler getControler() {
        return this.controler;
    }

    /**
     * Cette méthode permet de générer cette scène
     *
     * @param maze l'observable que cette vue observe.
     */
    public LabyrinthScene(Maze maze){




        grid=new GridPane();
        GridPane.setHgrow(grid, Priority.ALWAYS);
        GridPane.setVgrow(grid, Priority.ALWAYS);


        NumberBinding db = Bindings.min(HelloApplication.getPrimaryStage().heightProperty().divide(1.2), HelloApplication.getPrimaryStage().widthProperty().divide(1.2));
        grid.prefHeightProperty().bind(db);
        grid.prefWidthProperty().bind(db);
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
        double length = Math.min(grid.getPrefHeight()/(double) mazeGrid.length, grid.getPrefWidth()/(double) mazeGrid[0].length);

        for (int l = 0; l < mazeGrid.length; l++){
            for (int c = 0; c < mazeGrid[0].length; c++) {

                Rectangle rect = new Rectangle(length, length);

                // Bind rectangle size to grid size for dynamic resizing
                NumberBinding size = Bindings.min(
                        grid.widthProperty().divide(mazeGrid[0].length),
                        grid.heightProperty().divide(mazeGrid.length)
                );
                rect.widthProperty().bind(size);
                rect.heightProperty().bind(size);

                // Set colors based on position
                if (maze.getPlayerPosition().getX() == l && maze.getPlayerPosition().getY() == c){
                    rect.setFill(Paint.valueOf("#FF0000")); // Player in red
                } else if (maze.getExitPosition().equals(new Position(l, c))) {
                    rect.setFill(Paint.valueOf("#00FF00")); // Exit in green
                } else if (mazeGrid[l][c]){
                    rect.setFill(Paint.valueOf("#FFFFFF")); // Path in white
                } else {
                    rect.setFill(Paint.valueOf("#000000")); // Wall in black
                }

                grid.add(rect, c, l); // Note: JavaFX GridPane uses (col, row)

            }
        }

        // Check if player reached the exit
        if (maze.getPlayerPosition().equals(maze.getExitPosition())) {
            controler.playerWin();
        }
    }


    public GridPane getGridPane() {
        return this.grid;
    }
}
