package fr.univlille.labyrinth.view.labyrinth.filter;

import fr.univlille.labyrinth.model.maze.entities.Entity;
import fr.univlille.labyrinth.model.maze.traps.trap.Trap;

/*
 * Interface utilitaire pour décider de si l'on affiche ou non certains composants
 */
public interface RenderingFilter {

    boolean shouldRenderEntity(Entity entity, int x, int y);

    boolean shouldRenderTrap(Trap trap, int x, int y);

    boolean shouldDrawPlayer();
}
