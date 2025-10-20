package fr.univlille.labyrinth.model.algorithm;

import fr.univlille.labyrinth.model.Position;
import javafx.geometry.Pos;

import java.util.Random;

public abstract class MazeAlgorithmTemplate {
    protected boolean[][] maze;
    protected double percentageWall;
    protected final boolean PATH = true, WALL = false;
    protected final Random random = new Random();



    /** 
     * @param x
     * @param y
     */
    protected void markCell(int x, int y){
        maze[x][y]=PATH;
    }
    /** 
     * @param cellAlgorithmBoolean
     */
    protected void markCell(CellAlgorithmBoolean cellAlgorithmBoolean){
        maze[cellAlgorithmBoolean.x][cellAlgorithmBoolean.y]=PATH;

    }

    /** 
     * @param position
     */
    protected void markCell(Position position){
        markCell(position.getX(),position.getY());

    }

    /** 
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    protected void markPathBetweenCell(int x1, int y1, int x2, int y2){
        markCell(x1,y1);
        markCell(x2,y2);
        markCell((x1+x2)/2,(y1+y2)/2);
    }

    /** 
     * @param cellAlgorithmBoolean1
     * @param cellAlgorithmBoolean2
     */
    protected void markPathBetweenCell(CellAlgorithmBoolean cellAlgorithmBoolean1, CellAlgorithmBoolean cellAlgorithmBoolean2){
        markPathBetweenCell(cellAlgorithmBoolean1.x, cellAlgorithmBoolean1.y, cellAlgorithmBoolean2.x, cellAlgorithmBoolean2.y);
    }

    /** 
     * @param pos1
     * @param pos2
     */
    protected void markPathBetweenCell(Position pos1, Position pos2){
        markPathBetweenCell(pos1.getX(), pos1.getY(), pos2.getX(), pos2.getY());
    }

    protected void removePercentageWall() {
        for (int i = 1; i< maze.length-1;i++){
            for (int j = 1; j<maze[0].length-1;j++){
                if (!maze[i][j] && random.nextDouble(1)>percentageWall) maze[i][j]=PATH;
            }
        }
    }

    /** 
     * @param x
     * @param y
     * @return boolean
     */
    protected boolean isWall(int x, int y){
        if (x<1 || x>= maze.length-1 || y<1 || y>= maze[0].length-1) return false;
        return !maze[x][y];
    }
    /** 
     * @param cellAlgorithmBoolean
     * @return boolean
     */
    protected boolean isWall(CellAlgorithmBoolean cellAlgorithmBoolean){
        return isWall(cellAlgorithmBoolean.x, cellAlgorithmBoolean.y);
    }

    /** 
     * @param position
     * @return boolean
     */
    protected boolean isWall(Position position){
        return isWall(position.getX(), position.getY());
    }



    public abstract Position getStart();
    public abstract Position getEnd();
    public abstract boolean[][] createMaze(int width, int heigth, double percentageWall, int pathLength);
}
