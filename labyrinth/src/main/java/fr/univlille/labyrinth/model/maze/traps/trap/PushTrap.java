package fr.univlille.labyrinth.model.maze.traps.trap;

import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.PlayerMoveBehavior;

public class PushTrap extends Trap {
    /** 
     * @param playerID
     * @param position
     * @param oldPosition
     * @param maze
     */
    @Override
    public void onUse(int playerID, Position position, Position oldPosition, ObservableMaze maze) {
                revealTrap(position, maze.getTrapManager().getTraps());
                Direction direction = Direction.getDirection(oldPosition,position);
                do {
                    maze.getEntityManager().getPlayerEntityByID(playerID).getMoveBehavior().move(maze.getEntityManager().getPlayerEntityByID(playerID),direction,maze);
                } while (((PlayerMoveBehavior) maze.getEntityManager().getPlayerEntityByID(playerID).getMoveBehavior()).isMoving());
    }

    /** 
     * @return String
     */
    @Override
    public String name() {
        return "TRAP_PUSH";
    }
}
