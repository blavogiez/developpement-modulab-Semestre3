package fr.univlille.labyrinth.view.renderer;

import fr.univlille.labyrinth.view.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

public class ShapeRendererManager {
    private final Map<Shape, ShapeRenderer> renderers;

    public ShapeRendererManager() {
        this.renderers = new HashMap<>();
        this.renderers.put(Shape.CIRCLE, new CircleRenderer());
        this.renderers.put(Shape.SQUARE, new SquareRenderer());
        this.renderers.put(Shape.TRIANGLE, new TriangleRenderer());
    }

    /** 
     * @param gc
     * @param shape
     * @param x
     * @param y
     * @param size
     * @param color
     */
    public void render(GraphicsContext gc, Shape shape, double x, double y, double size, Color color) {
        ShapeRenderer renderer = renderers.get(shape);
        if (renderer != null) {
            renderer.render(gc, x, y, size, color);
        }
    }
}
