package fr.univlille.labyrinth.view.renderer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/*
 * Pattern strategy pour le dessin de formes (Carré, Triangle, Cercle...)
 */
public interface ShapeRenderer {
    void render(GraphicsContext gc, double x, double y, double size, Color color);
}
