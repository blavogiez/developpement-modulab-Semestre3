package fr.univlille.labyrinth.view.labyrinth.filter;

import fr.univlille.labyrinth.model.maze.entities.Entity;
import fr.univlille.labyrinth.model.maze.entities.EntityType;
import fr.univlille.labyrinth.model.maze.traps.trap.Trap;

public class NormalFilter implements RenderingFilter {

    private final boolean animationEnabled;

    public NormalFilter(boolean animationEnabled) {
        this.animationEnabled = animationEnabled;
    }

    @Override
    public boolean shouldRenderEntity(Entity entity, int x, int y) {
        if (entity.getEntityType() == EntityType.PLAYER) {
            return !animationEnabled;
        }
        return true;
    }

    @Override
    public boolean shouldRenderTrap(Trap trap, int x, int y) {
        return true;
    }

    @Override
    public boolean shouldDrawPlayer() {
        return animationEnabled;
    }
}
