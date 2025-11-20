package fr.univlille.labyrinth.model.algorithm.pathsearch;

import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.MazeWallChecker;
import fr.univlille.labyrinth.model.maze.Position;

import java.util.*;

/*
 * Classe statique utilitaire pour trouver le chemin entre une position A et B.
 */
public class MazePath {

    /**
     * @param maze
     * @param start
     * @param end
     * @return List<Position>
     */
    public static List<Position> pathFinder(Maze maze, Position start, Position end){
        Queue<Position> queue = new LinkedList<>();
        Map<Position, Position> previousPos = new HashMap<>();
        queue.add(start);
        List<Position> finalPath = new ArrayList<>();

        while (!queue.isEmpty()) {
            Position current = queue.poll();
            if(current.equals(end)){
                Position positionPath = current;
                while (!positionPath.equals(start)){
                    finalPath.add(positionPath);
                    positionPath = previousPos.get(positionPath);
                }
                Collections.reverse(finalPath);
                return finalPath;
            }
            List<Position> nextPositions = getNextPosition(maze, current ,previousPos.keySet());
            for (Position nextPos :nextPositions) {
                previousPos.put(nextPos,current);
                queue.add(nextPos);
            }

        }
        return null;
    }

    /**
     * @param maze
     * @param currentPos
     * @param listedPosition
     * @return List<Position>
     */
    public static List<Position> getNextPosition (Maze maze,Position currentPos,Set<Position> listedPosition){
        List<Position> correctNextPositions = new ArrayList<>();
        for (Direction direction : Direction.values()){
            Position temp = new Position(currentPos.getX() + direction.getX(),currentPos.getY()  + direction.getY());
            if(MazeWallChecker.positionCorrecte(temp,maze) && !listedPosition.contains(temp) && !MazeWallChecker.isWall(maze,currentPos.getY(), currentPos.getX(), temp.getY(), temp.getX())){
                correctNextPositions.add(temp);
            }
        }
        return correctNextPositions;
    }
}
