package fr.univlille.labyrinth.model.algorithmold;

import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.parcours.BreadthFirstSearch;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Cette classe teste l'algo de generation de labyrinthe en largeur (BFS) ; c'est le labyrinthe utilisé par défaut de l'application (Demandé pour le jalon 1)
// On verifie que le labyrinthe genere respecte la distance minimale entre start et end
// et que l'algo fonctionne correctement dans differents cas!

public class MazeAlgorithmStandardLargeurTest {

    static MazeAlgorithmStandardLargeur algo;
    static boolean[][] maze1, maze2, maze3;
    static Position start1, end1, start2, end2, start3, end3;

    @BeforeAll
    public static void initialization() {
        algo = MazeAlgorithmStandardLargeur.getInstance();

        //labyrinthe de taille moyenne avec distance minimale moderee
        maze1 = algo.createMaze(15, 15, 0.3, 8);
        start1 = algo.getStart();
        end1 = algo.getEnd();

        // Petit labyrinthe avec petite distance
        maze2 = algo.createMaze(7, 7, 0.2, 3);
        start2 = algo.getStart();
        end2 = algo.getEnd();

        //grand labyrinthe avec grande distance
        maze3 = algo.createMaze(25, 25, 0.4, 15);
        start3 = algo.getStart();
        end3 = algo.getEnd();
    }

    @Test
    public void testStartPosition() {
        assertTrue(start1.getX() > 0 && start1.getX() < maze1.length - 1);
        assertTrue(start1.getY() > 0 && start1.getY() < maze1[0].length - 1);
        assertTrue(start2.getX() > 0 && start2.getX() < maze2.length - 1);
        assertTrue(start2.getY() > 0 && start2.getY() < maze2[0].length - 1);
        assertTrue(start3.getX() > 0 && start3.getX() < maze3.length - 1);
        assertTrue(start3.getY() > 0 && start3.getY() < maze3[0].length - 1);
    }

    @Test
    public void testStartAndEndAreDifferent() {
        //Le point de depart et d'arrivee doivent etre differents
        assertNotEquals(start1, end1);
        assertNotEquals(start2, end2);
        assertNotEquals(start3, end3);
    }

    @Test
    public void testStartAndEndAreInsideBounds() {
        assertTrue(start1.getX()>0 && start1.getX() < maze1.length-1);
        assertTrue(start1.getY()>0 && start1.getY() < maze1[0].length-1);
        assertTrue(end1.getX()>0 && end1.getX() < maze1.length-1);
        assertTrue(end1.getY()>0 && end1.getY() < maze1[0].length-1);

        assertTrue(start2.getX() > 0 && start2.getX()<maze2.length - 1);
        assertTrue(start2.getY() > 0 && start2.getY()<maze2[0].length - 1);
        assertTrue(end2.getX() > 0 && end2.getX()<maze2.length - 1);
        assertTrue(end2.getY() > 0 && end2.getY()<maze2[0].length - 1);

        assertTrue(start3.getX() > 0 && start3.getX() < maze3.length - 1);
        assertTrue(start3.getY() > 0 && start3.getY() < maze3[0].length - 1);
        assertTrue(end3.getX() > 0 && end3.getX() < maze3.length - 1);
        assertTrue(end3.getY() > 0 && end3.getY() < maze3[0].length - 1);
    }

    @Test
    public void testPathExists() {
        // Il doit exister un chemin (PATH = true) entre start et end

        //(la fonction appelée retourne null si rien n'est trouvée ou la distance sinon)
        Integer distance1 = BreadthFirstSearch.calculateAllDistances(maze1, start1, end1);
        assertNotNull(distance1, "Un chemin doit exister entre start et end dans maze1");

        Integer distance2 = BreadthFirstSearch.calculateAllDistances(maze2, start2, end2);
        assertNotNull(distance2, "Un chemin doit exister entre start et end dans maze2");

        Integer distance3 = BreadthFirstSearch.calculateAllDistances(maze3, start3, end3);
        assertNotNull(distance3, "Un chemin doit exister entre start et end dans maze3");
    }

    @Test
    public void testMinimumPathLength() {
        // la distance BFS reelle entre start et end doit etre >= pathLength demande
        Integer distance1 = BreadthFirstSearch.calculateDistance(maze1, start1, end1);
        assertNotNull(distance1);
        assertTrue(distance1 >= 8, "Distance dans maze1 devrait être >= 8, mais est " + distance1);

        Integer distance2 = BreadthFirstSearch.calculateDistance(maze2, start2, end2);
        assertNotNull(distance2);
        assertTrue(distance2>=3, "Distance dans maze2 devrait être >= 3, mais est " + distance2);

        Integer distance3 = BreadthFirstSearch.calculateDistance(maze3, start3, end3);
        assertNotNull(distance3);
        assertTrue(distance3 >= 15, "Distance dans maze3 devrait être >= 15, mais est " + distance3);
    }

    @Test
    public void testSingletonPattern() {
        // getInstance() doit toujours retourner la meme instance
        MazeAlgorithmStandardLargeur instance1 = MazeAlgorithmStandardLargeur.getInstance();
        MazeAlgorithmStandardLargeur instance2 = MazeAlgorithmStandardLargeur.getInstance();
        assertSame(instance1, instance2);
        assertSame(algo, instance1);
    }

    @Test
    public void testWithImpossiblePathLength() {
        // quand la distance demandee est trop grande, l'algo doit soit trouver la distance max possible
        // soit lancer une exception (selon l'implementation)
        MazeAlgorithmStandardLargeur testAlgo = MazeAlgorithmStandardLargeur.getInstance();

        // Pour un tres petit labyrinthe, une grande distance devrait soit fonctionner avec distance max
        // soit lancer MazeSizeException
        try {
            boolean[][] maze = testAlgo.createMaze(5, 5, 0.1, 100);
            Position start = testAlgo.getStart();
            Position end = testAlgo.getEnd();

            // si ca ne lance pas d'exception, verifier qu'un chemin existe quand meme
            Integer distance = BreadthFirstSearch.calculateDistance(maze, start, end);
            assertNotNull(distance, "Un chemin devrait exister même si pathLength est trop grand");
        } catch (MazeSizeException e) {
            // l'exception est acceptable dans ce cas
            assertTrue(true);
        }
    }
}
