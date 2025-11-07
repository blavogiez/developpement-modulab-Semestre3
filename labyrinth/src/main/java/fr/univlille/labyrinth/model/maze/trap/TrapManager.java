package fr.univlille.labyrinth.model.maze.trap;

import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.PlayerMoveBehavior;

public class TrapManager {
    private final ObservableMaze maze;
    private final Trap[][] traps;

    public TrapManager(ObservableMaze maze){
        this.maze=maze;
        this.traps = TrapSetup.generate(maze,0,5,10,3,0);
    }


    public void trapEffect(Position oldPosition) {
        Position newPosition = maze.getPlayerPosition();
        Trap trap = traps[newPosition.getY()][newPosition.getX()];
        switch (trap) {
            case PUSH -> {
                revealTrap(newPosition);
                Direction direction = Direction.getDirection(oldPosition,newPosition);

                do {
                    maze.getEntityManager().getPlayerEntity().getMoveBehavior().move(maze.getEntityManager().getPlayerEntity(),direction,maze);
                } while (((PlayerMoveBehavior) maze.getEntityManager().getPlayerEntity().getMoveBehavior()).isMoving());


            }
            case FAKE -> {
                revealTrap(newPosition);
            }
            case STUN ->  {
                //besoin des codes, execute PvETurn()
            }
            case TELEPORTER -> {

                maze.setPlayerPosition(TrapSetup.getRandomCell(maze));

            }
        }
    }

    private void revealTrap(Position playerPosition) {
        traps[playerPosition.getY()][playerPosition.getX()] = Trap.USED;
    }

    public Trap[][] getTraps() {
        return traps;
    }
}
