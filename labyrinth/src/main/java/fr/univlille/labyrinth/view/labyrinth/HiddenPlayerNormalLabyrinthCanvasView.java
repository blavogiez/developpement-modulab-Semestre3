package fr.univlille.labyrinth.view.labyrinth;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.entities.Entity;
import fr.univlille.labyrinth.model.maze.entities.EntityType;
import fr.univlille.labyrinth.model.maze.traps.TrapFactory;

public class HiddenPlayerNormalLabyrinthCanvasView extends LabyrinthCanvasView {

    public HiddenPlayerNormalLabyrinthCanvasView(ObservableMaze maze) {
        super(maze);
        this.playerAnimation.disable();
    }

    @Override
    protected boolean shouldDrawPlayer() {
        return false;
    }

    @Override
    protected boolean shouldRenderTrap(TrapFactory trapFactory, int x, int y) {
        return trapFactory.name().equals("FAKE");
    }

    @Override
    protected boolean shouldRenderEntity(Entity entity) {
        return entity.getEntityType() == EntityType.EXIT;
    }
}
