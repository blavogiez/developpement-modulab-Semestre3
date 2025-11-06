package fr.univlille.labyrinth.model.maze.trap;

import fr.univlille.labyrinth.model.algorithm.Trap;
import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;

public class TrapManager {
    private ObservableMaze maze;
    private Trap[][] traps;

    public TrapManager(ObservableMaze maze){
        this.maze=maze;
        this.traps = TrapSetup.generate(maze,5);
    }


    public void trapEffect(Position oldPosition) {
        Trap trap = traps[maze.getPlayerPosition().getY()][maze.getPlayerPosition().getX()];
        switch (trap) {
            case PUSH -> {
                Direction direction = Direction.getDirection(oldPosition, maze.getPlayerPosition());
                boolean moving;
                do {
                    moving = maze.movePlayer(direction);
                } while (moving);
                revealTrap(oldPosition);
            }
            case FAKE -> {
                revealTrap(oldPosition);
            }
            case STUN ->  {
                //besoin des codes, execute PvETurn()
            }
            case TELEPORTER -> {

            }
        }
    }

    private void revealTrap(Position playerPosition) {
        traps[playerPosition.getY()][playerPosition.getX()] = Trap.USED;
    }


}
