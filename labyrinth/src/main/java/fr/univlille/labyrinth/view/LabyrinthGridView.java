package fr.univlille.labyrinth.view;

import fr.univlille.labyrinth.Main;
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
 * LabyrinthGridView est la vue de grille du Labyrinthe. Elle montre l'avancement au joueur. C'est une vue.
 *
 * @author Angel
 * @version 0.0
 * @since 0.0
 */
public class LabyrinthGridView implements Observer<Maze> {


//    private static GridPane pane;
    protected static GridPane grid;

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

    public LabyrinthGridView(Maze maze){



        grid=new GridPane();
        GridPane.setHgrow(grid, Priority.ALWAYS);
        GridPane.setVgrow(grid, Priority.ALWAYS);


        NumberBinding db = Bindings.min(Main.getPrimaryStage().heightProperty().divide(1.2), Main.getPrimaryStage().widthProperty().divide(1.2));
        grid.prefHeightProperty().bind(db);
        grid.prefWidthProperty().bind(db);

        // Taille fixe : le labyrinthe a toujours la meme taille, ce qui change c'est la taille des cases ! de ce fait on peut avoir un tableau en 200x10 sans probleme.
//        grid.setPrefSize(600, 600);
        grid.setMaxSize(600, 600);
        grid.setMinSize(600, 600);

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


                grid.add(rect, c, l); // Note: JavaFX GridPane uses (col, row)

            }
        }

        if (maze.getPlayerPosition().equals(maze.getExitPosition())) {

            controler.playerWin();
        }
    }


    public GridPane getGridPane() {
        return this.grid;
    }
}
