package fr.univlille.labyrinth.model.maze.entities.movebehaviors;

import java.util.List;
import java.util.Random;

import fr.univlille.labyrinth.model.algorithm.pathsearch.BreadthFirstSearch;
import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.Entity;

/*
 * Déplace l'entité de pas MOVING_STEP dans le labyrinthe (modifie sa position) avec une probabilité de 1/MOVING_PERCENTAGE
 */
public class MovingStepBehavior implements MoveBehavior {
    private final int MOVING_PERCENTAGE = 5;
    private final int MOVING_STEP = 1;
    private Random random;

    public MovingStepBehavior() {
        this.random = new Random();
    }

    @Override
    public void move(Entity entity, Direction direction, Maze maze) {
        if (random.nextInt(MOVING_PERCENTAGE) == 1) {
            movingExitByStep(entity, maze);
        }
    }

    /*
     * Déplace l'entité de pas MOVING_STEP dans le labyrinthe (modifie sa position)
     */
    private void movingExitByStep(Entity entity, Maze maze) {
        List<Position> candidates = BreadthFirstSearch.calculateAllDistances(
            maze, entity.getPosition(), MOVING_STEP);
        
        if (!candidates.isEmpty()) {
            Position newExitPosition = candidates.get(random.nextInt(candidates.size()));
            entity.setPosition(newExitPosition);
        }
    }
}