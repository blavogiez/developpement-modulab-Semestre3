package fr.univlille.labyrinth.view.renderer;

import fr.univlille.labyrinth.view.Shape;
import fr.univlille.labyrinth.view.layout.LabyrinthLayout;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Gestionnaire de rendu des composants graphiques dans le labyrinthe.
 * Cette classe permet de dessiner des formes avec des couleurs spécifiques
 * à des positions spécifiques dans la vue du labyrinthe.
 *
 * @author Antonin, Angèl, Baptiste, Romain, Victor
 * @version 1.0
 * @since 1.0
 */
public class ComponentRenderer {
    private final ShapeRendererManager shapeManager;

    /**
     * Constructeur du ComponentRenderer.
     * Initialise le gestionnaire de formes pour le rendu.
     */
    public ComponentRenderer() {
        this.shapeManager = new ShapeRendererManager();
    }

    /**
     * Dessine un composant à une position spécifique.
     *
     * @param gc le contexte graphique sur lequel dessiner
     * @param shape la forme à dessiner
     * @param color la couleur de la forme
     * @param x la coordonnée X de la position
     * @param y la coordonnée Y de la position
     * @param size la taille du composant
     */
    public void renderComponent(GraphicsContext gc, Shape shape, Color color, double x, double y, double size) {
        if (shape == null) {
            return;
        }
        shapeManager.render(gc, shape, x, y, size, color);
    }

    /**
     * Dessine un composant à une position de grille spécifique dans le labyrinthe.
     *
     * @param gc le contexte graphique sur lequel dessiner
     * @param shape la forme à dessiner
     * @param color la couleur de la forme
     * @param gridX la coordonnée X dans la grille du labyrinthe
     * @param gridY la coordonnée Y dans la grille du labyrinthe
     * @param layout les paramètres de mise en page du labyrinthe
     * @param sizeRatio le ratio de taille par rapport à la taille de cellule
     */
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
