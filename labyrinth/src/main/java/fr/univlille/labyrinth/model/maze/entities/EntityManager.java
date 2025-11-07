package fr.univlille.labyrinth.model.maze.entities;

import java.util.ArrayList;
import java.util.List;

import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.Maze;

public class EntityManager {
    private final List<Entity> entities;

    public EntityManager() {
        this.entities = new ArrayList<>();
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public List<Entity> getEntities() {
        return new ArrayList<>(entities);
    }

    public void clear() {
        entities.clear();
    }

    public boolean moveEntities(Maze maze, Direction direction) {
        boolean stmt = true ;
        for (Entity entity : entities) {
            if (!entity.move(maze, direction)) stmt = false ;
        }
        return stmt ;
    }
    
}
