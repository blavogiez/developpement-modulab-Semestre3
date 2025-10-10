package fr.univlille.labyrinth.tests;
import fr.univlille.labyrinth.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class PositionTest {
    static Position p1,p2,p3,p4;

    @BeforeAll
    public static void initialization(){
        p1=new Position(3,4);
        p2=new Position(3,4);
        p3=new Position(4,5);
        p4=new Position(0,0);
    }

    @Test
    public void testEqualsAndHashCode() {
        assertEquals(p1, p2);
        assertNotEquals(p1, p3);
        assertNotEquals(p2,p3);
        assertEquals(p1.hashCode(), p2.hashCode());
        assertNotEquals(p1.hashCode(),p3.hashCode());
    }

    @Test
    public void testSetters() {
        p4.setX(5);
        p4.setY(7);
        assertEquals(5, p4.getX());
        assertEquals(7, p4.getY());
    }
}
