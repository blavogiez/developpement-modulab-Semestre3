package fr.univlille.labyrinth.algorithm;

import fr.univlille.labyrinth.model.algorithm.MazeAlgorithmStandardLargeur;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.parcours.BreadthFirstSearch;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// cette classe teste que la distance minimale entrée/sortie demandée est toujours respectée
// On verif que la distance BFS réelle >= distance minimale demandée (normalement TOUJOURS le cas avec notre algo)

public class MazeDistanceGenerationTest {

    @Test
    public void testShortDistanceRespected() {
        // gen un labyrinthe 10x10 avec distance minimale de 5
        MazeAlgorithmStandardLargeur algo = MazeAlgorithmStandardLargeur.getInstance();
        boolean[][] maze = algo.createMaze(10, 10, 0.3, 5);
        Position start = algo.getStart();
        Position end = algo.getEnd();

        // verif que la distance réelle respecte la distance minimale demandée
        Integer distance = BreadthFirstSearch.calculateDistance(maze, start, end);
        assertNotNull(distance, "Un chemin doit exister entre start et end");
        assertTrue(distance >= 5, "Distance devrait être >= 5, mais est " + distance);
    }

    @Test
    public void testMediumDistanceRespected() {
        // gen un labyrinthe 20x20 avec distance minimale de 12
        MazeAlgorithmStandardLargeur algo = MazeAlgorithmStandardLargeur.getInstance();
        boolean[][] maze = algo.createMaze(20, 20, 0.3, 12);
        Position start = algo.getStart();
        Position end = algo.getEnd();

        // verif que la distance réelle respecte la distance minimale demandée
        Integer distance = BreadthFirstSearch.calculateDistance(maze, start, end);
        assertNotNull(distance, "Un chemin doit exister entre start et end");
        assertTrue(distance >= 12, "Distance devrait être >= 12, mais est " + distance);
    }

    @Test
    public void testLongDistanceRespected() {
        // gen un labyrinthe 30x30 avec distance minimale de 20
        MazeAlgorithmStandardLargeur algo = MazeAlgorithmStandardLargeur.getInstance();
        boolean[][] maze = algo.createMaze(30, 30, 0.4, 20);
        Position start = algo.getStart();
        Position end = algo.getEnd();

        // verif que la distance réelle respecte la distance minimale demandée
        Integer distance = BreadthFirstSearch.calculateDistance(maze, start, end);
        assertNotNull(distance, "Un chemin doit exister entre start et end");
        assertTrue(distance >= 20, "Distance devrait être >= 20, mais est " + distance);
    }

    @Test
    public void testDistanceConsistency() {
        // gen 50 labyrinthes et verif que tous respectent la distance minimale
        MazeAlgorithmStandardLargeur algo = MazeAlgorithmStandardLargeur.getInstance();
        int minDistance = 8;
        int failCount = 0;

        for (int i = 0; i < 50; i++) {
            // dimensions variables 1 fois sur 2 !
            minDistance = (i%2 == 0) ? 15 : 8 ;

            // on choisit le i aussi ; l'objectif est de tester beaucoup de combis :)
            boolean[][] maze = algo.createMaze(15+i, 15+(2*i), 0.3, minDistance);
            Position start = algo.getStart();
            Position end = algo.getEnd();

            Integer distance = BreadthFirstSearch.calculateDistance(maze, start, end);
            if (distance == null || distance < minDistance) {
                failCount++;
            }
        }

        // verif que tous les labyrinthes respectent la distance minimale
        assertEquals(0, failCount, failCount + " labyrinthes sur 50 ne respectent pas la distance minimale de " + minDistance);
    }
}
