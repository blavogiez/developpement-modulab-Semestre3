package fr.univlille.labyrinth.model.maze.entities.factory;

import fr.univlille.labyrinth.model.maze.entities.movebehaviors.MoveBehavior;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.PlayerMoveBehavior;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.MovingStepBehavior;

/*
 * Factory simple de MoveBehavior
 */
public class MoveBehaviorFactory {

    /*
     * pour info : yield = return en switch
     */
    public static MoveBehavior create(String name) {
        if (name == null || name.equalsIgnoreCase("DEFAULT")) {
            return null;
        }

        return switch (name.toUpperCase()) {
            case "PLAYER" -> {
                yield new PlayerMoveBehavior();
            }
            case "MOVING" -> {
                yield new MovingStepBehavior();
            }
            default -> throw new IllegalArgumentException("MoveBehavior non supporte : " + name);
        };
    }
}
