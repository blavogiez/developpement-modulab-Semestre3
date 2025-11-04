package fr.univlille.labyrinth.view.labyrinth;

import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Random;
import fr.univlille.labyrinth.view.GameColors;

public class LocalLabyrinthCanvasView extends LabyrinthCanvasView {

    private static final int MAZE_SIZE = 50;
    private static final int VIEW_RADIUS = 2;
    private static final int VIEW_SIZE = VIEW_RADIUS * 2 + 1;

    public LocalLabyrinthCanvasView(Maze maze) {
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

        gc.strokeRect(offsetX, offsetY, VIEW_SIZE * tailleCellule, VIEW_SIZE * tailleCellule);

        Position player = currentMaze.getPlayerPosition();
        int playerX = player.getX();
        int playerY = player.getY();

        for (int i = 0; i < VIEW_SIZE; i++) {
            for (int j = 0; j < VIEW_SIZE; j++) {
                int globalI = i + playerX - VIEW_RADIUS;
                int globalJ = j + playerY - VIEW_RADIUS;

                if (globalI < 0 || globalI >= MAZE_SIZE || globalJ < 0 || globalJ >= MAZE_SIZE) {
                    continue;
                }

                double x = offsetX + j * tailleCellule;
                double y = offsetY + i * tailleCellule;

                if (mursHorizontaux[globalI][globalJ]) {
                    gc.strokeLine(x, y + tailleCellule, x + tailleCellule, y + tailleCellule);
                }

                if (mursVerticaux[globalI][globalJ]) {
                    gc.strokeLine(x + tailleCellule, y, x + tailleCellule, y + tailleCellule);
                }
            }
        }
    }

    @Override
    protected void dessinerElements(GraphicsContext gc, Maze maze, int lignes, int colonnes) {
        Position exit = maze.getExitPosition();
        Position player = maze.getPlayerPosition();

        if (estDansRayon(exit.getX(), exit.getY(), maze, VIEW_RADIUS)) {
            int localX = exit.getX() - player.getX() + VIEW_RADIUS;
            int localY = exit.getY() - player.getY() + VIEW_RADIUS;
            dessinerMarqueur(gc, localX, localY, Color.GREEN);
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
