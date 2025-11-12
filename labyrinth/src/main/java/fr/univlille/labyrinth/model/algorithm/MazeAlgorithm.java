package fr.univlille.labyrinth.model.algorithm;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import fr.univlille.labyrinth.model.algorithm.pathsearch.BreadthFirstSearch;
import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.Position;
import javafx.geometry.Pos;



public abstract class MazeAlgorithm{
    boolean[][] verticalsWalls, horizontalsWalls;
    int height, width;

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
    };

    /** 
     * @param maze
     */
    public void generateExitAndPlayer(Maze maze){

        int distanceBetweenEntryAndExit = maze.getDistanceBetweenEntryAndExit();
        Random random = new Random();
        Position entryPosition = new Position(random.nextInt(width), random.nextInt(height));

        BreadthFirstSearch.DistanceResult distResult = BreadthFirstSearch.calculateAllDistances(maze, entryPosition, distanceBetweenEntryAndExit);

        Position exitPosition = distResult.positions().get(random.nextInt(distResult.positions().size()));

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
