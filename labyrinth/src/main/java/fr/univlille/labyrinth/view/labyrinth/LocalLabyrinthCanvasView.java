package fr.univlille.labyrinth.view.labyrinth;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.trap.Trap;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import fr.univlille.labyrinth.view.GameColors;

public class LocalLabyrinthCanvasView extends LabyrinthCanvasView {

    private static final int VIEW_RADIUS = 5;
    private static final int VIEW_SIZE = VIEW_RADIUS * 2 + 1;

    public LocalLabyrinthCanvasView(ObservableMaze maze) {
        super(maze);
    }

    @Override
    public void update(ObservableMaze maze) {
        this.currentMaze = maze;

        if (canvas.getWidth() == 0 || canvas.getHeight() == 0) {
            return;
        }

        calculerDimensions(VIEW_SIZE, VIEW_SIZE);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(GameColors.PATH.getColor());
        gc.fillRect(offsetX, offsetY, VIEW_SIZE * tailleCellule, VIEW_SIZE * tailleCellule);

        dessinerMurs(gc, VIEW_SIZE, VIEW_SIZE);
        dessinerElements(gc, maze, VIEW_SIZE, VIEW_SIZE);
    }

    @Override
    protected void dessinerMurs(GraphicsContext gc, int hauteur, int largeur) {
        gc.setStroke(GameColors.WALL.getColor());
        gc.setLineWidth(epaisseurMur);

        Position player = currentMaze.getPlayerPosition();
        int playerX = player.getX();
        int playerY = player.getY();

        for (int localY = 0; localY < VIEW_SIZE; localY++) {
            int globalY = playerY - VIEW_RADIUS + localY;

            int localX = 0;
            int globalX = playerX - VIEW_RADIUS + localX;
            if (currentMaze.isWall(globalY, globalX - 1, globalY, globalX)) {
                double x1 = offsetX;
                double y1 = offsetY + localY * tailleCellule;
                double y2 = y1 + tailleCellule;
                gc.strokeLine(x1, y1, x1, y2);
            }

            for (int localX2 = 0; localX2 < VIEW_SIZE - 1; localX2++) {
                globalX = playerX - VIEW_RADIUS + localX2;
                int globalXNext = globalX + 1;

                if (currentMaze.isWall(globalY, globalX, globalY, globalXNext)) {
                    double x1 = offsetX + (localX2 + 1) * tailleCellule;
                    double y1 = offsetY + localY * tailleCellule;
                    double y2 = y1 + tailleCellule;
                    gc.strokeLine(x1, y1, x1, y2);
                }
            }
        }

        for (int localX3 = 0; localX3 < VIEW_SIZE; localX3++) {
            int globalX = playerX - VIEW_RADIUS + localX3;

            int localY2 = 0;
            int globalY = playerY - VIEW_RADIUS + localY2;
            if (currentMaze.isWall(globalY - 1, globalX, globalY, globalX)) {
                double x1 = offsetX + localX3 * tailleCellule;
                double x2 = x1 + tailleCellule;
                double y1 = offsetY;
                gc.strokeLine(x1, y1, x2, y1);
            }

            for (int localY3 = 0; localY3 < VIEW_SIZE - 1; localY3++) {
                globalY = playerY - VIEW_RADIUS + localY3;
                int globalYNext = globalY + 1;

                if (currentMaze.isWall(globalY, globalX, globalYNext, globalX)) {
                    double x1 = offsetX + localX3 * tailleCellule;
                    double x2 = x1 + tailleCellule;
                    double y1 = offsetY + (localY3 + 1) * tailleCellule;
                    gc.strokeLine(x1, y1, x2, y1);
                }
            }
        }
    }

    @Override
    protected void dessinerElements(GraphicsContext gc, ObservableMaze maze, int lignes, int colonnes) {
        Position exit = maze.getExitPosition();
        Position player = maze.getPlayerPosition();

        if (estDansRayon(exit.getX(), exit.getY(), maze, VIEW_RADIUS)) {
            int localX = exit.getX() - player.getX() + VIEW_RADIUS;
            int localY = exit.getY() - player.getY() + VIEW_RADIUS;
            dessinerMarqueur(gc, localY, localX, GameColors.EXIT.getColor());
        }

        dessinerJoueur(gc, maze);

    }



    @Override
    protected void dessinerJoueur(GraphicsContext gc, ObservableMaze maze) {
        dessinerMarqueur(gc, VIEW_RADIUS, VIEW_RADIUS, GameColors.PLAYER.getColor());
    }

    @Override
    protected boolean shouldRenderCell(int ligne, int colonne, ObservableMaze maze) {
        return true;
    }
}
