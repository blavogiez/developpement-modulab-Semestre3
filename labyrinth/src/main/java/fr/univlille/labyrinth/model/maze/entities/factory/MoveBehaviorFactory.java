package fr.univlille.labyrinth.model.maze.entities.factory;

import fr.univlille.labyrinth.model.exceptions.MoveBehaviorException;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.MonsterMoveBehavior;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.MoveBehavior;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.PlayerMoveBehavior;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.MovingStepBehavior;

/*
 * Factory simple de MoveBehavior
 */
public class MoveBehaviorFactory {

    /** 
     * @param name
     * @return MoveBehavior
     */
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
            } case "MONSTER" -> {
                yield new MonsterMoveBehavior();
            }
            default -> throw new MoveBehaviorException("MoveBehavior non supporte : " + name);
        };
    }
}
