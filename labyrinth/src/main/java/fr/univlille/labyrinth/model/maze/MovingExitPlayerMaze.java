package fr.univlille.labyrinth.model.maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.univlille.labyrinth.parcours.BreadthFirstSearch;

public class MovingExitPlayerMaze extends PlayerMaze {

    private final int MOVING_STEP = 1 ;

    public MovingExitPlayerMaze(int width, int height, int distanceBetweenEntryAndExit) {
        super(width, height, distanceBetweenEntryAndExit);
    }

    @Override
    public void movePlayer(Direction direction){
        super.movePlayer(direction);
        movingExit();
    }

    public void movingExit() {
        Maze maze = this;
        this.entryPosition=this.getPlayerPosition();
        //utilisation d'un methode pour calculer une distance entre deux cellule
        List<Position> candidates = BreadthFirstSearch.calculateAllDistances(this, this.getPlayerPosition(),this.distanceBetweenEntryAndExit);
        Random random = new Random();
        Position exitPosition = candidates.get(random.nextInt(candidates.size()));
        maze.setExit(exitPosition);
    }

}
