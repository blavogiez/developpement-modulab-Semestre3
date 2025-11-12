package fr.univlille.labyrinth.model.maze.entities;

import java.util.ArrayList;
import java.util.List;

import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.save.Player;

public class EntityManager {
    private int cptPlayerID;

    private final List<Entity> entities;

    public EntityManager(List<Entity> entities) {
        this.entities = entities;
    }

    public EntityManager() {
        this(new ArrayList<>());
    }

    public void addCptPlayerID() {
        cptPlayerID++;
    }

    /** 
     * @return int
     */
    public int getCptPlayerID() {
        return cptPlayerID;
    }



    /** 
     * @param entity
     */
    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    /** 
     * @param entity
     */
    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    /** 
     * @return List<Entity>
     */
    public List<Entity> getEntities() {
        return new ArrayList<>(entities);
    }

    public void clear() {
        entities.clear();
    }

    /** 
     * @param playerID
     * @param maze
     * @param direction
     * @return boolean
     */
    public boolean moveEntities(int playerID, ObservableMaze maze, Direction direction) {
        boolean stmt = true ;
        for (Entity entity : entities) {
            if(entity.getEntityType()==EntityType.PLAYER) {
                PlayerEntity playerEntity = (PlayerEntity) entity ;
                if(playerEntity.getID()==playerID) {
                    if (!playerEntity.move(maze, direction)) stmt = false ;
                }
            }
            else {
                if (!entity.move(maze, direction)) stmt = false ;
            }
        }
        return stmt ;
    }

    /** 
     * @return PlayerEntity
     */
    public PlayerEntity getPlayerEntity() {
        for (Entity e : this.entities) {
            if (e.getEntityType()==EntityType.PLAYER) return (PlayerEntity) e ;
        }
        return null ;
    }

    /** 
     * @return List<PlayerEntity>
     */
    public List<PlayerEntity> getPlayerEntities() {
        List<PlayerEntity> players = new ArrayList<>();
        for (Entity e : this.entities) {
            if (e.getEntityType() == EntityType.PLAYER) {
                players.add((PlayerEntity) e);
            }
        }
        return players;
    }

    /** 
     * @param playerID
     * @return PlayerEntity
     */
    public PlayerEntity getPlayerEntityByID(int playerID) {
        for (PlayerEntity player : getPlayerEntities()) {
            if (player.getID() == playerID) {
                return player;
            }
        }
        return null;
    }

    /** 
     * @return MonsterEntity
     */
    public MonsterEntity getMonsterEntity() {
        for (Entity e : this.entities) {
            if (e.getEntityType()==EntityType.MONSTER) return (MonsterEntity) e ;
        }
        return null ;
    }

    /** 
     * @return int
     */
    public int checkMonsterOnPlayer() {
        int deadCount = 0;
        List<Entity> toRemove = new ArrayList<>();

        for (Entity player : this.entities) {
            if (player.getEntityType() == EntityType.PLAYER) {
                for (Entity monster : this.entities) {
                    if (monster.getEntityType() == EntityType.MONSTER) {
                        if (monster.getPosition().equals(player.getPosition())) {
                            toRemove.add(player);
                            deadCount++;
                            break;
                        }
                    }
                }
            }
        }

        for (Entity e : toRemove) {
            entities.remove(e);
        }

        return deadCount;
    }

    /** 
     * @return boolean
     */
    public boolean checkPlayerOnExit() {
        for (Entity player : this.entities) {
            if (player.getEntityType() == EntityType.PLAYER) {
                for (Entity exit : this.entities) {
                    if (exit.getEntityType() == EntityType.EXIT) {
                        if (player.getPosition().equals(exit.getPosition())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /** 
     * @param position
     * @return boolean
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
