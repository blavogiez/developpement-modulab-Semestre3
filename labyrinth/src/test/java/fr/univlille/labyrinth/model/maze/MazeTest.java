package fr.univlille.labyrinth.model.maze;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fr.univlille.labyrinth.model.algorithm.pathsearch.DepthStackSearch;

class MazeTest {

    @Test
    void should_store_width() {
        Maze maze = new Maze(15, 10, 15);

        assertEquals(15, maze.getWidth());
    }

    @Test
    void should_store_height() {
        Maze maze = new Maze(15, 20, 15);

        assertEquals(20, maze.getHeight());
    }

    @Test
    void should_vertical_array_store_height() {
        Maze maze = new Maze(15, 20, 15);

        assertEquals(maze.getWidth() - 1, maze.getMurVerticaux().length);
        assertEquals(14, maze.getMurVerticaux().length);
    }

    @Test
    void should_horizontal_array_store_width() {
        Maze maze = new Maze(15, 20, 15);

        assertEquals(maze.getHeight() - 1, maze.getMurHorizontaux().length);
        assertEquals(19, maze.getMurHorizontaux().length);
    }

    @Test
    void should_have_entry_and_exit_positions() {
        Maze maze = new Maze(10, 10, 12);

        assertNotNull(maze.getEntryPosition());
        assertNotNull(maze.getExitPosition());
    }

    @Test
    void should_have_path_between_entry_and_exit() {
        Maze maze = new Maze(12, 10, 12);
        assertTrue(DepthStackSearch.isExitPossible(maze));
    }
}
