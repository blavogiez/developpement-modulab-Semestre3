package fr.univlille.labyrinth.model.maze.traps.trap;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.PlayerEntity;

public class LavaTrap extends Trap {
    /** 
     * @param playerID
     * @param position
     * @param oldPosition
     * @param maze
     */
    @Override
    public void onUse(int playerID, Position position, Position oldPosition, ObservableMaze maze) {
        PlayerEntity player = maze.getEntityManager().getPlayerEntityByID(playerID);
        maze.getEntityManager().kill(player);
    }

    /** 
     * @return String
     */
    @Override
    public String name() {
        return "TRAP_LAVA";
    }
}
