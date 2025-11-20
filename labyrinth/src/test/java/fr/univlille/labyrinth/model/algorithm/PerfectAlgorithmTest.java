package fr.univlille.labyrinth.model.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import fr.univlille.labyrinth.model.algorithm.pathsearch.MazeDistance;
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
    public void start_position_should_be_correct() {
        assertTrue(MazeWallChecker.positionCorrecte(start1.getY(), start1.getX(), maze1), "" + start1);

        assertTrue(MazeWallChecker.positionCorrecte(start2.getY(), start2.getX(), maze2), "" + start2);

        assertTrue(MazeWallChecker.positionCorrecte(start3.getY(), start3.getX(), maze3), "" + start3);
    }

    @Test
    public void exit_position_should_be_correct() {
        assertTrue(MazeWallChecker.positionCorrecte(end1.getY(), end1.getX(), maze1));

        assertTrue(MazeWallChecker.positionCorrecte(end2.getY(), end2.getX(), maze2));

        assertTrue(MazeWallChecker.positionCorrecte(end3.getY(), end3.getX(), maze3));
    }

    @Test
    public void start_and_exit_position_should_be_different() {
        assertNotEquals(start1, end1);
        assertNotEquals(start2, end2);
        assertNotEquals(start3, end3);
    }

    @Test
    public void should_a_path_exist_between_start_and_exit() {
        Integer distance1 = MazeDistance.calculateDistance(maze1, start1, end1);
        assertNotNull(distance1, "Un chemin doit exister entre start et end dans maze1");

        Integer distance2 = MazeDistance.calculateDistance(maze2, start2, end2);
        assertNotNull(distance2, "Un chemin doit exister entre start et end dans maze2");

        Integer distance3 = MazeDistance.calculateDistance(maze3, start3, end3);
        assertNotNull(distance3, "Un chemin doit exister entre start et end dans maze3");
    }

    @Test
    public void path_should_have_minimum_length() {
        Integer distance1 = MazeDistance.calculateDistance(maze1, start1, end1);
        assertNotNull(distance1);
        assertEquals(distance1,10, "Distance dans maze1 devrait être = 10, mais est " + distance1);

        Integer distance2 = MazeDistance.calculateDistance(maze2, start2, end2);
        assertNotNull(distance2);
        assertEquals(distance2,20, "Distance dans maze2 devrait être = 20, mais est " + distance2);

        Integer distance3 = MazeDistance.calculateDistance(maze3, start3, end3);
        assertNotNull(distance3);
        assertEquals(distance3,30, "Distance dans maze3 devrait être = 30, mais est " + distance3);
    }

    @Test
    public void should_calculate_distance() {
        int minDistance;
        int failCount = 0;

        for (int i = 0; i < 50; i++) {
            minDistance = (i%2 == 0) ? 15 : 8 ;
            int taille_1 = 15+i;
            int taille_2 = 15+(2*i);
            Maze maze;
            if (i%2 == 0) maze = new Maze(taille_1, taille_2, minDistance);
            else maze = new Maze(taille_2, taille_1, minDistance);
            Position start = maze.getEntryPosition();
            Position end = maze.getExitPosition();

            Integer distance = MazeDistance.calculateDistance(maze, start, end);
            if (distance == null || distance < minDistance) {
                failCount++;
            }
        }

        // verif que tous les labyrinthes respectent la distance minimale
        assertEquals(0, failCount, failCount + " labyrinthes sur 50 ne respectent pas la distance minimale");
    }
}
