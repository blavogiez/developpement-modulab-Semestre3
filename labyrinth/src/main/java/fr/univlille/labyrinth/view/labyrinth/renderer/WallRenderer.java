package fr.univlille.labyrinth.view.labyrinth.renderer;

import fr.univlille.labyrinth.model.maze.MazeWallChecker;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.view.GameViewConfig;
import fr.univlille.labyrinth.view.labyrinth.filter.WallFilter;
import fr.univlille.labyrinth.view.layout.LabyrinthLayout;
import javafx.scene.canvas.GraphicsContext;

public class WallRenderer {

    private final WallFilter wallFilter;

    public WallRenderer(WallFilter wallFilter) {
        this.wallFilter = wallFilter;
    }

    /** 
     * @param gc
     * @param maze
     * @param layout
     * @param height
     * @param width
     */
    public void dessinerMurs(GraphicsContext gc, ObservableMaze maze, LabyrinthLayout layout, int height, int width) {
        gc.setStroke(GameViewConfig.WALL.getColor());
        gc.setLineWidth(layout.getWallThickness());

        dessinerMursVerticaux(gc, maze, layout, height, width);
        dessinerMursHorizontaux(gc, maze, layout, height, width);
    }

    /** 
     * @param gc
     * @param maze
     * @param layout
     * @param height
     * @param width
     */
    private void dessinerMursHorizontaux(GraphicsContext gc, ObservableMaze maze, LabyrinthLayout layout, int height, int width) {
        for (int x = 0; x < width; x++) {
            if (wallFilter.shouldDrawHorizontalWall(-1, x, height, width)) {
                double x1 = layout.getOffsetX() + x * layout.getCellSize();
                double x2 = x1 + layout.getCellSize();
                double y1 = layout.getOffsetY();
                gc.strokeLine(x1, y1, x2, y1);
            }
            for (int y = 0; y < height; y++) {
                if (MazeWallChecker.isWall(maze, y, x, y + 1, x) && wallFilter.shouldDrawHorizontalWall(y, x, height, width)) {
                    double x1 = layout.getOffsetX() + x * layout.getCellSize();
                    double x2 = x1 + layout.getCellSize();
                    double y1 = layout.getOffsetY() + (y + 1) * layout.getCellSize();
                    gc.strokeLine(x1, y1, x2, y1);
                }
            }
        }
    }

    /** 
     * @param gc
     * @param maze
     * @param layout
     * @param height
     * @param width
     */
    private void dessinerMursVerticaux(GraphicsContext gc, ObservableMaze maze, LabyrinthLayout layout, int height, int width) {
        for (int y = 0; y < height; y++) {
            if (wallFilter.shouldDrawVerticalWall(y, -1, height, width)) {
                double x1 = layout.getOffsetX();
                double y1 = layout.getOffsetY() + y * layout.getCellSize();
                double y2 = y1 + layout.getCellSize();
                gc.strokeLine(x1, y1, x1, y2);
            }

            for (int x = 0; x < width; x++) {
                if (MazeWallChecker.isWall(maze, y, x, y, x + 1) && wallFilter.shouldDrawVerticalWall(y, x, height, width)) {
                    double x1 = layout.getOffsetX() + (x + 1) * layout.getCellSize();
                    double y1 = layout.getOffsetY() + y * layout.getCellSize();
                    double y2 = y1 + layout.getCellSize();
                    gc.strokeLine(x1, y1, x1, y2);
                }
            }
        }
    }
}
