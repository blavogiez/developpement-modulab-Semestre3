package fr.univlille.labyrinth;

import fr.univlille.labyrinth.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Cette classe sert à tester que le labyrinthe s'initialise bien aux positions demandées !

// On fait également un test sur le respect du pourcentage de mur, vérifiant que la moyenne de 10 labyrinthes générés tend vers le pourcentage de mur (différence absolue inférieure à 0.05)
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

    // Les tests d'entrée et de fin concernent la partie algorithm :)
}
