package fr.univlille.labyrinth.view.renderer;

import fr.univlille.labyrinth.view.Shape;
import fr.univlille.labyrinth.view.layout.LabyrinthLayout;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ComponentRenderer {
    private final ShapeRendererManager shapeManager;

    public ComponentRenderer() {
        this.shapeManager = new ShapeRendererManager();
    }

    public void renderComponent(GraphicsContext gc, Shape shape, Color color, double x, double y, double size) {
        if (shape == null) {
            return;
        }
        shapeManager.render(gc, shape, x, y, size, color);
    }

    public void renderComponentAt(GraphicsContext gc, Shape shape, Color color, int gridX, int gridY, LabyrinthLayout layout, double sizeRatio) {
        if (shape == null) {
            return;
        }
        double size = layout.getCellSize() * sizeRatio;
        double x = layout.getOffsetX() + gridX * layout.getCellSize() + (layout.getCellSize() - size) / 2;
        double y = layout.getOffsetY() + gridY * layout.getCellSize() + (layout.getCellSize() - size) / 2;
        shapeManager.render(gc, shape, x, y, size, color);
    }
}
