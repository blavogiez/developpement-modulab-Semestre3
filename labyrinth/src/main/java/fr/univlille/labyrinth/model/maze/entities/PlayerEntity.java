package fr.univlille.labyrinth.model.maze.entities;

import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.MoveBehavior;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.PlayerMoveBehavior;
import java.util.List;

public class PlayerEntity extends Entity {
    public PlayerEntity() {
        super(new Position(0, 0), new PlayerMoveBehavior());
    }

    public PlayerEntity(Position position) {
        super(position, new PlayerMoveBehavior());
    }

    public PlayerEntity(Position position, MoveBehavior moveBehavior) {
        super(position, moveBehavior);
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

    @Override
    public boolean move(Maze maze, Direction direction, List<Entity> otherEntities) {
        if (!maze.isWall(position.getY(),position.getX(),position.getY()+direction.getY(),position.getX()+direction.getX())) {
            position.addX(direction.getX());
            position.addY(direction.getY());
            return true ;
        }
        return false ;
    }

    public static PlayerEntity getPlayerEntity(List<Entity> entities) {
        for (Entity e : entities) {
            if (e.getEntityType()==EntityType.PLAYER) return (PlayerEntity) e ;
        }
        return null ;
    }
}
