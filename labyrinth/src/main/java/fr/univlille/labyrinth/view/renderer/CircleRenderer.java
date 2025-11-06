package fr.univlille.labyrinth.view.renderer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CircleRenderer implements ShapeRenderer {
    @Override
    public void render(GraphicsContext gc, double x, double y, double size, Color color) {
        gc.setFill(color);
        gc.fillOval(x, y, size, size);
    }
}
