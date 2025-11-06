package fr.univlille.labyrinth.model.maze;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fr.univlille.labyrinth.model.algorithm.pathsearch.DepthStackSearch;

class MazeTest {

    @Test
    void shouldInitializeGridWithCorrectDimensions() {
        Maze maze = new PlayerMaze(10, 10, 12);

        assertNotNull(maze.getGrid());
        assertEquals(10, maze.getGrid().length);
        assertEquals(10, maze.getGrid()[0].length);
    }

    @Test
    void shouldStoreWidth() {
        Maze maze = new PlayerMaze(15, 10, 15);

        assertEquals(15, maze.getWidth());
    }

    @Test
    void shouldStoreHeight() {
        Maze maze = new PlayerMaze(15, 20, 15);

        assertEquals(20, maze.getHeight());
    }

    @Test
    void shouldVerticalArrayStoreHeight() {
        Maze maze = new PlayerMaze(15, 20, 15);

        assertEquals(maze.getWidth() - 1, maze.getMurVerticaux().length);
        assertEquals(14, maze.getMurVerticaux().length);
    }

    @Test
    void shouldHorizontalArrayStoreWidth() {
        Maze maze = new PlayerMaze(15, 20, 15);

        assertEquals(maze.getHeight() - 1, maze.getMurHorizontaux().length);
        assertEquals(19, maze.getMurHorizontaux().length);
    }

    // @Test
    // void shouldRespectWallPercentageStatistically() {
    //     int totalWalls = 0;
    //     int totalCells = 0;
    //     double targetPercentage = 0.4;

    //     double seuilOuCestOk = 0.05 ;

    //     for (int i = 0; i < 100; i++) {
    //         Maze maze = new Maze(MazeAlgorithmFactory.STANDARDLARGEUR, 20, 20, targetPercentage);
    //         int walls = 0;

    //         for (int x = 1; x < maze.getWidth() - 1; x++) {
    //             for (int y = 1; y < maze.getHeight() - 1; y++) {
    //                 if (!maze.getGrid()[x][y]) {
    //                     walls++;
    //                 }
    //             }
    //         }

    //         totalWalls += walls;
    //         totalCells += (maze.getWidth() - 2) * (maze.getHeight() - 2);
    //     }

    //     double actualPercentage = (double) totalWalls / totalCells;
    //     assertTrue(Math.abs(actualPercentage - targetPercentage) < seuilOuCestOk);
    // }

    @Test
    void shouldHaveEntryAndExitPositions() {
        Maze maze = new PlayerMaze(10, 10, 12);

        assertNotNull(maze.getEntryPosition());
        assertNotNull(maze.getExitPosition());
    }

    @Test
    void shouldHavePathBetweenEntryAndExit() {
        Maze maze = new PlayerMaze(12, 10, 12);
        assertTrue(DepthStackSearch.isExitPossible(maze));
    }
}
