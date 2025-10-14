package fr.univlille.labyrinth.view;

import fr.univlille.labyrinth.controller.LabyrinthControler;
import fr.univlille.labyrinth.model.Direction;
import fr.univlille.labyrinth.model.Maze;
import fr.univlille.labyrinth.model.Observer;
import fr.univlille.labyrinth.model.Position;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;


/**
 * LabyrinthScene est la scène de Labyrinthe. Elle montre l'avancement au joueur. C'est une vue.
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class LabyrinthScene extends Scene implements Observer<Maze> {

    private final static BorderPane pane= new BorderPane();
    protected Rectangle[][] pixels;
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
     * @param maze l'observable que cette vue observe.
     */
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

    /**
     * Met à jour la méthode.
     *
     * @param maze l'observable que cette vue observe.
     */
    @Override
    public void update(Maze maze) {
        grid.getChildren().clear();
        pixels = new Rectangle[maze.getWidth()][maze.getHeight()];
        boolean[][] mazeGrid = maze.getGrid();
        for (int l = 0; l<mazeGrid.length;l++){
            for (int c = 0; c <mazeGrid[0].length;c++) {
                int pixelHeight = 30;
                Rectangle rect = new Rectangle(pixelHeight, pixelHeight);
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
