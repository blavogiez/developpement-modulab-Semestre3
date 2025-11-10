package fr.univlille.labyrinth.model.maze.trap;

import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.PlayerEntity;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.PlayerMoveBehavior;

public class TrapManager {
    private final ObservableMaze maze;
    private final Trap[][] traps;

    public TrapManager(ObservableMaze maze){
        this.maze=maze;
        this.traps = TrapSetup.generate(maze,0,5,10,3,0);
    }


    public void trapEffect(int playerID, Position oldPosition) {
        PlayerEntity player = maze.getEntityManager().getPlayerEntityByID(playerID);
        if (player == null) return;

        Position newPosition = player.getPosition();
        Trap trap = traps[newPosition.getY()][newPosition.getX()];

        switch (trap) {
            case PUSH -> {
                revealTrap(newPosition);
                Direction direction = Direction.getDirection(oldPosition, newPosition);
                do {
                    player.getMoveBehavior().move(player, direction, maze);
                } while (((PlayerMoveBehavior) player.getMoveBehavior()).isMoving());
            }
            case FAKE -> {
                revealTrap(newPosition);
            }
            case STUN ->  {
                //besoin des codes, execute PvETurn()
            }
            case TELEPORTER -> {
                player.setPosition(TrapSetup.getRandomCell(maze));
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
