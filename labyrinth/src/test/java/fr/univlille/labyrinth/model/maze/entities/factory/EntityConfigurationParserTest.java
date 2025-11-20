package fr.univlille.labyrinth.model.maze.entities.factory;

import fr.univlille.labyrinth.model.maze.entities.EntityType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EntityConfigurationParserTest {

    @Test
    void should_parse_simple_configuration() {
        String config = "t=PLAYER;q=1;m=PLAYER";

        List<EntityConfiguration> result = EntityConfigurationParser.parse(config);

        assertEquals(1, result.size());
        assertEquals(EntityType.PLAYER, result.get(0).getType());
        assertEquals(1, result.get(0).quantity());
        assertEquals("PLAYER", result.get(0).moveBehaviorName());
    }

    @Test
    void should_parse_multiple_entities() {
        String config = "t=PLAYER;q=1;m=PLAYER|t=EXIT;q=1;m=MOVING";

        List<EntityConfiguration> result = EntityConfigurationParser.parse(config);

        assertEquals(2, result.size());
        assertEquals(EntityType.PLAYER, result.get(0).getType());
        assertEquals(EntityType.EXIT, result.get(1).getType());
        assertEquals("MOVING", result.get(1).moveBehaviorName());
    }

    @Test
    void should_handle_default_configuration() {
        String config = "DEFAULT";

        List<EntityConfiguration> result = EntityConfigurationParser.parse(config);

        assertEquals(2, result.size());
        assertEquals(EntityType.PLAYER, result.get(0).getType());
        assertEquals(EntityType.EXIT, result.get(1).getType());
    }

    @Test
    void should_parse_quantity_and_move_behavior() {
        String config = "t=PLAYER;q=3;m=DEFAULT";

        List<EntityConfiguration> result = EntityConfigurationParser.parse(config);

        assertEquals(1, result.size());
        assertEquals(3, result.get(0).quantity());
        assertEquals("DEFAULT", result.get(0).moveBehaviorName());
    }

    @Test
    void should_handle_null_configuration() {
        List<EntityConfiguration> result = EntityConfigurationParser.parse(null);

        assertEquals(2, result.size());
        assertEquals(EntityType.PLAYER, result.get(0).getType());
        assertEquals(EntityType.EXIT, result.get(1).getType());
    }

    @Test
    void should_handle_empty_configuration() {
        List<EntityConfiguration> result = EntityConfigurationParser.parse("");

        assertEquals(2, result.size());
        assertEquals(EntityType.PLAYER, result.get(0).getType());
        assertEquals(EntityType.EXIT, result.get(1).getType());
    }
}
