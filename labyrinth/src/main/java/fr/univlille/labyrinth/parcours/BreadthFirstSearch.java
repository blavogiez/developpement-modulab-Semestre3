package fr.univlille.labyrinth.parcours;

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
}
