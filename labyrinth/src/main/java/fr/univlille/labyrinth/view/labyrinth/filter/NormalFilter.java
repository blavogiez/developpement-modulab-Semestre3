package fr.univlille.labyrinth.view.labyrinth.filter;

import fr.univlille.labyrinth.model.maze.entities.Entity;
import fr.univlille.labyrinth.model.maze.entities.EntityType;
import fr.univlille.labyrinth.model.maze.traps.trap.Trap;

public class NormalFilter implements RenderingFilter {

    private final boolean animationEnabled;

    public NormalFilter(boolean animationEnabled) {
        this.animationEnabled = animationEnabled;
    }

    /** 
     * @param entity
     * @param x
     * @param y
     * @return {@code true} si la condition est remplie, sinon {@code false}
     */
    @Override
    public boolean shouldRenderEntity(Entity entity, int x, int y) {
        if (entity.getEntityType() == EntityType.PLAYER) {
            return !animationEnabled;
        }
        return true;
    }

    /** 
     * @param trap
     * @param x
     * @param y
     * @return {@code true} si la condition est remplie, sinon {@code false}
     */
    @Override
    public boolean shouldRenderTrap(Trap trap, int x, int y) {
        return true;
    }

    /** 
     * @return {@code true} si la condition est remplie, sinon {@code false}
     */
    @Override
    public boolean shouldDrawPlayer() {
        return animationEnabled;
    }
}
