package fr.univlille.labyrinth.model.maze.entities.factory;

import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.Entity;
import fr.univlille.labyrinth.model.maze.entities.EntityType;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.MoveBehavior;

import java.util.ArrayList;
import java.util.List;

/*
 * Factory simple de liste d'entités
 * Initialise une entité complète à partir d'une configuration (Paramètres) et d'un labyrinthe (Position de l'entité)
 */

public class EntityListFactory {

    public static List<Entity> createEntities(String configuration, Maze maze) {
        List<EntityConfiguration> configs = EntityConfigurationParser.parse(configuration);
        List<Entity> entities = new ArrayList<>();

        for (EntityConfiguration config : configs) {
            for (int i = 0; i < config.quantity(); i++) {
                Position position = determinePosition(config.type(), i, maze);
                MoveBehavior moveBehavior = MoveBehaviorFactory.create(config.moveBehaviorName());

                Entity entity = moveBehavior == null ? config.type().create(position) : config.type().create(position, moveBehavior);

                entities.add(entity);
            }
        }

        return entities;
    }

    // à bouger après (je sais que ça doit pas etre la c pour test)
    private static Position determinePosition(EntityType type, int index, Maze maze) {
        return switch (type) {
            case PLAYER -> index == 0 ? maze.getEntryPosition() : new Position(index, 0);
            case EXIT -> index == 0 ? maze.getExitPosition() : new Position(index, index);
        };
    }
}
