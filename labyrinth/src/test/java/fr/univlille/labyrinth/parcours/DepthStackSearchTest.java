package fr.univlille.labyrinth.parcours;

import fr.univlille.labyrinth.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DepthStackSearchTest {
    static Maze m1, m2, m3;

    @BeforeAll
    public static void initialization() {
        m1 = new Maze(10, 10, 0.2);
        m2 = new Maze(20, 20, 0.3);
        m3 = new Maze(5, 5, 0.1);
    }

    @Test
    public void testIsExitPossible() {
        boolean result1 = DepthStackSearch.isExitPossible(m1);
        assertTrue(result1, "La sortie devrait etre accessible dans m1");
    }

    @Test
    public void testSmallMaze() {
        boolean result = DepthStackSearch.isExitPossible(m3);
        assertTrue(result, "La sortie devrait etre accessible dans le petit labyrinthe");
    }

    @Test
    public void testMediumMaze() {
        boolean result = DepthStackSearch.isExitPossible(m2);
        assertTrue(result, "La sortie devrait etre accessible dans le labyrinthe moyen");
    }

    @Test
    public void testMultipleMazes() {
        boolean r1 = DepthStackSearch.isExitPossible(m1);
        boolean r2 = DepthStackSearch.isExitPossible(m2);
        boolean r3 = DepthStackSearch.isExitPossible(m3);
        assertTrue(r1);
        assertTrue(r2);
        assertTrue(r3);
    }
}
