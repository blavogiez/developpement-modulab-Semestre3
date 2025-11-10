package fr.univlille.labyrinth.model.maze.entities;

import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.MoveBehavior;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.PlayerMoveBehavior;

public class PlayerEntity extends Entity {
    public PlayerEntity(Position position) {
        this(position, new PlayerMoveBehavior());
    }

    public PlayerEntity(Position position, MoveBehavior moveBehavior) {
        super(position, moveBehavior);
    }

    public static Position getInitialPosition(ObservableMaze maze) {
        return maze.getEntryPosition();
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.PLAYER;
    }

    @Override
    public MoveBehavior getMoveBehavior() {
        return super.getMoveBehavior();
    }

    public boolean isPlayerPositionAtExit(Maze maze) {
        return this.position.equals(maze.getExitPosition());
    }

}
