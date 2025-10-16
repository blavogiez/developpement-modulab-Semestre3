package fr.univlille.labyrinth.view;

import fr.univlille.labyrinth.Main;
import fr.univlille.labyrinth.controller.LabyrinthControler;
import fr.univlille.labyrinth.model.*;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
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
public class LabyrinthView extends Scene implements Observer<Maze> {

    private final static GridPane pane= new GridPane();
    protected GridPane grid;
    protected LabyrinthControler controler;

    /**
     * Cette méthode permet d'accorder un controler à cette vue
     *
     * @param controler le controleur mis en place de type LabyrinthControler.
     */
    public void setControler(LabyrinthControler controler) {
        this.controler = controler;
    }

    /**
     * Cette méthode permet de générer cette scène
     *
     * @param gamemode l'observable que cette vue observe.
     */
    public LabyrinthView(GameMode gamemode){
        super(pane);
        FreeMode.mazeHeight=11;
        FreeMode.mazeWidth=11;
        FreeMode.mazeWallPercentage=0.5;
        gamemode.start();
        Maze maze = gamemode.getCurrentMaze();

        gamemode.getCurrentMaze().add(this);
        setControler(new LabyrinthControler(gamemode));

        GridPane.setHgrow(pane, Priority.ALWAYS);
        GridPane.setVgrow(pane, Priority.ALWAYS);

        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setAlignment(Pos.CENTER);

        grid = new GridPane();
        NumberBinding db = Bindings.min(Main.getPrimaryStage().heightProperty().divide(1.2), Main.getPrimaryStage().widthProperty().divide(1.2));
        grid.prefHeightProperty().bind(db);
        grid.prefWidthProperty().bind(db);
        grid.setAlignment(Pos.CENTER);

        widthProperty().addListener((obs, oldVal, newVal) -> update(maze));

        heightProperty().addListener((obs, oldVal, newVal) -> update(maze));

        GridPane.setHgrow(grid, Priority.ALWAYS);
        GridPane.setVgrow(grid, Priority.ALWAYS);



        setOnKeyPressed(x -> {
            if (x.getCode().equals(KeyCode.DOWN)) controler.movePlayer(Direction.DOWN);
            else if (x.getCode().equals(KeyCode.UP)) controler.movePlayer(Direction.UP);
            else if (x.getCode().equals(KeyCode.LEFT)) controler.movePlayer(Direction.LEFT);
            else if (x.getCode().equals(KeyCode.RIGHT)) controler.movePlayer(Direction.RIGHT);
        });

        Label label = new Label(maze.getHeight()+" x "+maze.getWidth());
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
        double length = Math.min(grid.getPrefHeight()/(double) mazeGrid.length,grid.getPrefWidth()/(double) mazeGrid[0].length);
        for (int l = 0; l<mazeGrid.length;l++){
            for (int c = 0; c <mazeGrid[0].length;c++) {

                Rectangle rect = new Rectangle(length,length);

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
                grid.add(rect,l,c);

            }
        }
        if (maze.getPlayerPosition().equals(maze.getExitPosition())) {
            Label lb = new Label("Bravo !");
            pane.getChildren().add(lb);
            controler.playerWin();
        }

    }
}
