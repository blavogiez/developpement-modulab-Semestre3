package fr.univlille.labyrinth.view.labyrinth;

import fr.univlille.labyrinth.model.maze.Maze;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Random;

public class NormalLabyrinthCanvasView extends LabyrinthCanvasView {

    private static final int MAZE_SIZE = 50;

    public NormalLabyrinthCanvasView(Maze maze) {
        super(maze);
    }

    @Override
    protected void dessinerElements(GraphicsContext gc, Maze maze, int lignes, int colonnes) {
        dessinerEntree(gc, maze);
        dessinerSortie(gc, maze);
        dessinerJoueur(gc, maze);
    }

    @Override
    protected boolean shouldRenderCell(int ligne, int colonne, Maze maze) {
        return true;
    }
}
