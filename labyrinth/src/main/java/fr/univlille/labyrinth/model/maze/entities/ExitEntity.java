package fr.univlille.labyrinth.model.maze.entities;

import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.MoveBehavior;

public class ExitEntity extends Entity {
    public ExitEntity(Position position) {
        this(position,null);
    }

    public ExitEntity(Position position, MoveBehavior moveBehavior) {
        super(position, moveBehavior);
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.EXIT;
    }
}