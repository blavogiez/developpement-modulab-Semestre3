package fr.univlille.labyrinth.model.maze;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

import fr.univlille.labyrinth.model.algorithm.MazeAlgorithmFactory;
import fr.univlille.labyrinth.model.algorithm.pathsearch.DepthStackSearch;

class MazeTest {

//    @Test //TODO ObservableMaze
//    void shouldInitializeGridWithCorrectDimensions() {
//        Maze maze = new Maze(10, 10, 12);
//
//        assertNotNull(maze.getGrid());
//        assertEquals(10, maze.getGrid().length);
//        assertEquals(10, maze.getGrid()[0].length);
//    }

    @Test
    void shouldStoreWidth() {
        Maze maze = new Maze(15, 10, 15);

        assertEquals(15, maze.getWidth());
    }

    @Test
    void shouldStoreHeight() {
        Maze maze = new Maze(15, 20, 15);

        assertEquals(20, maze.getHeight());
    }

    @Test
    void shouldVerticalArrayStoreHeight() {
        Maze maze = new Maze(15, 20, 15);

        assertEquals(maze.getWidth() - 1, maze.getMurVerticaux().length);
        assertEquals(14, maze.getMurVerticaux().length);
    }

    @Test
    void shouldHorizontalArrayStoreWidth() {
        Maze maze = new Maze(15, 20, 15);

        assertEquals(maze.getHeight() - 1, maze.getMurHorizontaux().length);
        assertEquals(19, maze.getMurHorizontaux().length);
    }

    @Test
    void shouldHaveEntryAndExitPositions() {
        Maze maze = new Maze(10, 10, 12);

        assertNotNull(maze.getEntryPosition());
        assertNotNull(maze.getExitPosition());
    }

    @Test
    void shouldHavePathBetweenEntryAndExit() {
        Maze maze = new Maze(12, 10, 12);
        assertTrue(DepthStackSearch.isExitPossible(maze));
    }
}
