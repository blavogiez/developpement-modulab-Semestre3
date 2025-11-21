package fr.univlille.labyrinth.model.algorithm;

import java.util.Arrays;
import java.util.Random;

import fr.univlille.labyrinth.model.algorithm.pathsearch.DistanceResult;
import fr.univlille.labyrinth.model.algorithm.pathsearch.MazeDistance;
import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.Position;


public abstract class MazeAlgorithm{
    boolean[][] verticalsWalls;
    boolean[][] horizontalsWalls;
    int height;
    int width;

    /** 
     * @param maze
     */
    public void generateMaze(Maze maze){
        horizontalsWalls = maze.getMurHorizontaux();
        verticalsWalls = maze.getMurVerticaux();
        allAreTrue(horizontalsWalls);
        allAreTrue(verticalsWalls);
        width = maze.getWidth();
        height = maze.getHeight();
    }

    private static final Random RANDOM = new Random();
    /** 
     * @param maze
     */
    public void generateExitAndPlayer(Maze maze){

        int distanceBetweenEntryAndExit = maze.getDistanceBetweenEntryAndExit();

        Position entryPosition = new Position(RANDOM.nextInt(width), RANDOM.nextInt(height));

        DistanceResult distResult = MazeDistance.calculateAllDistances(maze, entryPosition, distanceBetweenEntryAndExit);

        Position exitPosition = distResult.positions().get(RANDOM.nextInt(distResult.positions().size()));

        maze.setEntry(entryPosition);
        maze.setExit(exitPosition);
        maze.setDistanceBetweenEntryAndExit(distResult.actualDistance());
    }


    /** 
     * @param tab
     */
    protected static void allAreTrue(boolean[][] tab) {
        for (boolean[] booleans : tab) {
            Arrays.fill(booleans, true);
        }
    }

    /** 
     * @param ligne
     * @param colonne
     * @param height
     * @param width
     * @return boolean
     */
    public static boolean positionCorrecte(int ligne, int colonne, int height, int width) {
        return ligne>=0 && ligne < height && colonne >=0 && colonne < width;
    }

    /** 
     * @param ligne
     * @param colonne
     * @param tab
     * @return boolean
     */
    public static boolean positionCorrecte(int ligne, int colonne, boolean[][] tab) {
        return positionCorrecte(ligne,colonne,tab.length,tab[0].length);
    }

    /** 
     * @param pos
     * @param tab
     * @return boolean
     */
    public static boolean positionCorrecte(Position pos, boolean[][] tab) {
        return positionCorrecte(pos.getY(),pos.getX(),tab.length,tab[0].length);
    }

    /** 
     * @param pos
     * @param height
     * @param width
     * @return boolean
     */
    public static boolean positionCorrecte(Position pos, int height, int width) {
        return positionCorrecte(pos.getY(),pos.getX(),height,width);
    }

    /** 
     * @param start
     * @param direction
     */
    protected void removeWall(Position start, Direction direction){
        removeWall(start,start.add(direction.getY(),direction.getX()),direction);
    }

    /** 
     * @param start
     * @param next
     */
    protected void removeWall(Position start, Position next){
        removeWall(start,next,start.diff(next));
    }



    /** 
     * @param start
     * @param next
     * @param direction
     */
    protected void removeWall(Position start, Position next, Direction direction){
        Position min = start.min(next);
        if (min.getX()<0 || min.getY()<0) return;

        switch (direction) {
            case LEFT, RIGHT ->
                verticalsWalls[min.getX()][min.getY()] = false;

            case UP, DOWN ->
                horizontalsWalls[min.getY()][min.getX()] = false;


        }
    }
}
