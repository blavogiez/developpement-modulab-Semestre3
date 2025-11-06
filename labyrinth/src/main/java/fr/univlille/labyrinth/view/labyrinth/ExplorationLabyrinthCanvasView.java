package fr.univlille.labyrinth.view.labyrinth;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// à test plus en détail
public class ExplorationLabyrinthCanvasView extends LabyrinthCanvasView {
    private static final int EXPLORATION_RADIUS = 5;

    private boolean[][] cellulesExplorees;

    public ExplorationLabyrinthCanvasView(ObservableMaze maze) {
        super(maze);
        cellulesExplorees = new boolean[maze.getHeight()][maze.getWidth()];
        update(maze);
    }

    @Override
    public void update(ObservableMaze maze) {
        if (cellulesExplorees == null ||
            cellulesExplorees.length != maze.getHeight() ||
            cellulesExplorees[0].length != maze.getWidth()) {
            cellulesExplorees = new boolean[maze.getHeight()][maze.getWidth()];
        }
        super.update(maze);
    }

    @Override
    protected void dessinerElements(GraphicsContext gc, ObservableMaze maze, int hauteur, int largeur) {
        marquerCellulesExplorees(maze);
        dessinerZonesNonExplorees(gc, maze, hauteur, largeur);

        if (shouldRenderCell(maze.getEntryPosition().getY(), maze.getEntryPosition().getX(), maze)) {
            dessinerEntree(gc, maze);
        }
        if (shouldRenderCell(maze.getExitPosition().getY(), maze.getExitPosition().getX(), maze)) {
            dessinerSortie(gc, maze);
        }

        dessinerTrap(gc, maze);
        dessinerJoueur(gc, maze);
    }

    private void marquerCellulesExplorees(ObservableMaze maze) {
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                if (estDansRayon(j, i, maze, EXPLORATION_RADIUS)) {
                    cellulesExplorees[i][j] = true;
                }
            }
        }
    }

    private void dessinerZonesNonExplorees(GraphicsContext gc, ObservableMaze maze, int hauteur, int largeur) {
        gc.setFill(Color.BLACK);

        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                if (!shouldRenderCell(y, x, maze)) {
                    double xCoord = offsetX + x * tailleCellule;
                    double yCoord = offsetY + y * tailleCellule;
                    gc.fillRect(xCoord, yCoord, tailleCellule, tailleCellule);
                }
            }
        }
    }

    @Override
    protected boolean shouldRenderCell(int y, int x, ObservableMaze maze) {
        return cellulesExplorees[y][x];
    }
}
