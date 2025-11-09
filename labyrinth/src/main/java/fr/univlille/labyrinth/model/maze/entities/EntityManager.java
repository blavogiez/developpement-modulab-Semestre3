package fr.univlille.labyrinth.model.maze.entities;

import java.util.ArrayList;
import java.util.List;

import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;

public class EntityManager {
    private final List<Entity> entities;

    public EntityManager(List<Entity> entities) {
        this.entities = entities;
    }

    public EntityManager() {
        this(new ArrayList<>());
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

    public boolean moveEntities(ObservableMaze maze, Direction direction) {
        boolean stmt = true ;
        for (Entity entity : entities) {
            if (!entity.move(maze, direction)) stmt = false ;
        }
        return stmt ;
    }

    public PlayerEntity getPlayerEntity() {
        for (Entity e : this.entities) {
            if (e.getEntityType()==EntityType.PLAYER) return (PlayerEntity) e ;
        }
        return null ;
    }

    public MonsterEntity getMonsterEntity() {
        for (Entity e : this.entities) {
            if (e.getEntityType()==EntityType.MONSTER) return (MonsterEntity) e ;
        }
        return null ;
    }

    /*
     * Vérifie si au moins au monstre se situe à la même position que le joueur.
     * quand y'aura multijoueur il faudra avoir param int et remove l'entité de la liste (le 1er joueur meurt mais le 2e continue)
     * comme ça la lose sera déclenchée quand y'aura aucun joueur dans le 
     */
    public boolean checkMonsterOnPlayer() {
        PlayerEntity player = getPlayerEntity();
        if (player == null) return false;
        for (Entity e : this.entities) {
            if (e.getEntityType() == EntityType.MONSTER) {
                if (e.getPosition().equals(player.getPosition())) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * Vérifie si le joueur se situe à la même position qu'une sortie.
     * quand y'aura multijoueur il faudra avoir param int et remove l'entité de la liste (le 1er joueur meurt mais le 2e continue)
     * comme ça la lose sera déclenchée quand y'aura aucun joueur dans le 
     */
    public boolean checkPlayerOnExit() {
        PlayerEntity player = getPlayerEntity();
        if (player == null) return false;
        for (Entity e : this.entities) {
            if (e.getEntityType() == EntityType.EXIT) {
                if (e.getPosition().equals(player.getPosition())) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * Vérifie si une quelconque entité se situe sur la position en entrée. Cela permet d'éviter de faire positionner deux entités sur la même position.
     */
    public boolean isEntityOnCell(Position position) {
        for (Entity e : this.entities) {
            if (e.getPosition().equals(position)) {
                return true;
            }
        }
        return false ;
    }

}
