package fr.univlille.labyrinth.model.algorithm;

import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.Position;

/*
 * Version Fusion par Victor
 */
public class PerfectAlgorithmRandomFusion extends MazeAlgorithm {

    int[][] grid;

    /** 
     * @param maze
     */
    @Override
    public void generateMaze(Maze maze) {
        super.generateMaze(maze);
        grid = generateNumberGrid(maze.getHeight(), maze.getWidth());
        do {
           Direction direction = Direction.getRandomDirection();
           Position position = Position.getRandomPosition(height,width);
           fusionPosition(position,direction);
        } while (!isAllTheSameNumber());


    }

    /** 
     * @param height
     * @param width
     * @return int[][]
     */
    int[][] generateNumberGrid(int height, int width){
        int[][] res = new int[height][width];
        int stack = 0;
        for (int i = 0; i<res.length;i++){
            for (int j = 0; j<res[i].length;j++){
                res[i][j] = stack;
                stack++;
            }
        }
        return res;

    }

    /** 
     * @param position
     * @param direction
     */
    void fusionPosition(Position position, Direction direction){
        Position next = position.add(direction.getX(),direction.getY());
        if (positionCorrecte(next,height,width)){
            int y1 = position.getY();
            int x1 = position.getX();
            int y2 = next.getY();
            int x2 = next.getX();
            int value1 = getInt(y1,x1);
            int value2 = getInt(y2,x2);
            if (value1!=value2){
                removeWall(position,next,direction);
                transformAllNumber(value2,value1);
            }
        }
    }



    /** 
     * @param oldint
     * @param newint
     */
    void transformAllNumber(int oldint, int newint){
        for (int i = 0; i<grid.length;i++){
            for (int j = 0; j<grid[i].length;j++){
                if (grid[i][j]==oldint) grid[i][j]=newint;
            }
        }
    }

    /** 
     * @param y
     * @param x
     * @return int
     */
    int getInt(int y, int x){
        return grid[y][x];
    }


    /** 
     * @return boolean
     */
    boolean isAllTheSameNumber(){
        int number = grid[0][0];
        for (int i = 0; i<grid.length;i++){
            for (int j = 0; j<grid[i].length;j++){
                if (grid[i][j]!=number) return false;
            }
        }
        return true;
    }

    private static PerfectAlgorithmRandomFusion instance;

    /** 
     * @return PerfectAlgorithmRandomFusion
     */
    public static PerfectAlgorithmRandomFusion getInstance(){
        if (instance==null){
            instance = new PerfectAlgorithmRandomFusion();
        }
        return instance;
    }

    /** 
     * @return String
     */
    public String toString() {
        return "Fusion";
    }

}
