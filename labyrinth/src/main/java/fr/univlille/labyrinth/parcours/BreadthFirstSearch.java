package fr.univlille.labyrinth.parcours;

import fr.univlille.labyrinth.model.Position;

import java.util.*;

public class BreadthFirstSearch {

    /** 
     * @param maze
     * @param start
     * @param end
     * @return Integer
     */
    // methode helper pour calculer la distance BFS (Parcours en largeur !) reelle entre deux positions
    // retourne null si aucun chemin n'existe
    public static Integer calculateDistance(boolean[][] maze, Position start, Position end) {
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

                // verifier que la position est valide et est un chemin (PATH = true)
                if (nx>=0 && nx<maze.length && ny>=0 && ny<maze[0].length
                    && maze[nx][ny] && !distances.containsKey(next)) {
                    distances.put(next, distances.get(current) + 1);
                    queue.add(next);
                }
            }
        }

        return null; // aucun chemin trouve
    }
}
