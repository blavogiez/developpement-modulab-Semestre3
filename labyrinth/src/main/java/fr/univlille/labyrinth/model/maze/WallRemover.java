package fr.univlille.labyrinth.model.maze;

import java.util.Random;

public abstract class WallRemover {
    private WallRemover(){}
    /** 
     * @param maze
     * @return int
     */
    public static int countWalls(Maze maze){
        int count = 0;
        for(boolean[] line : maze.getMurHorizontaux()){
            for(boolean wall : line){
                if (wall){
                    count++;
                }
            }
        }
        for(boolean[] line : maze.getMurVerticaux()){
            for(boolean wall : line){
                if (wall){
                    count++;
                }
            }
        }
        return count;
    }

    /** 
     * @param percentage
     * @param maze
     */
    public static void randomWallRemoval(double percentage, Maze maze){
        int nbWallToRemove = (int)(countWalls(maze)*percentage);
        randomVerticalWallRemoval(nbWallToRemove/2,maze);
        randomHorizontalWallRemoval(nbWallToRemove/2,maze);
    }

    private static final Random RANDOM = new Random();

    /** 
     * @param nbWall
     * @param maze
     */
    public static void randomVerticalWallRemoval(int nbWall, Maze maze){

        while(nbWall > 0){
            int i = RANDOM.nextInt(maze.getWidth()-1);
            int j= RANDOM.nextInt(maze.getHeight());
            if(maze.getMurVerticaux()[i][j]){
                maze.getMurVerticaux()[i][j] = false;
                nbWall--;
            }
        }
    }

    /** 
     * @param nbWall
     * @param maze
     */
    public static void randomHorizontalWallRemoval(int nbWall,Maze maze){
        while(nbWall > 0){
            int i = RANDOM.nextInt(maze.getWidth());
            int j= RANDOM.nextInt(maze.getHeight()-1);
            if(maze.getMurHorizontaux()[j][i]){
                maze.getMurHorizontaux()[j][i] = false;
                nbWall--;
            }
        }
    }
}
