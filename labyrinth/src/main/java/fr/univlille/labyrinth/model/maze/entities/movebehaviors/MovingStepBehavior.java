package fr.univlille.labyrinth.model.maze.entities.movebehaviors;

import java.util.List;
import java.util.Random;

import fr.univlille.labyrinth.model.algorithm.pathsearch.MazeDistance;
import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.Entity;

/*
 * Déplace l'entité de pas MOVING_STEP dans le labyrinthe (modifie sa position) avec une probabilité de 1/MOVING_PERCENTAGE
 */
public class MovingStepBehavior implements MoveBehavior {
    private static final int MOVING_PERCENTAGE = 5;
    private static final int MOVING_STEP = 1;
    private static final Random random = new Random();

    public MovingStepBehavior() {
        /* Nothing to setup since all is final and static */
    }

    /** 
     * @param entity
     * @param direction
     * @param maze
     */
    @Override
    public void move(Entity entity, Direction direction, ObservableMaze maze) {
        if (random.nextInt(MOVING_PERCENTAGE) == 1) {
            movingExitByStep(entity, maze);
        }
    }

    /** 
     * @param entity
     * @param maze
     */
    /*
     * Déplace l'entité de pas MOVING_STEP dans le labyrinthe (modifie sa position)
     */
    private void movingExitByStep(Entity entity, Maze maze) {
        List<Position> candidates = MazeDistance.calculateAllDistances(maze, entity.getPosition(), MOVING_STEP).positions();

        if (!candidates.isEmpty()) {
            Position newExitPosition = candidates.get(random.nextInt(candidates.size()));
            entity.setPosition(newExitPosition);
        }
    }
}