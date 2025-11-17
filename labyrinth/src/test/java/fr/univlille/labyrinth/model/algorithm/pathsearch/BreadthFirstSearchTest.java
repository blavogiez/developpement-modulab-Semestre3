package fr.univlille.labyrinth.model.algorithm.pathsearch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import fr.univlille.labyrinth.model.maze.Maze;

class BreadthFirstSearchTest {

    @Test
    void shouldFindDistance() {
        Maze maze = new Maze(10, 10, 12);

        assertEquals(12, MazeDistance.calculateDistance(maze, maze.getEntryPosition(), maze.getExitPosition()));
    }
}