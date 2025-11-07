package fr.univlille.labyrinth.model.maze.entities.factory;

import fr.univlille.labyrinth.model.maze.entities.EntityType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EntityConfigurationParserTest {

    @Test
    void shouldParseSimpleConfiguration() {
        String config = "t=PLAYER;q=1;m=PLAYER";

        List<EntityConfiguration> result = EntityConfigurationParser.parse(config);

        assertEquals(1, result.size());
        assertEquals(EntityType.PLAYER, result.get(0).type());
        assertEquals(1, result.get(0).quantity());
        assertEquals("PLAYER", result.get(0).moveBehaviorName());
    }

    @Test
    void shouldParseMultipleEntities() {
        String config = "t=PLAYER;q=1;m=PLAYER|t=EXIT;q=1;m=MOVING";

        List<EntityConfiguration> result = EntityConfigurationParser.parse(config);

        assertEquals(2, result.size());
        assertEquals(EntityType.PLAYER, result.get(0).type());
        assertEquals(EntityType.EXIT, result.get(1).type());
        assertEquals("MOVING", result.get(1).moveBehaviorName());
    }

    @Test
    void shouldHandleDefaultConfiguration() {
        String config = "DEFAULT";

        List<EntityConfiguration> result = EntityConfigurationParser.parse(config);

        assertEquals(2, result.size());
        assertEquals(EntityType.PLAYER, result.get(0).type());
        assertEquals(EntityType.EXIT, result.get(1).type());
    }

    @Test
    void shouldParseQuantityAndMoveBehavior() {
        String config = "t=PLAYER;q=3;m=DEFAULT";

        List<EntityConfiguration> result = EntityConfigurationParser.parse(config);

        assertEquals(1, result.size());
        assertEquals(3, result.get(0).quantity());
        assertEquals("DEFAULT", result.get(0).moveBehaviorName());
    }

    @Test
    void shouldHandleNullConfiguration() {
        List<EntityConfiguration> result = EntityConfigurationParser.parse(null);

        assertEquals(2, result.size());
        assertEquals(EntityType.PLAYER, result.get(0).type());
        assertEquals(EntityType.EXIT, result.get(1).type());
    }

    @Test
    void shouldHandleEmptyConfiguration() {
        List<EntityConfiguration> result = EntityConfigurationParser.parse("");

        assertEquals(2, result.size());
        assertEquals(EntityType.PLAYER, result.get(0).type());
        assertEquals(EntityType.EXIT, result.get(1).type());
    }
}
