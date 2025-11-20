package fr.univlille.labyrinth.model.maze.entities.factory;


import fr.univlille.labyrinth.controller.freemode.MazeThreatConfiguration;
import fr.univlille.labyrinth.model.maze.entities.EntityType;

public class EntityConfiguration implements MazeThreatConfiguration {
    EntityType type;
    int quantity;
    String moveBehaviorName;

    public EntityConfiguration(EntityType type, int quantity, String behavior) {
        this.type=type;
        this.quantity=quantity;
        this.moveBehaviorName=behavior;
    }

    @Override
    public String type() {
        return type.name();
    }

    @Override
    public int quantity() {
        return quantity;
    }


    public EntityType getType() {
        return type;
    }

    public String moveBehaviorName() {
        return moveBehaviorName;
    }
}
