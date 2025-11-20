package fr.univlille.labyrinth.model.maze.traps.trap;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.ExitEntity;

public class TeleportExitTrap extends Trap {
    /** 
     * @param playerID
     * @param position
     * @param oldPosition
     * @param maze
     */
    @Override
    public void onUse(int playerID, Position position, Position oldPosition, ObservableMaze maze) {
        maze.getEntityManager().getEntitiesByType(ExitEntity.class).forEach(x ->
            x.setPosition(getRandomCell(maze))
        );
        revealTrap(position,maze.getTrapManager());
    }

    /** 
     * @return String
     */
    @Override
    public String name() {
        return "TRAP_TELEPORT_EXIT";
    }
}
