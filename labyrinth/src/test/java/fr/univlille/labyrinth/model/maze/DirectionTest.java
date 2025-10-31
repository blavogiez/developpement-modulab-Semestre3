package fr.univlille.labyrinth.model.maze;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @Test
    void shouldHaveFourDirections() {
        Direction[] directions = Direction.values();

        assertEquals(4, directions.length);
    }

    @Test
    void shouldGetDirectionByName() {
        assertEquals(Direction.UP, Direction.valueOf("UP"));
        assertEquals(Direction.DOWN, Direction.valueOf("DOWN"));
        assertEquals(Direction.LEFT, Direction.valueOf("LEFT"));
        assertEquals(Direction.RIGHT, Direction.valueOf("RIGHT"));
    }

    @Test
    void shouldBeDistinctValues() {
        assertNotEquals(Direction.UP, Direction.DOWN);
        assertNotEquals(Direction.LEFT, Direction.RIGHT);
        assertNotEquals(Direction.UP, Direction.LEFT);
    }
}
