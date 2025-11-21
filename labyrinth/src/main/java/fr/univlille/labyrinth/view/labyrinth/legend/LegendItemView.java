package fr.univlille.labyrinth.view.labyrinth.legend;

import fr.univlille.labyrinth.view.Shape;
import fr.univlille.labyrinth.view.renderer.ComponentRenderer;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * Vue d'un élément de légende représentant un composant du labyrinthe avec sa forme et sa couleur.
 * Affiche un canvas avec la forme de l'élément et des labels pour le nom et le compteur.
 *
 * @author Antonin, Angèl, Baptiste, Romain, Victor
 * @version 1.0
 * @since 1.0
 */
public class LegendItemView extends HBox {
    private static final double SHAPE_SIZE = 20;
    private static final double CANVAS_SIZE = 30;
    private static final double ITEM_SPACING = 10;
    private static final double NAME_MIN_WIDTH = 100;
    private static final double COUNT_MIN_WIDTH = 40;

    private final Canvas shapeCanvas;
    private final Label nameLabel;
    private final Label countLabel;
    private final ComponentRenderer renderer;

    /**
     * Constructeur de la vue d'un élément de légende.
     *
     * @param name Le nom de l'élément à afficher
     * @param shape La forme de l'élément à afficher
     * @param color La couleur de l'élément à afficher
     * @param count Le nombre d'éléments à afficher
     */
    public LegendItemView(String name, Shape shape, Color color, int count) {
        this.renderer = new ComponentRenderer();
        this.shapeCanvas = new Canvas(CANVAS_SIZE, CANVAS_SIZE);
        this.nameLabel = new Label(name);
        this.countLabel = new Label("x" + count);

        drawShape(shape, color);
        setupLayout();
    }

    /**
     * Dessine la forme de l'élément sur le canvas avec la couleur spécifiée.
     *
     * @param shape La forme à dessiner
     * @param color La couleur à utiliser pour le dessin
     */
    private void drawShape(Shape shape, Color color) {
        GraphicsContext gc = shapeCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, CANVAS_SIZE, CANVAS_SIZE);
        double x = (CANVAS_SIZE - SHAPE_SIZE) / 2;
        double y = (CANVAS_SIZE - SHAPE_SIZE) / 2;
        renderer.renderComponent(gc, shape, color, x, y, SHAPE_SIZE);
    }

    /**
     * Configure la disposition des éléments dans le conteneur.
     */
    private void setupLayout() {
        setSpacing(ITEM_SPACING);
        setAlignment(Pos.CENTER_LEFT);
        nameLabel.setMinWidth(NAME_MIN_WIDTH);
        countLabel.setMinWidth(COUNT_MIN_WIDTH);
        getChildren().addAll(shapeCanvas, nameLabel, countLabel);
    }

    /**
     * Met à jour le compteur affiché dans l'élément de légende.
     *
     * @param count Le nouveau nombre à afficher
     */
    public void updateCount(int count) {
        countLabel.setText("x" + count);
    }
}
