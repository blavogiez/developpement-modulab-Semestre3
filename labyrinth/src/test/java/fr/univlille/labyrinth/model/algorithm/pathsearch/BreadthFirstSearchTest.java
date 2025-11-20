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
    void should_find_distance() {
            for (int distance = 5; distance <25; distance++){
                Maze maze = new Maze(10, 10, distance);
                assertEquals(distance, MazeDistance.calculateDistance(maze, maze.getEntryPosition(), maze.getExitPosition()));
            }
        }
}