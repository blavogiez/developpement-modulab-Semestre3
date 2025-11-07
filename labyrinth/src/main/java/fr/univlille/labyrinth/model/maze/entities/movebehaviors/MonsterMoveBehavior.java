package fr.univlille.labyrinth.model.maze.entities.movebehaviors;

import fr.univlille.labyrinth.model.algorithm.pathsearch.BreadthFirstSearch;
import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.Entity;

import java.util.List;

public class MonsterMoveBehavior {
    public void move(Entity entity, Direction direction, Maze maze) {
        Position position = entity.getPosition();
        Position playerPosition = ((ObservableMaze)maze).getPlayerPosition();
        List<Position> path = BreadthFirstSearch.pathFinder(maze,position,playerPosition);
        if(!path.isEmpty()) {
            System.out.println(position);
            entity.setPosition(path.get(0));
        }
    }

    public boolean isMoving() {
        return true;
    }
}
