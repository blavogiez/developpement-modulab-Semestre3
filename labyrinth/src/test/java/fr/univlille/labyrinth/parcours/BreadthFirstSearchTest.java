package fr.univlille.labyrinth.parcours;

import fr.univlille.labyrinth.model.algorithm.MazeAlgorithmFactory;
import fr.univlille.labyrinth.model.maze.Maze;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BreadthFirstSearchTest {
    static Maze m1, m2, m3;

    @BeforeAll
    public static void initialization() {
        m1 = new Maze(MazeAlgorithmFactory.STANDARDLARGEUR,10, 10, 0.2);
        m2 = new Maze(MazeAlgorithmFactory.STANDARDLARGEUR,20, 20, 0.3);
        m3 = new Maze(MazeAlgorithmFactory.STANDARDLARGEUR,5, 5, 0.1);
    }

    @Test
    public void testCalculateDistance() {
        //on teste que le calcul de distance retourne bien un resultat
        Integer result1 = BreadthFirstSearch.calculateDistance(m1.getGrid(), m1.getEntryPosition(), m1.getExitPosition());
        assertNotNull(result1, "Un chemin devrait exister dans le labyrinthe");
    }

    @Test
    public void testSmallMaze() {
        Integer result = BreadthFirstSearch.calculateDistance(m3.getGrid(), m3.getEntryPosition(), m3.getExitPosition());
        assertNotNull(result, "Un chemin devrait exister dans le petit labyrinthe");
    }

    @Test
    public void testMediumMaze() {
        //teste sur labyrinthe de taille moyenne
        Integer result = BreadthFirstSearch.calculateDistance(m2.getGrid(), m2.getEntryPosition(), m2.getExitPosition());
        assertNotNull(result, "Un chemin devrait exister dans le labyrinthe moyen");
    }

    @Test
    public void testMultipleMazes() {
        Integer r1 = BreadthFirstSearch.calculateDistance(m1.getGrid(), m1.getEntryPosition(), m1.getExitPosition());
        Integer r2 = BreadthFirstSearch.calculateDistance(m2.getGrid(), m2.getEntryPosition(), m2.getExitPosition());
        Integer r3 = BreadthFirstSearch.calculateDistance(m3.getGrid(), m3.getEntryPosition(), m3.getExitPosition());
        assertNotNull(r1);
        assertNotNull(r2);
        assertNotNull(r3);
    }

    @Test
    public void testDistanceIsPositive() {
        //si un chemin existe, la distance doit etre positive
        Integer distance = BreadthFirstSearch.calculateDistance(m1.getGrid(), m1.getEntryPosition(), m1.getExitPosition());
        assertNotNull(distance);
        assertTrue(distance>=0, "La distance devrait etre positive");
    }
}
