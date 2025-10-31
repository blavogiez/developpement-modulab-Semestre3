package fr.univlille.labyrinth.model.maze;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void shouldCreatePositionWithCoordinates() {
        Position position = new Position(3, 4);

        assertEquals(3, position.getX());
        assertEquals(4, position.getY());
    }

    @Test
    void shouldBeEqualWhenSameCoordinates() {
        Position pos1 = new Position(3, 4);
        Position pos2 = new Position(3, 4);

        assertEquals(pos1, pos2);
    }

    @Test
    void shouldNotBeEqualWhenDifferentCoordinates() {
        Position pos1 = new Position(3, 4);
        Position pos2 = new Position(4, 5);

        assertNotEquals(pos1, pos2);
    }

    @Test
    void shouldHaveSameHashcodeWhenEqual() {
        Position pos1 = new Position(3, 4);
        Position pos2 = new Position(3, 4);

        assertEquals(pos1.hashCode(), pos2.hashCode());
    }

    @Test
    void shouldUpdateXCoordinate() {
        Position position = new Position(0, 0);

        position.setX(5);

        assertEquals(5, position.getX());
    }

    @Test
    void shouldUpdateYCoordinate() {
        Position position = new Position(0, 0);

        position.setY(7);

        assertEquals(7, position.getY());
    }
}
