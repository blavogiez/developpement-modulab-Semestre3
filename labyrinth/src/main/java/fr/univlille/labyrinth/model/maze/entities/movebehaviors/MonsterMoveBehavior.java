package fr.univlille.labyrinth.model.maze.entities.movebehaviors;

import java.util.List;

import fr.univlille.labyrinth.model.algorithm.pathsearch.MazePath;
import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.Entity;
import fr.univlille.labyrinth.model.maze.entities.PlayerEntity;

public class MonsterMoveBehavior implements MoveBehavior {
    private static final long MOVE_INTERVAL_MS = 500 ;

    private static boolean ignoreCooldown = false;

    private long lastMoveTime = 0;

    /*
    Ignore le cooldown
    Utile dans le cadre des tests */
    public static void setIgnoreCooldown(boolean ignore) {
        ignoreCooldown = ignore;
    }

    @Override
    public void move(Entity entity, Direction direction, ObservableMaze maze) {
        if (!canMoveNow()) {
            return;
        }
        updateLastMoveTime();

        Position position = entity.getPosition();
        List<PlayerEntity> players = maze.getEntityManager().getEntitiesByType(PlayerEntity.class);
        if (players.isEmpty()) return;

        List<Position> shortestPath = null;
        for (PlayerEntity player : players) {
            List<Position> path = MazePath.pathFinder(maze, position, player.getPosition());
            if (!path.isEmpty() && (shortestPath == null || path.size() < shortestPath.size())) {
                shortestPath = path;
            }
        }

        if (shortestPath != null && !shortestPath.isEmpty()) {
            entity.setPosition(shortestPath.get(0));
        }
    }

    private boolean canMoveNow() {
        if (ignoreCooldown) {
            return true;
        }
        long now = System.currentTimeMillis();
        return now - lastMoveTime >= MOVE_INTERVAL_MS;
    }

    private void updateLastMoveTime() {
        if (!ignoreCooldown) {
            lastMoveTime = System.currentTimeMillis();
        }
    }
}
