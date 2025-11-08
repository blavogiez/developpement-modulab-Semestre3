package fr.univlille.labyrinth.model.maze;

import java.util.List;
import java.util.Random;

import fr.univlille.labyrinth.model.algorithm.pathsearch.BreadthFirstSearch;

public class MovingExitObservableMaze extends ObservableMaze {
    private final int MOVING_PERCENTAGE = 5;
    private final int MOVING_STEP = 1;
    private Random random;
    private boolean withStep;

    public MovingExitObservableMaze(int width, int height, int distanceBetweenEntryAndExit, boolean withStep) {
        super(width, height, distanceBetweenEntryAndExit);
        this.random = new Random();
        this.withStep = withStep;
    }

    @Override
    public boolean movePlayer(Direction direction) {
        if (this.withStep) {
            if (random.nextInt(MOVING_PERCENTAGE) == 1) {
                movingExitByStep();
            }
            return super.movePlayer(direction);
        } else {
            movingExitByCurrentDistance();
        }
        return super.movePlayer(direction);
    }

    public void movingExitByCurrentDistance() {
        ObservableMaze maze = this;
        this.entryPosition=this.getPlayerPosition();
        int currentDistanceBetweenPlayerAndExit = BreadthFirstSearch.calculateDistance(maze, maze.getPlayerPosition(), maze.getExitPosition());
        List<Position> candidates = BreadthFirstSearch.calculateAllDistances(this, this.getPlayerPosition(),currentDistanceBetweenPlayerAndExit).positions();
        Position exitPosition = candidates.get(random.nextInt(candidates.size()));
        maze.setExit(exitPosition);
    }

    public void movingExitByStep() {
        ObservableMaze maze = this;
        this.entryPosition=this.getPlayerPosition();
        List<Position> candidates = BreadthFirstSearch.calculateAllDistances(this, this.getExitPosition(),MOVING_STEP).positions();
        Position exitPosition = candidates.get(random.nextInt(candidates.size()));
        maze.setExit(exitPosition);
    }
}
