package fr.univlille.labyrinth.model.maze;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fr.univlille.labyrinth.model.algorithm.pathsearch.DepthStackSearch;

/*
 * Test vis-à-vis de la substitusion de Liskov dans le cas du labyrinthe
 * ici nous testons si un ObservableMaze répond effectivement aux mêmes spécifications que sa classe parent, maze
 * Cela garantit la liaison entre les deux classes.
 * 
 * les tests sont les mêmes que pour Maze.
 */

class LiskovMazeTest {

    @Test
    void shouldStoreWidth() {
        ObservableMaze maze = new ObservableMaze(15, 10, 15);

        assertEquals(15, maze.getWidth());
    }

    @Test
    void shouldStoreHeight() {
        ObservableMaze maze = new ObservableMaze(15, 20, 15);

        assertEquals(20, maze.getHeight());
    }

    @Test
    void shouldVerticalArrayStoreHeight() {
        ObservableMaze maze = new ObservableMaze(15, 10, 15);

        assertEquals(maze.getWidth() - 1, maze.getMurVerticaux().length);
        assertEquals(14, maze.getMurVerticaux().length);
    }

    @Test
    void shouldHorizontalArrayStoreWidth() {
        ObservableMaze maze = new ObservableMaze(15, 20, 15);

        assertEquals(maze.getHeight() - 1, maze.getMurHorizontaux().length);
        assertEquals(19, maze.getMurHorizontaux().length);
    }

    @Test
    void shouldHaveEntryAndExitPositions() {
        ObservableMaze maze = new ObservableMaze(15, 10, 15);

        assertNotNull(maze.getEntryPosition());
        assertNotNull(maze.getExitPosition());
    }

    @Test
    void shouldHavePathBetweenEntryAndExit() {
        ObservableMaze maze = new ObservableMaze(15, 20, 15);
        assertTrue(DepthStackSearch.isExitPossible(maze));
    }
}

