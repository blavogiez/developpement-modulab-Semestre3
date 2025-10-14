package fr.univlille.labyrinth.view;

import fr.univlille.labyrinth.controller.LabyrinthControler;
import fr.univlille.labyrinth.model.Direction;
import fr.univlille.labyrinth.model.Maze;
import fr.univlille.labyrinth.model.Observer;
import fr.univlille.labyrinth.model.Position;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

    protected LabyrinthControler controler;

    public void setControler(LabyrinthControler controler) {
        this.controler = controler;
    }

    public LabyrinthScene(Maze maze){
        super(pane);

        HBox hbox = new HBox();
        grid = new GridPane();


        setOnKeyPressed(x -> {
            if (x.getCode().equals(KeyCode.DOWN)) controler.movePlayer(Direction.DOWN);
            else if (x.getCode().equals(KeyCode.UP)) controler.movePlayer(Direction.UP);
            else if (x.getCode().equals(KeyCode.LEFT)) controler.movePlayer(Direction.LEFT);
            else if (x.getCode().equals(KeyCode.RIGHT)) controler.movePlayer(Direction.RIGHT);
        });

        hbox.getChildren().add(grid);

        pane.getChildren().add(hbox);

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
                if (maze.getPlayerPosition().getX()==l && maze.getPlayerPosition().getY()==c){
                    rect.setFill(Paint.valueOf("#FF0000"));
                } else if (maze.getExitPosition().equals(new Position(l,c))) {
                    rect.setFill(Paint.valueOf("#00FF00"));
                }
                 else if (mazeGrid[l][c]){
                    rect.setFill(Paint.valueOf("#FFFFFF"));
                }  else {
                    rect.setFill(Paint.valueOf("#000000"));
                }
                pixels[l][c] = rect;
                grid.add(rect,l,c);

            }
        }
        if (maze.getPlayerPosition().equals(maze.getExitPosition())) {
            TextField tf = new TextField();
            tf.setText("Bravo !");
            pane.getChildren().add(tf);
            controler.playerWin();
        }

    }




}
