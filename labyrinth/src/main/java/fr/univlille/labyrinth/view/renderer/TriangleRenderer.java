package fr.univlille.labyrinth.view.renderer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TriangleRenderer implements ShapeRenderer {

    private final int NPOINTS=3;
    /** 
     * @param gc
     * @param x
     * @param y
     * @param size
     * @param color
     */
    @Override
    public void render(GraphicsContext gc, double x, double y, double size, Color color) {
        gc.setFill(color);
        double[] xPoints = {x + size / 2, x, x + size};
        double[] yPoints = {y, y + size, y + size};
        gc.fillPolygon(xPoints, yPoints, NPOINTS);
    }
}
