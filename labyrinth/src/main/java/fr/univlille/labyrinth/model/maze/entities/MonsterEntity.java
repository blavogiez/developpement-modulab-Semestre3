package fr.univlille.labyrinth.model.maze.entities;

import fr.univlille.labyrinth.model.algorithm.pathsearch.BreadthFirstSearch;
import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.MoveBehavior;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.PlayerMoveBehavior;

import java.util.List;

public class MonsterEntity extends Entity {
    protected Position position;
    public MonsterEntity() {
        super(new Position(2, 2), new PlayerMoveBehavior());
    }

    public MonsterEntity(Position position) {
        super(position, new PlayerMoveBehavior());
    }

    public MonsterEntity(Position position, MoveBehavior moveBehavior) {
        super(position, moveBehavior);
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.MONSTER;
    }

    @Override
    public MoveBehavior getMoveBehavior() {
        return super.getMoveBehavior();
    }

}
