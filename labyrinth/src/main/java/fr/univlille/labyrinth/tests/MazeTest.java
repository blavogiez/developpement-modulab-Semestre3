package fr.univlille.labyrinth.tests;

import fr.univlille.labyrinth.model.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MazeTest {
    static Maze m1,m2,m3,m4;
    static Player p1,p2,p3;

    @BeforeAll
    public static void initialization(){
        m1 = new Maze(10, 10, 50);
        m2 = new Maze(20, 20, 30);
        m3 = new Maze(30, 30, 50);
        m4=m1;
    }
    @BeforeEach
    public void setUp(){

    }

    @Test
    public void testWidth(){
        assertEquals(10, m1.getWidth());
        assertEquals(m1.getWidth(),m4.getWidth());
    }
    @Test
    public void testheight(){
        assertEquals(10, m1.getHeight());
        assertEquals(20, m2.getHeight());
        assertEquals(30, m3.getHeight());
        assertEquals(m4.getHeight(),m1.getHeight());
        
    }
    @Test
    public void testGrid(){
        assertArrayEquals(new boolean[10][10],m1.getGrid());
        assertArrayEquals(new boolean[10][10],m4.getGrid());
        assertArrayEquals(new boolean[20][20],m2.getGrid());
        assertArrayEquals(new boolean[30][30],m3.getGrid());
    }

}
