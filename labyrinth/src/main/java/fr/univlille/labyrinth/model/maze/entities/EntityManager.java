package fr.univlille.labyrinth.model.maze.entities;

import java.util.ArrayList;
import java.util.List;

import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;

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
     */
    public void moveEntities(int playerID, ObservableMaze maze, Direction direction) {
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
     * @return List<T extends Entity>
     */
    public <T extends Entity> List<T> getEntitiesByType(Class<T> clas) {
        List<T> result = new ArrayList<>();
        for (Entity e : this.entities) {
            if (clas.isInstance(e)) {
                result.add(clas.cast(e));
            }
        }
        return result;
    }

    /**
     * @return boolean
     */
    public boolean containsType(Class<?> cla) {
        for (Object entity : this.entities) {
            if (entity != null && entity.getClass() == cla) {
                return true;
            }
        }
        return false;
    }


    /** 
     * @param playerID
     * @return PlayerEntity
     */
    public PlayerEntity getPlayerEntityByID(int playerID) {
        for (PlayerEntity player : getEntitiesByType(PlayerEntity.class)) {
            if (player.getID() == playerID) {
                return player;
            }
        }
        return null;
    }

    /** 
     * @return MonsterEntity
     */
    public List<Position> getMonstersPositions() {
        List<Position> positions = new ArrayList<Position>();
        for (Entity e : this.entities) {
            if (e.getEntityType() == EntityType.MONSTER){
                positions.add(e.getPosition());
            }
        }
        return positions ;
    }

    /**
     * @return int
     */
    public void checkMonsterOnPlayer() {

        List<Entity> toRemove = new ArrayList<>();
        List<Position> monstersPositions = getMonstersPositions();

        for (Entity player : this.entities) {
            if (player.getEntityType() == EntityType.PLAYER && monstersPositions.contains(player.getPosition())) {
                toRemove.add(player);
            }
        }
        for (Entity e : toRemove) {
            kill(e);
        }
    }

    /** 
     * @param entity
     */
    public void kill(Entity entity){
        entities.remove(entity);
    }

    /** 
     * Renvoie le joueur présent sur une position de sortie 
     * Si un joueur est effectivement présent, on supprime toutes les sorties (unique gagnant)
     * @return PlayerEntity
     */
    public PlayerEntity checkPlayerOnExit() {
        PlayerEntity winner = null ;
        List<PlayerEntity> playerList = getEntitiesByType(PlayerEntity.class);
        List<ExitEntity> exitList = getEntitiesByType(ExitEntity.class);
        List<Position> exitListPosition = exitList.parallelStream().map(Entity::getPosition).toList();

        for (PlayerEntity player : playerList){
            if (exitListPosition.contains(player.getPosition())) winner=player;
        }

        if(winner!=null) entities.removeAll(getEntitiesByType(ExitEntity.class));

        return winner;
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
