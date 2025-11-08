package fr.univlille.labyrinth.view.renderer;

import fr.univlille.labyrinth.model.maze.entities.Entity;
import fr.univlille.labyrinth.model.maze.entities.EntityType;
import fr.univlille.labyrinth.view.GameViewConfig;
import fr.univlille.labyrinth.view.Shape;
import fr.univlille.labyrinth.view.layout.LabyrinthLayout;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class EntityRenderer {
    private final ShapeRendererManager shapeManager;

    public EntityRenderer() {
        this.shapeManager = new ShapeRendererManager();
    }

    public void renderEntity(GraphicsContext gc, Entity entity, LabyrinthLayout layout) {
        if (entity == null || entity.getPosition() == null) {
            return;
        }

        EntityType type = entity.getEntityType();
        if (type == null) {
            return;
        }

        GameViewConfig config = GameViewConfig.valueOf(type.name());
        Shape shape = config.getShape();
        Color color = config.getColor();

        if (shape == null) {
            return;
        }

        int x = entity.getPosition().getX();
        int y = entity.getPosition().getY();

        double entitySize = layout.getCellSize() * 0.6;
        double entityX = layout.getOffsetX() + x * layout.getCellSize() + (layout.getCellSize() - entitySize) / 2;
        double entityY = layout.getOffsetY() + y * layout.getCellSize() + (layout.getCellSize() - entitySize) / 2;

        shapeManager.render(gc, shape, entityX, entityY, entitySize, color);
    }
}
