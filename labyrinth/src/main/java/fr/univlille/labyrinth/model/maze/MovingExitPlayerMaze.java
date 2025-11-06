package fr.univlille.labyrinth.model.maze;

import java.util.List;
import java.util.Random;

import fr.univlille.labyrinth.model.algorithm.pathsearch.BreadthFirstSearch;

public class MovingExitPlayerMaze extends PlayerMaze {
    private final int MOVING_PERCENTAGE = 5 ;
    private final int MOVING_STEP = 1 ;
    private Random random ;

    public MovingExitPlayerMaze(int width, int height, int distanceBetweenEntryAndExit) {
        super(width, height, distanceBetweenEntryAndExit);
        this.random = new Random();
    }

    @Override
    public boolean movePlayer(Direction direction){
        if(random.nextInt(MOVING_PERCENTAGE)==1){
            movingExitByStep();
        }
        return super.movePlayer(direction);
    }

    public void movingExitByCurrentDistance() {
        PlayerMaze maze = this;
        this.entryPosition=this.getPlayerPosition();
        //utilisation d'un methode pour calculer une distance entre deux cellule
        int currentDistanceBetweenPlayerAndExit = BreadthFirstSearch.calculateDistance(maze, maze.getPlayerPosition(), maze.getExitPosition());
        List<Position> candidates = BreadthFirstSearch.calculateAllDistances(this, this.getPlayerPosition(),currentDistanceBetweenPlayerAndExit);
        Position exitPosition = candidates.get(random.nextInt(candidates.size()));
        System.out.println("Position exit : " + exitPosition);
        maze.setExit(exitPosition);
    }

    public void movingExitByStep() {
        PlayerMaze maze = this;
        this.entryPosition=this.getPlayerPosition();
        List<Position> candidates = BreadthFirstSearch.calculateAllDistances(this, this.getExitPosition(),MOVING_STEP);
        Position exitPosition = candidates.get(random.nextInt(candidates.size()));
        System.out.println("Position exit : " + exitPosition);
        maze.setExit(exitPosition);
    }
}
