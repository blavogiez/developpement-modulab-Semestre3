package fr.univlille.labyrinth.view.renderer;

import fr.univlille.labyrinth.model.maze.entities.Entity;
import fr.univlille.labyrinth.model.maze.entities.EntityType;
import fr.univlille.labyrinth.view.EntityShape;
import fr.univlille.labyrinth.view.EntityShapeMapper;
import fr.univlille.labyrinth.view.layout.LabyrinthLayout;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

public class EntityRenderer {
    private final EntityShapeMapper shapeMapper;
    private final Map<EntityShape, ShapeRenderer> renderers;

    public EntityRenderer(EntityShapeMapper shapeMapper) {
        this.shapeMapper = shapeMapper;
        this.renderers = new HashMap<>();
        this.renderers.put(EntityShape.CIRCLE, new CircleRenderer());
        this.renderers.put(EntityShape.SQUARE, new SquareRenderer());
        this.renderers.put(EntityShape.TRIANGLE, new TriangleRenderer());
    }

    public void renderEntity(GraphicsContext gc, Entity entity, LabyrinthLayout layout) {
        if (entity == null || entity.getPosition() == null) {
            return;
        }

        EntityType type = entity.getEntityType();
        if (type == null) {
            return;
        }

        EntityShape shape = shapeMapper.getShape(type);
        Color color = shapeMapper.getColor(type);
        ShapeRenderer renderer = renderers.get(shape);

        if (renderer == null) {
            return;
        }

        int x = entity.getPosition().getX();
        int y = entity.getPosition().getY();

        double entitySize = layout.getCellSize() * 0.6;
        double entityX = layout.getOffsetX() + x * layout.getCellSize() + (layout.getCellSize() - entitySize) / 2;
        double entityY = layout.getOffsetY() + y * layout.getCellSize() + (layout.getCellSize() - entitySize) / 2;

        renderer.render(gc, entityX, entityY, entitySize, color);
    }
}
