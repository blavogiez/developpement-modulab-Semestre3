package fr.univlille.labyrinth.model.maze.entities;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.MonsterMoveBehavior;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.MoveBehavior;

public class MonsterEntity extends Entity {
    public MonsterEntity(Position position) {
        this(position, new MonsterMoveBehavior());
    }

    public MonsterEntity(Position position, MoveBehavior moveBehavior) {
        super(position, moveBehavior);
    }

    public static Position getInitialPosition(ObservableMaze maze) {
        return maze.getExitPosition();
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
