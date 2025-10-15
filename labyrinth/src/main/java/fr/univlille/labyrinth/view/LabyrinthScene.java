package fr.univlille.labyrinth.view;

import fr.univlille.labyrinth.HelloApplication;
import fr.univlille.labyrinth.controller.LabyrinthControler;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


/**
 * LabyrinthScene est la scène de Labyrinthe. Elle montre l'avancement au joueur. C'est une vue.
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class LabyrinthScene implements Observer<Maze> {

    private static GridPane pane;
    protected static GridPane grid = new GridPane();

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
        //super(pane);
        pane = new GridPane();  // Initialize pane here!
        GridPane.setHgrow(pane, Priority.ALWAYS);
        GridPane.setVgrow(pane, Priority.ALWAYS);

        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setAlignment(Pos.CENTER);
        pane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

//        widthProperty().addListener((obs, oldVal, newVal) -> {
//            update(maze);
//        });
//
//        heightProperty().addListener((obs, oldVal, newVal) -> {
//            update(maze);
//        });

        GridPane.setHgrow(grid, Priority.ALWAYS);
        GridPane.setVgrow(grid, Priority.ALWAYS);





        Label label = new Label(maze.getHeight()+" x " + maze.getWidth());
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
            Label winLabel = new Label("Bravo !");
            winLabel.setFont(Font.font("Lexend", FontWeight.BOLD, 32));
            winLabel.setTextFill(Paint.valueOf("#00FF00"));
            pane.add(winLabel, 1, 4); // Add below the grid
            GridPane.setHalignment(winLabel, HPos.CENTER);
            controler.playerWin();
        }
    }

    public GridPane getCompletePane() {
        return this.grid;
    }
}
