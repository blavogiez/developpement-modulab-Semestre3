package fr.univlille.labyrinth.model.algorithm.pathsearch;

import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.Position;

import java.util.*;

public class BreadthFirstSearch {

    /*
     * Record simple qui retourne la liste de toutes les positions demandées à la distance demandée
     * actualDistance corrige la distance si elle était impossible (supérieure à la distance maximale) et va changer le parametre distance dans le labyrinthe
     * Donc si on met une distance de 300 000 et que le maximum est à 140, le labyrinthe corrige et c'est bien la distance effective de 140 qui sera affichée lors du toString du labyrinthe. 
     */
    public record DistanceResult(List<Position> positions, int actualDistance) {}

    /**
     * @param maze
     * @param start
     * @param targetDistance
     * @return DistanceResult
     */
    // parcourt le labyrinthe en largeur depuis start et calcule les distances de toutes les positions accessibles
    // si targetDistance depasse la distance maximale possible, elle est automatiquement limitee a cette distance maximale
    // retourne toutes les positions situees a la distance cible (ou maximale si targetDistance est trop grande)
    public static DistanceResult calculateAllDistances(Maze maze, Position start, int targetDistance) {
        Queue<Position> queue = new LinkedList<>();
        Map<Position, Integer> distances = new HashMap<>();

        queue.add(start);
        distances.put(start, 0);

        while (!queue.isEmpty()) {
            Position current = queue.poll();
            int currentDistance = distances.get(current);

            for (Direction dir : Direction.values()) {
                int nx = current.getX() + dir.getX();
                int ny = current.getY() + dir.getY();
                Position next = new Position(nx, ny);

                if (nx >= 0 && nx < maze.getWidth() && ny >= 0 && ny < maze.getHeight()
                    && !distances.containsKey(next)
                    && !maze.isWall(current.getY(), current.getX(), ny, nx)) {
                    distances.put(next, currentDistance + 1);
                    queue.add(next);
                }
            }
        }

        int maxDistance = 0;
        for (int dist : distances.values()) {
            maxDistance = Math.max(maxDistance, dist);
        }

        int finalTarget = Math.min(targetDistance, maxDistance);
        List<Position> result = new ArrayList<>();

        for (Map.Entry<Position, Integer> entry : distances.entrySet()) {
            if (entry.getValue() == finalTarget) {
                result.add(entry.getKey());
            }
        }

        return new DistanceResult(result, finalTarget);
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

        while (!queue.isEmpty()) {
            Position current = queue.poll();

            if (current.equals(end)) {
                return distances.get(current);
            }

            for (Direction dir : Direction.values()) {
                int nx = current.getX() + dir.getX();
                int ny = current.getY() + dir.getY();
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
        List<Position> correctNextPositions = new ArrayList<>();
        for (Direction direction : Direction.values()){
            Position temp = new Position(currentPos.getX() + direction.getX(),currentPos.getY()  + direction.getY());
            if(maze.positionCorrecte(temp) && !listedPosition.contains(temp) && !maze.isWall(currentPos.getY(), currentPos.getX(), temp.getY(), temp.getX())){
                correctNextPositions.add(temp);
            }
        }
        return correctNextPositions;
    }
}
