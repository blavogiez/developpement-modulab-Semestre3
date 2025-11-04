package fr.univlille.labyrinth.view.labyrinth;

import fr.univlille.labyrinth.model.maze.Maze;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Random;

// à test plus en détail
public class ExplorationLabyrinthCanvasView extends LabyrinthCanvasView {
    private static final int EXPLORATION_RADIUS = 5;

    private boolean[][] cellulesExplorees;

    public ExplorationLabyrinthCanvasView(Maze maze) {
        super(maze);
        update(maze);
    }

    @Override
    protected void dessinerElements(GraphicsContext gc, Maze maze, int lignes, int colonnes) {
        marquerCellulesExplorees(maze);
        //update(maze);
        dessinerZonesNonExplorees(gc, maze, lignes, colonnes);
        dessinerJoueur(gc, maze);
    }

    private void marquerCellulesExplorees(Maze maze) {
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                if (estDansRayon(i, j, maze, EXPLORATION_RADIUS)) {
                    cellulesExplorees[i][j] = true;
                }
            }
        }
    }

    private void dessinerZonesNonExplorees(GraphicsContext gc, Maze maze, int lignes, int colonnes) {
        gc.setFill(Color.BLACK);

        for (int i = 0; i < colonnes; i++) {
            for (int j = 0; j < lignes; j++) {
                if (!shouldRenderCell(i, j, maze)) {
                    double x = offsetX + j * tailleCellule;
                    double y = offsetY + i * tailleCellule;
                    gc.fillRect(x, y, tailleCellule, tailleCellule);
                }
            }
        }
    }

    @Override
    protected boolean shouldRenderCell(int ligne, int colonne, Maze maze) {
        return cellulesExplorees[ligne][colonne];
    }
}
