package fr.univlille.labyrinth;
import fr.univlille.labyrinth.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class DirectionTest {
    static Direction d1,d2,d3,d4;

    @BeforeAll
    public static void initialization(){
        d1=Direction.UP;
        d2=Direction.DOWN;
        d3=Direction.LEFT;
        d4=Direction.RIGHT;
    }

    @Test
    public void testDirectionValues() {
        assertEquals(Direction.UP, d1);
        assertEquals(Direction.DOWN, d2);
        assertEquals(Direction.LEFT, d3);
        assertEquals(Direction.RIGHT, d4);
    }

    @Test
    public void testValueOf() {
        assertEquals(Direction.UP, Direction.valueOf("UP"));
        assertEquals(Direction.DOWN, Direction.valueOf("DOWN"));
        assertEquals(Direction.LEFT, Direction.valueOf("LEFT"));
        assertEquals(Direction.RIGHT, Direction.valueOf("RIGHT"));
    }

    @Test
    public void testValues() {
        Direction[] directions = Direction.values();
        assertEquals(4, directions.length);
        assertNotNull(directions);
    }
}
