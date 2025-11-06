package fr.univlille.labyrinth.model.algorithm.pathsearch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import fr.univlille.labyrinth.model.maze.Maze;

class DepthStackSearchTest {

    /*
     * Helper pour rendre un labyrinthe complet
     */
    private static void allAreTrue(boolean[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            Arrays.fill(tab[i], true);
        }
    }

    @Test
    void shouldNotFindPathInFullMaze() {
        Maze maze = new Maze(10, 10, 12);

        allAreTrue(maze.getMurHorizontaux());
        allAreTrue(maze.getMurVerticaux());
        assertFalse(DepthStackSearch.isExitPossible(maze));
    }

    @Test
    void shouldFindPathInEmptyMaze() {
        Maze maze = new Maze(15, 10, 15);

        assertTrue(DepthStackSearch.isExitPossible(maze));
    }
}