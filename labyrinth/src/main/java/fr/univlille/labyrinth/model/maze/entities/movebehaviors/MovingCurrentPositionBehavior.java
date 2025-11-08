package fr.univlille.labyrinth.model.maze.entities.movebehaviors;

import java.util.List;
import java.util.Random;

import fr.univlille.labyrinth.model.algorithm.pathsearch.BreadthFirstSearch;
import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.Entity;

/*
 * Déplace l'entité de pas Distance actuel à sortie dans le labyrinthe (modifie sa position)
 */
public class MovingCurrentPositionBehavior implements MoveBehavior {
    private Random random;

    public MovingCurrentPositionBehavior() {
    }

    @Override
    public void move(Entity entity, Direction direction, Maze maze) {
            movingExitByCurrentDistance(entity,maze);
    }

    /*
     * Déplace l'entité de pas Distance actuel  à sortie dans le labyrinthe (modifie sa position)
     */
    public void movingExitByCurrentDistance(Entity entity, Maze maze) {
        int currentDistanceBetweenPlayerAndExit = BreadthFirstSearch.calculateDistance(maze, entity.getPosition(),maze.getExitPosition());
        List<Position> candidates = BreadthFirstSearch.calculateAllDistances(maze, entity.getPosition(),currentDistanceBetweenPlayerAndExit).positions();
        Position exitPosition = candidates.get(random.nextInt(candidates.size()));
        maze.setExit(exitPosition);
    }
}