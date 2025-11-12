package fr.univlille.labyrinth.model.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import fr.univlille.labyrinth.model.algorithm.pathsearch.BreadthFirstSearch;
import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.MazeWallChecker;
import fr.univlille.labyrinth.model.maze.Position;

// Cette classe teste l'algo de generation de labyrinthe en largeur (BFS) ; c'est le labyrinthe utilisé par défaut de l'application (Demandé pour le jalon 1)
// On verifie que le labyrinthe genere respecte la distance minimale entre start et end
// et que l'algo fonctionne correctement dans differents cas!

public class PerfectAlgorithmTest {

    static MazeAlgorithm algo;
    static Maze maze1, maze2, maze3;
    static Position start1, end1, start2, end2, start3, end3;

    @BeforeAll
    public static void initialization() {
        algo = MazeAlgorithmFactory.PERFECT.getAlgorithm();

        //petit labyrinthe de taille avec distance minimale
        maze1 = new Maze(10,12,10, algo); 
        start1 = maze1.getEntryPosition();
        end1 = maze1.getExitPosition();


        // moyen labyrinthe avec petite distance
        maze2 = new Maze(40,12,20, algo); 
        start2 = maze2.getEntryPosition();
        end2 = maze2.getExitPosition();

        //grand labyrinthe avec grande distance
        maze3 = new Maze(50,32,30, algo); 
        start3 = maze3.getEntryPosition();
        end3 = maze3.getExitPosition();
    }

    @Test
    public void testStartPosition() {
        assertTrue(MazeWallChecker.positionCorrecte(start1.getY(), start1.getX(), maze1), "" + start1);

        assertTrue(MazeWallChecker.positionCorrecte(start2.getY(), start2.getX(), maze2), "" + start2);

        assertTrue(MazeWallChecker.positionCorrecte(start3.getY(), start3.getX(), maze3), "" + start3);
    }

    @Test
    public void testExitPosition() {
        assertTrue(MazeWallChecker.positionCorrecte(end1.getY(), end1.getX(), maze1));

        assertTrue(MazeWallChecker.positionCorrecte(end2.getY(), end2.getX(), maze2));

        assertTrue(MazeWallChecker.positionCorrecte(end3.getY(), end3.getX(), maze3));
    }

    @Test
    public void testStartAndEndAreDifferent() {
        //Le point de depart et d'arrivee doivent etre differents
        assertNotEquals(start1, end1);
        assertNotEquals(start2, end2);
        assertNotEquals(start3, end3);
    }

    @Test
    public void testPathExists() {
        // Il doit exister un chemin (PATH = true) entre start et end

        //(la fonction appelée retourne null si rien n'est trouvée ou la distance sinon)
        Integer distance1 = BreadthFirstSearch.calculateDistance(maze1, start1, end1);
        assertNotNull(distance1, "Un chemin doit exister entre start et end dans maze1");

        Integer distance2 = BreadthFirstSearch.calculateDistance(maze2, start2, end2);
        assertNotNull(distance2, "Un chemin doit exister entre start et end dans maze2");

        Integer distance3 = BreadthFirstSearch.calculateDistance(maze3, start3, end3);
        assertNotNull(distance3, "Un chemin doit exister entre start et end dans maze3");
    }

    @Test
    public void testMinimumPathLength() {
        // la distance BFS reelle entre start et end doit etre >= pathLength demande
        Integer distance1 = BreadthFirstSearch.calculateDistance(maze1, start1, end1);
        assertNotNull(distance1);
        assertEquals(distance1,10, "Distance dans maze1 devrait être = 10, mais est " + distance1);

        Integer distance2 = BreadthFirstSearch.calculateDistance(maze2, start2, end2);
        assertNotNull(distance2);
        assertEquals(distance2,20, "Distance dans maze2 devrait être = 20, mais est " + distance2);

        Integer distance3 = BreadthFirstSearch.calculateDistance(maze3, start3, end3);
        assertNotNull(distance3);
        assertEquals(distance3,30, "Distance dans maze3 devrait être = 30, mais est " + distance3);
    }

    @Test
    public void testDistanceConsistency() {
        // gen 50 labyrinthes et verif que tous respectent la distance minimale
        int minDistance = 8;
        int failCount = 0;

        for (int i = 0; i < 50; i++) {
            // dimensions variables 1 fois sur 2 !
            minDistance = (i%2 == 0) ? 15 : 8 ;

            // on choisit le i aussi ; l'objectif est de tester beaucoup de combis :)
            Maze maze = new Maze(15+i, 15+(2*i), minDistance);
            Position start = maze.getEntryPosition();
            Position end = maze.getExitPosition();

            Integer distance = BreadthFirstSearch.calculateDistance(maze, start, end);
            if (distance == null || distance < minDistance) {
                failCount++;
            }
        }

        // verif que tous les labyrinthes respectent la distance minimale
        assertEquals(0, failCount, failCount + " labyrinthes sur 50 ne respectent pas la distance minimale de " + minDistance);
    }
}
