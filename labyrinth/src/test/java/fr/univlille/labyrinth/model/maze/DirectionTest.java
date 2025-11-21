package fr.univlille.labyrinth.model.maze;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @Test
    void should_have_four_directions() {
        Direction[] directions = Direction.values();

        assertEquals(4, directions.length);
    }

    @Test
    void should_get_direction_by_name() {
        assertEquals(Direction.UP, Direction.valueOf("UP"));
        assertEquals(Direction.DOWN, Direction.valueOf("DOWN"));
        assertEquals(Direction.LEFT, Direction.valueOf("LEFT"));
        assertEquals(Direction.RIGHT, Direction.valueOf("RIGHT"));
    }

    @Test
    void should_be_distinct_values() {
        assertNotEquals(Direction.UP, Direction.DOWN);
        assertNotEquals(Direction.LEFT, Direction.RIGHT);
        assertNotEquals(Direction.UP, Direction.LEFT);
    }
}
