package fr.univlille.labyrinth.model.maze.entities;

import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.MoveBehavior;

public class Entity {
    protected Position position ;
    protected MoveBehavior moveBehavior ;

    public Entity(Position position, MoveBehavior moveBehavior) {
        this.position=position;
        this.moveBehavior=moveBehavior;
    }

    public Position getPosition() {
        return position;
    }

    public MoveBehavior getMoveBehavior() {
        return moveBehavior;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void move(Maze maze) {
        if(moveBehavior!=null) moveBehavior.move(this,maze);
    }
}
