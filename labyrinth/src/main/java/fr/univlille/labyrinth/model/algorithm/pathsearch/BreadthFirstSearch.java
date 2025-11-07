package fr.univlille.labyrinth.model.algorithm.pathsearch;

import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.Position;

import java.util.*;

public class BreadthFirstSearch {

    public static List<Position> calculateAllDistances(Maze maze, Position start, int targetDistance) {
        Queue<Position> queue = new LinkedList<>();
        Map<Position, Integer> distances = new HashMap<>();

        queue.add(start);
        distances.put(start, 0);

        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int maxDistance = 0;

        while (!queue.isEmpty()) {
            Position current = queue.poll();
            int currentDistance = distances.get(current);
            maxDistance = Math.max(maxDistance, currentDistance);

            for (int[] dir : directions) {
                int nx = current.getX() + dir[0];
                int ny = current.getY() + dir[1];
                Position next = new Position(nx, ny);

                if (nx >= 0 && nx < maze.getWidth() && ny >= 0 && ny < maze.getHeight()
                    && !distances.containsKey(next)
                    && !maze.isWall(current.getY(), current.getX(), ny, nx)) {
                    distances.put(next, currentDistance + 1);
                    queue.add(next);
                }
            }
        }

        List<Position> result = new ArrayList<>();
        int finalDistance = targetDistance;

        for (Map.Entry<Position, Integer> entry : distances.entrySet()) {
            if (entry.getValue() == finalDistance) {
                result.add(entry.getKey());
            }
        }

        if (result.isEmpty()) {
            finalDistance = maxDistance;
            for (Map.Entry<Position, Integer> entry : distances.entrySet()) {
                if (entry.getValue() == finalDistance) {
                    result.add(entry.getKey());
                }
            }
        }

        return result;
    }

    /**
     * @param maze
     * @param start
     * @param end
     * @return Integer
     */
    // methode helper pour calculer la distance BFS (Parcours en largeur !) reelle entre deux positions
    // retourne null si aucun chemin n'existe
    public static Integer calculateDistance(Maze maze, Position start, Position end) {
        Queue<Position> queue = new LinkedList<>();
        Map<Position, Integer> distances = new HashMap<>();

        queue.add(start);
        distances.put(start, 0);

        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        while (!queue.isEmpty()) {
            Position current = queue.poll();

            if (current.equals(end)) {
                return distances.get(current);
            }

            for (int[] dir : directions) {
                int nx = current.getX() + dir[0];
                int ny = current.getY() + dir[1];
                Position next = new Position(nx, ny);

                if (nx >= 0 && nx < maze.getWidth() && ny >= 0 && ny < maze.getHeight()
                    && !distances.containsKey(next)
                    && !maze.isWall(current.getY(), current.getX(), ny, nx)) {
                    distances.put(next, distances.get(current) + 1);
                    queue.add(next);
                }
            }
        }

        return null;
    }


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

    public static List<Position> getNextPosition (Maze maze,Position currentPos,Set<Position> listedPosition){
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        List<Position> correctNextPositions = new ArrayList<>();
        for (int[] direction : directions){
            Position temp = new Position(currentPos.getX() + direction[0],currentPos.getY()  + direction[1]);
            if(maze.positionCorrecte(temp) && !listedPosition.contains(temp) && !maze.isWall(currentPos.getY(), currentPos.getX(), temp.getY(), temp.getX())){
                correctNextPositions.add(temp);
            }
        }
        return correctNextPositions;
    }
}
