package fr.univlille.labyrinth.view.renderer;

import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.Entity;
import fr.univlille.labyrinth.view.EntityShapeMapper;
import fr.univlille.labyrinth.view.layout.LabyrinthLayout;
import javafx.scene.canvas.GraphicsContext;

public class LocalEntityRenderer extends EntityRenderer {
    private static final int VIEW_RADIUS = 5;

    public LocalEntityRenderer(EntityShapeMapper shapeMapper) {
        super(shapeMapper);
    }

    public void renderEntityLocal(GraphicsContext gc, Entity entity, LabyrinthLayout layout, Position playerPos) {
        if (entity == null || entity.getPosition() == null) {
            return;
        }

        Position pos = entity.getPosition();
        int dx = Math.abs(pos.getX() - playerPos.getX());
        int dy = Math.abs(pos.getY() - playerPos.getY());

        if (Math.max(dx, dy) <= VIEW_RADIUS) {
            int localX = pos.getX() - playerPos.getX() + VIEW_RADIUS;
            int localY = pos.getY() - playerPos.getY() + VIEW_RADIUS;

            LabyrinthLayout localLayout = new LabyrinthLayout(
                layout.getCellSize(),
                layout.getOffsetX() - (playerPos.getX() - VIEW_RADIUS) * layout.getCellSize(),
                layout.getOffsetY() - (playerPos.getY() - VIEW_RADIUS) * layout.getCellSize(),
                layout.getWallThickness()
            );

            renderEntity(gc, entity, localLayout);
        }
    }
}
