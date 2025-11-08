package fr.univlille.labyrinth.view.labyrinth;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;

import fr.univlille.labyrinth.model.maze.entities.Entity;
import fr.univlille.labyrinth.view.EntityShapeMapper;
import fr.univlille.labyrinth.view.GameColors;
import fr.univlille.labyrinth.view.renderer.LocalEntityRenderer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LocalLabyrinthCanvasView extends LabyrinthCanvasView {

    private static final int VIEW_RADIUS = 5;
    private static final int VIEW_SIZE = VIEW_RADIUS * 2 + 1;
    private final LocalEntityRenderer localEntityRenderer;

    public LocalLabyrinthCanvasView(ObservableMaze maze) {
        super(maze);
        this.localEntityRenderer = new LocalEntityRenderer(new EntityShapeMapper());
    }

    @Override
    public void update(ObservableMaze maze) {
        this.currentMaze = maze;

        if (canvas.getWidth() == 0 || canvas.getHeight() == 0) {
            return;
        }

        layout = layoutCalculator.calculate(canvas.getWidth(), canvas.getHeight(), VIEW_SIZE, VIEW_SIZE);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(GameColors.PATH.getColor());
        gc.fillRect(layout.getOffsetX(), layout.getOffsetY(), VIEW_SIZE * layout.getCellSize(), VIEW_SIZE * layout.getCellSize());

        dessinerMurs(gc, VIEW_SIZE, VIEW_SIZE);
        dessinerElements(gc, maze, VIEW_SIZE, VIEW_SIZE);
    }

    @Override
    protected void dessinerMurs(GraphicsContext gc, int height, int width) {
        gc.setStroke(GameColors.WALL.getColor());
        gc.setLineWidth(layout.getWallThickness());

        Position player = currentMaze.getPlayerPosition();
        int playerX = player.getX();
        int playerY = player.getY();

        for (int localY = 0; localY < height; localY++) {
            int globalY = playerY - VIEW_RADIUS + localY;

            int localX = 0;
            int globalX = playerX - VIEW_RADIUS + localX;
            if (currentMaze.isWall(globalY, globalX - 1, globalY, globalX)) {
                double x1 = layout.getOffsetX();
                double y1 = layout.getOffsetY() + localY * layout.getCellSize();
                double y2 = y1 + layout.getCellSize();
                gc.strokeLine(x1, y1, x1, y2);
            }

            for (int localX2 = 0; localX2 < width - 1; localX2++) {
                globalX = playerX - VIEW_RADIUS + localX2;
                int globalXNext = globalX + 1;

                if (currentMaze.isWall(globalY, globalX, globalY, globalXNext)) {
                    double x1 = layout.getOffsetX() + (localX2 + 1) * layout.getCellSize();
                    double y1 = layout.getOffsetY() + localY * layout.getCellSize();
                    double y2 = y1 + layout.getCellSize();
                    gc.strokeLine(x1, y1, x1, y2);
                }
            }
        }

        for (int localX3 = 0; localX3 < width; localX3++) {
            int globalX = playerX - VIEW_RADIUS + localX3;

            int localY2 = 0;
            int globalY = playerY - VIEW_RADIUS + localY2;
            if (currentMaze.isWall(globalY - 1, globalX, globalY, globalX)) {
                double x1 = layout.getOffsetX() + localX3 * layout.getCellSize();
                double x2 = x1 + layout.getCellSize();
                double y1 = layout.getOffsetY();
                gc.strokeLine(x1, y1, x2, y1);
            }

            for (int localY3 = 0; localY3 < height - 1; localY3++) {
                globalY = playerY - VIEW_RADIUS + localY3;
                int globalYNext = globalY + 1;

                if (currentMaze.isWall(globalY, globalX, globalYNext, globalX)) {
                    double x1 = layout.getOffsetX() + localX3 * layout.getCellSize();
                    double x2 = x1 + layout.getCellSize();
                    double y1 = layout.getOffsetY() + (localY3 + 1) * layout.getCellSize();
                    gc.strokeLine(x1, y1, x2, y1);
                }
            }
        }
    }

    @Override
    protected void dessinerElements(GraphicsContext gc, ObservableMaze maze, int lignes, int colonnes) {
        Position playerPos = maze.getPlayerPosition();
        for (Entity entity : maze.getEntityManager().getEntities()) {
            localEntityRenderer.renderEntityLocal(gc, entity, layout, playerPos);
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
