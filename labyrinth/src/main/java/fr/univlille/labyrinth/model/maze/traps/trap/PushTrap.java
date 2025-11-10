package fr.univlille.labyrinth.model.maze.traps.trap;

import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.PlayerMoveBehavior;

public class PushTrap extends Trap {
    @Override
    public void onUse(Position position, Position oldPosition, ObservableMaze maze) {
                revealTrap(position, maze.getTrapManager().getTraps());
                Direction direction = Direction.getDirection(oldPosition,position);
                do {
                    maze.getEntityManager().getPlayerEntity().getMoveBehavior().move(maze.getEntityManager().getPlayerEntity(),direction,maze);
                } while (((PlayerMoveBehavior) maze.getEntityManager().getPlayerEntity().getMoveBehavior()).isMoving());
    }

    @Override
    public String name() {
        return "TRAP_PUSH";
    }
}
