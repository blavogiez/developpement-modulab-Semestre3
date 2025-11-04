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
        cellulesExplorees = new boolean[maze.getHeight()][maze.getWidth()];
        update(maze);
    }

    @Override
    public void update(Maze maze) {
        if (cellulesExplorees == null ||
            cellulesExplorees.length != maze.getHeight() ||
            cellulesExplorees[0].length != maze.getWidth()) {
            cellulesExplorees = new boolean[maze.getHeight()][maze.getWidth()];
        }
        super.update(maze);
    }

    @Override
    protected void dessinerElements(GraphicsContext gc, Maze maze, int lignes, int colonnes) {
        marquerCellulesExplorees(maze);
        dessinerZonesNonExplorees(gc, maze, lignes, colonnes);

        if (shouldRenderCell(maze.getEntryPosition().getX(), maze.getEntryPosition().getY(), maze)) {
            dessinerEntree(gc, maze);
        }
        if (shouldRenderCell(maze.getExitPosition().getX(), maze.getExitPosition().getY(), maze)) {
            dessinerSortie(gc, maze);
        }

        dessinerJoueur(gc, maze);
    }

    private void marquerCellulesExplorees(Maze maze) {
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                if (estDansRayon(j, i, maze, EXPLORATION_RADIUS)) {
                    cellulesExplorees[i][j] = true;
                }
            }
        }
    }

    private void dessinerZonesNonExplorees(GraphicsContext gc, Maze maze, int lignes, int colonnes) {
        gc.setFill(Color.BLACK);

        for (int ligne = 0; ligne < lignes; ligne++) {
            for (int colonne = 0; colonne < colonnes; colonne++) {
                if (!shouldRenderCell(colonne, ligne, maze)) {
                    double x = offsetX + colonne * tailleCellule;
                    double y = offsetY + ligne * tailleCellule;
                    gc.fillRect(x, y, tailleCellule, tailleCellule);
                }
            }
        }
    }

    @Override
    protected boolean shouldRenderCell(int ligne, int colonne, Maze maze) {
        return cellulesExplorees[colonne][ligne];
    }
}
