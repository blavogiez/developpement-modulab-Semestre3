package fr.univlille.labyrinth.model.maze.traps.trap;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.PlayerMoveBehavior;

public class StunTrap extends Trap {
    /**
     * @param playerID
     * @param position
     * @param oldPosition
     * @param maze
     */
    @Override
    public void onUse(int playerID, Position position, Position oldPosition, ObservableMaze maze) {
        final int STUNT_DURATION = 5;
        ((PlayerMoveBehavior) maze.getEntityManager().getPlayerEntityByID(playerID).getMoveBehavior()).setStuntDuration(STUNT_DURATION);
        revealTrap(position,maze.getTrapManager());
    }

    /** 
     * @return String
     */
    @Override
    public String name() {
        return "TRAP_STUN";
    }
}
