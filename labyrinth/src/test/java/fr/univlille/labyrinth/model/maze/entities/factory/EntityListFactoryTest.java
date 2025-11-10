package fr.univlille.labyrinth.model.maze.entities.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.maze.entities.Entity;
import fr.univlille.labyrinth.model.maze.entities.EntityType;
import fr.univlille.labyrinth.model.maze.entities.ExitEntity;
import fr.univlille.labyrinth.model.maze.entities.PlayerEntity;

class EntityListFactoryTest {

    private ObservableMaze maze;

    @BeforeEach
    void setUp() {
        maze = new ObservableMaze(10, 10, 15);
    }

    @Test
    void shouldCreateEntitiesFromDefaultConfiguration() {
        List<Entity> entities = EntityListFactory.createEntities(maze,"DEFAULT");

        assertEquals(2, entities.size());
        assertInstanceOf(PlayerEntity.class, entities.get(0));
        assertInstanceOf(ExitEntity.class, entities.get(1));
    }

    @Test
    void shouldCreateMultipleEntitiesWithQuantity() {
        String config = "t=PLAYER;q=3;m=PLAYER";

        List<Entity> entities = EntityListFactory.createEntities(maze, config);

        assertEquals(3, entities.size());
        entities.forEach(entity -> assertInstanceOf(PlayerEntity.class, entity));
    }

    @Test
    void shouldCreateEntitiesWithCorrectMoveBehavior() {
        String config = "t=EXIT;q=1;m=MOVING";

        List<Entity> entities = EntityListFactory.createEntities(maze, config);

        assertEquals(1, entities.size());
        assertNotNull(entities.get(0).getMoveBehavior());
    }

    @Test
    void shouldCreateEntityWithNullMoveBehavior() {
        String config = "t=EXIT;q=1;m=DEFAULT";

        List<Entity> entities = EntityListFactory.createEntities(maze, config);

        assertEquals(1, entities.size());
        assertNull(entities.get(0).getMoveBehavior());
    }

    @Test
    void shouldAssignCorrectPositions() {
        Position exitPos = maze.getExitPosition();
        List<Entity> entities = maze.getEntityManager().getEntities();

        assertEquals(2, entities.size());
        assertEquals(maze.getEntryPosition(), entities.get(0).getPosition());
        assertEquals(exitPos, entities.get(1).getPosition());
    }

    @Test
    void shouldCreateMixedEntities() {
        String config = "t=PLAYER;q=2;m=PLAYER|t=EXIT;q=5;m=MOVING";

        List<Entity> entities = EntityListFactory.createEntities(maze, config);

        assertEquals(7, entities.size());
        long playerCount = entities.stream().filter(e -> e.getEntityType() == EntityType.PLAYER).count();
        long exitCount = entities.stream().filter(e -> e.getEntityType() == EntityType.EXIT).count();
        assertEquals(2, playerCount);
        assertEquals(5, exitCount);
    }
}
