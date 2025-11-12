package fr.univlille.labyrinth.model.maze.traps;

import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.PlayerEntity;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.PlayerMoveBehavior;
import fr.univlille.labyrinth.model.maze.traps.trap.Trap;

public class TrapManager {
    private final ObservableMaze maze;
    private final Trap[][] trap;

    public TrapManager(ObservableMaze maze){
        this.maze=maze;
        this.trap = TrapSetup.generate(maze,0,5,10,3,0);

    }


    /** 
     * @param playerID
     * @param oldPosition
     */
    public void trapEffect(int playerID, Position oldPosition) {
        PlayerEntity player = maze.getEntityManager().getPlayerEntityByID(playerID);
        if (player == null) return;
        Position newPosition = player.getPosition();
        this.trap[newPosition.getY()][newPosition.getX()].onUse(playerID, newPosition, oldPosition, maze);
    }

    /** 
     * @return Trap[][]
     */
    public Trap[][] getTraps() {
        return trap;
    }
}
