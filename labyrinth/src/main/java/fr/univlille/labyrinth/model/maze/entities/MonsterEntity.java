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

    /** 
     * @param maze
     * @return Position
     */
    public static Position getInitialPosition(ObservableMaze maze) {
        return maze.getExitPosition();
    }

    /** 
     * @return EntityType
     */
    @Override
    public EntityType getEntityType() {
        return EntityType.MONSTER;
    }

    /** 
     * @return MoveBehavior
     */
    @Override
    public MoveBehavior getMoveBehavior() {
        return super.getMoveBehavior();
    }

}
