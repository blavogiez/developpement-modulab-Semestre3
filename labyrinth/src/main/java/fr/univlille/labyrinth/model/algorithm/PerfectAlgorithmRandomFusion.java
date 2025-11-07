package fr.univlille.labyrinth.model.algorithm;

import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.Position;
import javafx.geometry.Pos;

public class PerfectAlgorithmRandomFusion extends MazeAlgorithm {

    int[][] grid;

    @Override
    public void generateMaze(Maze maze) {
        int height = maze.getHeight();
        int width = maze.getWidth();
        verticalsWalls = maze.getMurVerticaux();
        horizontalsWalls = maze.getMurHorizontaux();
        grid = generateNumberGrid(maze.getHeight(), maze.getWidth());
        do {
           Direction direction = Direction.getRandomDirection();
           Position position = Position.getRandomPosition(width,height);
           fusionPosition(position,direction);
        } while (!isAllTheSameNumber());

    }

    int[][] generateNumberGrid(int height, int width){
        return null;
    }

    void fusionPosition(Position position, Direction direction){
        Position next = position.add(direction.getX(),direction.getY());
        removeWall(position,direction);
    }



    void transformAllNumber(int oldint, int newint){
        for (int i = 0; i<grid.length;i++){
            for (int j = 0; j<grid[i].length;j++){
                if (grid[i][j]==oldint) grid[i][j]=newint;
            }
        }
    }


    boolean isAllTheSameNumber(){
        return false;
    }

    private static PerfectAlgorithmRandomFusion instance;

    public static PerfectAlgorithmRandomFusion getInstance(){
        if (instance==null){
            instance = new PerfectAlgorithmRandomFusion();
        }
        return instance;
    }



}
