package fr.univlille.labyrinth.view.labyrinth;

import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Random;
import fr.univlille.labyrinth.view.GameColors;

public class LocalLabyrinthCanvasView extends LabyrinthCanvasView {

    private static final int VIEW_RADIUS = 2;
    private static final int VIEW_SIZE = VIEW_RADIUS * 2 + 1;

    public LocalLabyrinthCanvasView(Maze maze) {
        super(maze);
    }

    @Override
    public void update(Maze maze) {
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
    protected void dessinerMurs(GraphicsContext gc, int lignes, int colonnes) {
        gc.setStroke(GameColors.WALL.getColor());
        gc.setLineWidth(epaisseurMur);

        boolean[][] mursHorizontaux = currentMaze.getMurHorizontaux();
        boolean[][] mursVerticaux = currentMaze.getMurVerticaux();

        Position player = currentMaze.getPlayerPosition();
        int playerX = player.getX();
        int playerY = player.getY();

        int mazeWidth = currentMaze.getWidth();
        int mazeHeight = currentMaze.getHeight();

        for (int localCol = 0; localCol < VIEW_SIZE; localCol++) {
            int globalX = playerX - VIEW_RADIUS + localCol;

            double x1 = offsetX + localCol * tailleCellule;
            double x2 = x1 + tailleCellule;

            gc.strokeLine(x1, offsetY, x2, offsetY);

            for (int localRow = 0; localRow < VIEW_SIZE - 1; localRow++) {
                int globalY = playerY - VIEW_RADIUS + localRow;

                if (globalX >= 0 && globalX < mazeWidth - 1 &&
                    globalY >= 0 && globalY < mazeHeight - 1) {
                    if (mursVerticaux[globalY][globalX]) {
                        double y = offsetY + (localRow + 1) * tailleCellule;
                        gc.strokeLine(x1, y, x2, y);
                    }
                }
                else {
                    double x = offsetX + localCol * tailleCellule;
                    double y = offsetY + localRow * tailleCellule;
                    gc.setFill(Color.BLACK);
                    gc.fillRect(x, y, tailleCellule, tailleCellule);
                }
            }

            gc.strokeLine(x1, offsetY + VIEW_SIZE * tailleCellule, x2, offsetY + VIEW_SIZE * tailleCellule);
        }

        for (int localRow = 0; localRow < VIEW_SIZE; localRow++) {
            int globalY = playerY - VIEW_RADIUS + localRow;

            double y1 = offsetY + localRow * tailleCellule;
            double y2 = y1 + tailleCellule;

            
            gc.strokeLine(offsetX, y1, offsetX, y2);

            for (int localCol = 0; localCol < VIEW_SIZE - 1; localCol++) {
                int globalX = playerX - VIEW_RADIUS + localCol;

                if (globalY >= 0 && globalY < mazeHeight - 1 &&
                    globalX >= 0 && globalX < mazeWidth - 1) {
                    if (mursHorizontaux[globalX][globalY]) {
                        double x = offsetX + (localCol + 1) * tailleCellule;
                        gc.strokeLine(x, y1, x, y2);
                    }
                }
                else {
                    double x = offsetX + localCol * tailleCellule;
                    double y = offsetY + localRow * tailleCellule;
                    gc.setFill(Color.BLACK);
                    gc.fillRect(x, y, tailleCellule, tailleCellule);
                }
            }

            gc.strokeLine(offsetX + VIEW_SIZE * tailleCellule, y1, offsetX + VIEW_SIZE * tailleCellule, y2);
        }
    }

    @Override
    protected void dessinerElements(GraphicsContext gc, Maze maze, int lignes, int colonnes) {
        Position exit = maze.getExitPosition();
        Position player = maze.getPlayerPosition();

        if (estDansRayon(exit.getX(), exit.getY(), maze, VIEW_RADIUS)) {
            int localX = exit.getX() - player.getX() + VIEW_RADIUS;
            int localY = exit.getY() - player.getY() + VIEW_RADIUS;
            dessinerMarqueur(gc, localX, localY, GameColors.EXIT.getColor());
        }

        dessinerJoueur(gc, maze);
    }

    @Override
    protected void dessinerJoueur(GraphicsContext gc, Maze maze) {
        dessinerMarqueur(gc, VIEW_RADIUS, VIEW_RADIUS, GameColors.PLAYER.getColor());
    }

    @Override
    protected boolean shouldRenderCell(int ligne, int colonne, Maze maze) {
        return true;
    }
}
