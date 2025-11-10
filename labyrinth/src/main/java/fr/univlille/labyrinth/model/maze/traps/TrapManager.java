package fr.univlille.labyrinth.model.maze.traps;

import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.PlayerMoveBehavior;
import fr.univlille.labyrinth.model.maze.traps.trap.Trap;

public class TrapManager {
    private final ObservableMaze maze;
    private final Trap[][] trap;

    public TrapManager(ObservableMaze maze){
        this.maze=maze;
        this.trap = TrapSetup.generate(maze,0,5,10,3,0);

    }


    public void trapEffect(Position oldPosition) {
        Position newPosition = maze.getPlayerPosition();
        this.trap[newPosition.getY()][newPosition.getX()].onUse(newPosition, oldPosition, maze);
    }

    public Trap[][] getTraps() {
        return trap;
    }
}
