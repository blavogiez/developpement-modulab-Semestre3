package fr.univlille.labyrinth.model.maze.entities.movebehaviors;

import fr.univlille.labyrinth.model.algorithm.pathsearch.MazePath;
import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.Entity;
import fr.univlille.labyrinth.model.maze.entities.PlayerEntity;

import java.util.List;

public class MonsterMoveBehavior implements MoveBehavior {


    /** 
     * @param entity
     * @param direction
     * @param maze
     */
    /*
     * Observable maze à mettre en parametre apres
     */
    public void move(Entity entity, Direction direction, ObservableMaze maze) {
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

    /** 
     * @return boolean
     */
    public boolean isMoving() {
        return true;
    }
}
