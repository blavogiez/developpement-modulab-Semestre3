package fr.univlille.labyrinth.model.maze.entities.factory;

import java.util.ArrayList;
import java.util.List;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.entities.Entity;
import fr.univlille.labyrinth.model.maze.entities.EntityManager;
import fr.univlille.labyrinth.model.maze.entities.EntityType;
import fr.univlille.labyrinth.model.maze.entities.PlayerEntity;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.MoveBehavior;

/*
 * Factory simple de liste d'entités
 * Initialise une entité complète à partir d'une configuration (Paramètres) et d'un labyrinthe (Position de l'entité)
 */

public class EntityListFactory {

    /** 
     * @param maze
     * @param configuration
     * @return List<Entity>
     */
    public static List<Entity> createEntities(ObservableMaze maze, String configuration) {
        List<EntityConfiguration> configs = EntityConfigurationParser.parse(configuration);
        List<Entity> entities = new ArrayList<>();

        for (EntityConfiguration config : configs) {
            for (int i = 0; i < config.quantity(); i++) {
                MoveBehavior moveBehavior = MoveBehaviorFactory.create(config.moveBehaviorName());
                Entity entity = moveBehavior == null ? config.type().create(maze) : config.type().create(maze, moveBehavior);
                entities.add(entity);
            }
        }

        return entities;
    }

    /** 
     * @param maze
     * @param configuration
     */
    /*
     * Cette configuration de remplir au fur et à mesure est utile pour que les entités se positionnent en ayant connaissance des autres déjà présentes, chose impossible si l'on assignait tout à la fois.
     */
    public static void fillMazeEntities(ObservableMaze maze, String configuration) {
        List<EntityConfiguration> configs = EntityConfigurationParser.parse(configuration);
        EntityManager entityManager = maze.getEntityManager();

        for (EntityConfiguration config : configs) {
            for (int i = 0; i < config.quantity(); i++) {
                MoveBehavior moveBehavior = MoveBehaviorFactory.create(config.moveBehaviorName());
                Entity entity = moveBehavior == null ? config.type().create(maze) : config.type().create(maze, moveBehavior);
                if(entity.getEntityType()== EntityType.PLAYER) {
                    PlayerEntity playerEntity = (PlayerEntity) entity ;
                    playerEntity.setID(entityManager.getCptPlayerID());
                    entityManager.addCptPlayerID();
                }
                entityManager.addEntity(entity);
            }
        }
    }
}