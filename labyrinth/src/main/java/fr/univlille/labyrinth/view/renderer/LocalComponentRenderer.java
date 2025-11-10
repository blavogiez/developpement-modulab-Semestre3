package fr.univlille.labyrinth.view.renderer;

import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.view.Shape;
import fr.univlille.labyrinth.view.layout.LabyrinthLayout;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LocalComponentRenderer extends ComponentRenderer {
    private static final int VIEW_RADIUS = 5;

    public LocalComponentRenderer() {
        super();
    }

    public void renderComponentAtLocal(GraphicsContext gc, Shape shape, Color color, int gridX, int gridY, LabyrinthLayout layout, double sizeRatio, Position playerPos) {
        int dx = Math.abs(gridX - playerPos.getX());
        int dy = Math.abs(gridY - playerPos.getY());

        if (Math.max(dx, dy) <= VIEW_RADIUS) {
            LabyrinthLayout localLayout = new LabyrinthLayout(
                layout.getCellSize(),
                layout.getOffsetX() - (playerPos.getX() - VIEW_RADIUS) * layout.getCellSize(),
                layout.getOffsetY() - (playerPos.getY() - VIEW_RADIUS) * layout.getCellSize(),
                layout.getWallThickness()
            );

            renderComponentAt(gc, shape, color, gridX, gridY, localLayout, sizeRatio);
        }
    }
}
