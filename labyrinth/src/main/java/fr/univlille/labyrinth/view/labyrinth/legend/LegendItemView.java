package fr.univlille.labyrinth.view.labyrinth.legend;

import fr.univlille.labyrinth.view.Shape;
import fr.univlille.labyrinth.view.renderer.ComponentRenderer;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/*
Petit dessin de la forme associée à l'item pour représenter un composant de labyrinthe
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

    public LegendItemView(String name, Shape shape, Color color, int count) {
        this.renderer = new ComponentRenderer();
        this.shapeCanvas = new Canvas(CANVAS_SIZE, CANVAS_SIZE);
        this.nameLabel = new Label(name);
        this.countLabel = new Label("x" + count);

        drawShape(shape, color);
        setupLayout();
    }

    private void drawShape(Shape shape, Color color) {
        GraphicsContext gc = shapeCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, CANVAS_SIZE, CANVAS_SIZE);
        double x = (CANVAS_SIZE - SHAPE_SIZE) / 2;
        double y = (CANVAS_SIZE - SHAPE_SIZE) / 2;
        renderer.renderComponent(gc, shape, color, x, y, SHAPE_SIZE);
    }

    private void setupLayout() {
        setSpacing(ITEM_SPACING);
        setAlignment(Pos.CENTER_LEFT);
        nameLabel.setMinWidth(NAME_MIN_WIDTH);
        countLabel.setMinWidth(COUNT_MIN_WIDTH);
        getChildren().addAll(shapeCanvas, nameLabel, countLabel);
    }

    public void updateCount(int count) {
        countLabel.setText("x" + count);
    }
}
