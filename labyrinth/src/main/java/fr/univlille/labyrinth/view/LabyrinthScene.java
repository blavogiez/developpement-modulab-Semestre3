package fr.univlille.labyrinth.view;

import fr.univlille.labyrinth.model.Maze;
import fr.univlille.labyrinth.model.Observer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class LabyrinthScene extends Scene implements Observer<Maze> {
    private static BorderPane pane= new BorderPane();
    private int pixelHeight = 30;
    protected Rectangle[][] pixels;
    protected GridPane grid;

//    protected  controler

    public LabyrinthScene(Maze maze){
        super(pane);

        HBox hbox = new HBox();
        grid = new GridPane();


        GridPane keyboard = new GridPane();
        Button buttonUp = new Button("↑");
        Button buttonLeft = new Button("←");
        Button buttonRight = new Button("→");
        Button buttonDown = new Button("↓");

        buttonDown.setOnAction(e -> {

        });




        hbox.getChildren().add(grid);

        //Make the VB/HB, set it up and replace null with it
        //If it change depending of the model, make a method "update" & implements a Observer interface, to be notified. Dont forget to add the observable
        update(maze);

    }

    @Override
    public void update(Maze maze) {
        grid.getChildren().clear();
        pixels = new Rectangle[maze.getWidth()][maze.getHeight()];
        boolean[][] mazeGrid = maze.getGrid();
        for (int l = 0; l<mazeGrid.length;l++){
            for (int c = 0; c <mazeGrid[0].length;c++) {
                Rectangle rect = new Rectangle(pixelHeight,pixelHeight);
                if (mazeGrid[l][c]){
                    rect.setFill(Paint.valueOf("#000000"));
                } else if (maze.getPlayerPosition().getX()==l && maze.getPlayerPosition().getY()==c){
                    rect.setFill(Paint.valueOf("#FF00AA"));
                } else {
                    rect.setFill(Paint.valueOf("#FFFFFF"));
                }
                pixels[l][c] = rect;
                grid.add(rect,l,c);

            }
        }

    }




}
