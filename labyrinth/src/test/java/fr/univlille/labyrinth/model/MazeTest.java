package fr.univlille.labyrinth.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MazeTest {
    static Maze m1,m2,m3,m4;
    @BeforeAll
    public static void initialization(){
        m1 = new Maze(10, 10, 50);
        m2 = new Maze(20, 20, 30);
        m3 = new Maze(30, 30, 50);
        m4=m1;
    }
    @Test
    public void testGridInitialization() {
        assertNotNull(m1.getGrid());
        assertEquals(9, m1.getGrid().length);
        assertEquals(9, m1.getGrid()[0].length);
        assertNotNull(m2.getGrid());
        assertEquals(19, m2.getGrid().length);
        assertEquals(19, m2.getGrid()[0].length);
        assertNotNull(m3.getGrid());
        assertEquals(29, m3.getGrid().length);
        assertEquals(29, m3.getGrid()[0].length);
    }

    @Test
    public void testWidth(){
        assertEquals(9, m1.getWidth());
        assertEquals(m1.getWidth(),m4.getWidth());
    }
    @Test
    public void testHeight(){
        assertEquals(9, m1.getHeight());
        assertEquals(19, m2.getHeight());
        assertEquals(29, m3.getHeight());
        assertEquals(m4.getHeight(),m1.getHeight());

    }

    @Test
    public void testEntryExit(){
        assertEquals(new Position(1,1),m1.getEntryPosition());
        assertEquals(new Position(1,1),m2.getEntryPosition());
        assertEquals(new Position(1,1),m3.getEntryPosition());
        assertEquals(m1.getEntryPosition(),m4.getEntryPosition());
        assertEquals(new Position(7,7),m1.getExitPosition());
        assertEquals(new Position(17,17),m2.getExitPosition());
        assertEquals(new Position(27,27),m3.getExitPosition());
        assertEquals(m4.getExitPosition(),m1.getExitPosition());
    }

    @Test
    public void testEntryExitPosition() {
        Position entryM3 = m3.getEntryPosition();
        Position exitM3 = m3.getExitPosition();
        assertTrue(entryM3.getX() >= 0 && entryM3.getX() < m3.getWidth());
        assertTrue(entryM3.getY() >= 0 && entryM3.getY() < m3.getHeight());
        assertTrue(exitM3.getX() >= 0 && exitM3.getX() < m3.getWidth());
        assertTrue(exitM3.getY() >= 0 && exitM3.getY() < m3.getHeight());

        Position entryM4 = m4.getEntryPosition();
        Position exitM4 = m4.getExitPosition();
        assertTrue(entryM4.getX() >= 0 && entryM4.getX() < m4.getWidth());
        assertTrue(entryM4.getY() >= 0 && entryM4.getY() < m4.getHeight());
        assertTrue(exitM4.getX() >= 0 && exitM4.getX() < m4.getWidth());
        assertTrue(exitM4.getY() >= 0 && exitM4.getY() < m4.getHeight());
    }
}
