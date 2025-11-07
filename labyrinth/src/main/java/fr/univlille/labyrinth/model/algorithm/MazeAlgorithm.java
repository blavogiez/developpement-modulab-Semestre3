package fr.univlille.labyrinth.model.algorithm;

import java.io.Serializable;
import java.util.Arrays;

import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.Position;
import javafx.geometry.Pos;


public abstract class MazeAlgorithm{
    boolean[][] verticalsWalls, horizontalsWalls;

    public abstract void generateMaze(Maze maze);

    protected static void allAreTrue(boolean[][] tab) {
        for (boolean[] booleans : tab) {
            Arrays.fill(booleans, true);
        }
    }

    protected void removeWall(Position start, Direction direction){
        removeWall(start,start.add(direction.getY(),direction.getX()),direction);
    }

    protected void removeWall(Position start, Position next){
        removeWall(start,next,start.diff(next));
    }



    protected void removeWall(Position start, Position next, Direction direction){
        Position min = start.min(next);
        switch (direction) {
            case LEFT, RIGHT -> {
                verticalsWalls[min.getX()][min.getY()] = false;
            }
            case UP, DOWN -> {
                horizontalsWalls[min.getY()][min.getX()] = false;
            }
        }
    }
}
