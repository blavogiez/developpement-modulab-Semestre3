package fr.univlille.labyrinth.view.renderer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SquareRenderer implements ShapeRenderer {
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
        gc.fillRect(x, y, size, size);
    }
}
