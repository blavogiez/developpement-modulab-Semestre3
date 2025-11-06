package fr.univlille.labyrinth.model.maze.entities;

import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.MoveBehavior;

public abstract class Entity {
    protected Position position ;
    protected MoveBehavior moveBehavior ;

    public Entity(Position position, MoveBehavior moveBehavior) {
        this.position=position;
        this.moveBehavior=moveBehavior;
    }

    public abstract EntityType getEntityType();

    public Position getPosition() {
        return position;
    }

    public MoveBehavior getMoveBehavior() {
        return moveBehavior;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean move(Maze maze, Direction direction) {
        if(moveBehavior!=null) {
            moveBehavior.move(this,direction,maze);
            return true ;
        } return false ;
    }
}
