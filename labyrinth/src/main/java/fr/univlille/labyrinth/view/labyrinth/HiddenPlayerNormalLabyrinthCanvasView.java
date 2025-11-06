package fr.univlille.labyrinth.view.labyrinth;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.entities.Entity;
import fr.univlille.labyrinth.model.maze.entities.EntityType;
import javafx.scene.canvas.GraphicsContext;

public class HiddenPlayerNormalLabyrinthCanvasView extends LabyrinthCanvasView {

    public HiddenPlayerNormalLabyrinthCanvasView(ObservableMaze maze) {
        super(maze);
    }

    @Override
    protected void dessinerElements(GraphicsContext gc, ObservableMaze maze, int lignes, int colonnes) {
        for (Entity entity : maze.getEntityManager().getEntities()) {
            if (entity.getEntityType() != EntityType.PLAYER) {
                entityRenderer.renderEntity(gc, entity, layout);
            }
        }
    }

    @Override
    protected boolean shouldRenderCell(int y, int x, ObservableMaze maze) {
        return true;
    }
}
