package fr.univlille.labyrinth.model.maze.entities;

import fr.univlille.labyrinth.model.algorithm.pathsearch.BreadthFirstSearch;
import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.MoveBehavior;
import javafx.geometry.Pos;

import java.util.List;

public class MonsterEntity extends Entity {
    protected Position position;
    protected MoveBehavior moveBehavior ;

    public MonsterEntity(Position position, MoveBehavior moveBehavior) {
        super(new Position(5,5),moveBehavior);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    /*
     * Retourne true si l'action s'est bien déroulée.
     */
    public boolean move(Maze maze, Direction direction, List<Entity> otherEntities) {
        Position playerPosition = PlayerEntity.getPlayerEntity(otherEntities).getPosition();
        List<Position> path = BreadthFirstSearch.pathFinder(maze,position,playerPosition);
        if(!path.isEmpty()) {
            System.out.println(position);
            position = path.get(0);
            return true ;
        } return false ;


    }
}
