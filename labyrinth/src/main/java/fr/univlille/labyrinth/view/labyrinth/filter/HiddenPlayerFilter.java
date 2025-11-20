package fr.univlille.labyrinth.view.labyrinth.filter;

import fr.univlille.labyrinth.model.maze.entities.Entity;
import fr.univlille.labyrinth.model.maze.entities.EntityType;
import fr.univlille.labyrinth.model.maze.traps.trap.FakeTrap;
import fr.univlille.labyrinth.model.maze.traps.trap.Trap;

public class HiddenPlayerFilter implements RenderingFilter {

    /** 
     * @param entity
     * @param x
     * @param y
     * @return {@code true} si la condition est remplie, sinon {@code false}
     */
    @Override
    public boolean shouldRenderEntity(Entity entity, int x, int y) {
        return entity.getEntityType() == EntityType.EXIT;
    }

    /** 
     * @param trap
     * @param x
     * @param y
     * @return {@code true} si la condition est remplie, sinon {@code false}
     */
    @Override
    public boolean shouldRenderTrap(Trap trap, int x, int y) {
        return trap instanceof FakeTrap;
    }

    /** 
     * @return {@code true} si la condition est remplie, sinon {@code false}
     */
    @Override
    public boolean shouldDrawPlayer() {
        return false;
    }
}
