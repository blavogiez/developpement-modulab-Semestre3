package fr.univlille.labyrinth.model.algorithm;

import fr.univlille.labyrinth.model.Position;

import java.util.Random;

public abstract class MazeAlgorithmTemplate {
    protected boolean[][] maze;
    protected double percentageWall;
    protected final boolean PATH = true, WALL = false;
    protected final Random random = new Random();



    protected void markCell(int x, int y){
        maze[x][y]=PATH;
    }
    protected void markCell(CellAlgorithmBoolean cellAlgorithmBoolean){
        maze[cellAlgorithmBoolean.x][cellAlgorithmBoolean.y]=PATH;

    }

    protected void markPathBetweenCell(int x1, int y1, int x2, int y2){
        markCell(x1,y1);
        markCell(x2,y2);
        markCell((x1+x2)/2,(y1+y2)/2);
    }

    protected void markPathBetweenCell(CellAlgorithmBoolean cellAlgorithmBoolean1, CellAlgorithmBoolean cellAlgorithmBoolean2){
        markPathBetweenCell(cellAlgorithmBoolean1.x, cellAlgorithmBoolean1.y, cellAlgorithmBoolean2.x, cellAlgorithmBoolean2.y);
    }

    protected void removePercentageWall() {
        for (int i = 1; i< maze.length-1;i++){
            for (int j = 1; j<maze[0].length-1;j++){
                if (!maze[i][j] && random.nextDouble(1)>percentageWall) maze[i][j]=PATH;
            }
        }
    }

    protected boolean isWall(int x, int y){
        if (x<1 || x>= maze.length-1 || y<1 || y>= maze[0].length-1) return false;
        return !maze[x][y];
    }
    protected boolean isWall(CellAlgorithmBoolean cellAlgorithmBoolean){
        return isWall(cellAlgorithmBoolean.x, cellAlgorithmBoolean.y);
    }



    public abstract Position getStart();
    public abstract Position getEnd();
    public abstract boolean[][] createMaze(int width, int heigth, double percentageWall);
}
