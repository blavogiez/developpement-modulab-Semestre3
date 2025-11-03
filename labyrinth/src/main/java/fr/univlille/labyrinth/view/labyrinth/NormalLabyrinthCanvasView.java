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
    protected void genererLabyrintheTest() {
        mursVerticaux = new boolean[MAZE_SIZE][MAZE_SIZE];
        mursHorizontaux = new boolean[MAZE_SIZE][MAZE_SIZE];
        Random random = new Random();

        for (int i = 0; i < MAZE_SIZE; i++) {
            for (int j = 0; j < MAZE_SIZE; j++) {
                mursVerticaux[i][j] = random.nextBoolean();
                mursHorizontaux[i][j] = random.nextBoolean();
            }
        }
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
