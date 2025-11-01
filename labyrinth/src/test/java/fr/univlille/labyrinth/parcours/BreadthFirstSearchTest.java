package fr.univlille.labyrinth.parcours;

import fr.univlille.labyrinth.model.algorithm.MazeAlgorithmFactory;
import fr.univlille.labyrinth.model.maze.Maze;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BreadthFirstSearchTest {

    @Test
    void shouldFindPathInStandardMaze() {
        Maze maze = new Maze(MazeAlgorithmFactory.STANDARDLARGEUR, 10, 10, 0.2);

        Integer distance = BreadthFirstSearch.calculateDistance(
                maze.getGrid(), maze.getEntryPosition(), maze.getExitPosition());

        assertNotNull(distance);
    }

    @Test
    void shouldFindPathInSmallMaze() {
        Maze maze = new Maze(MazeAlgorithmFactory.STANDARDLARGEUR, 5, 5, 0.1);

        Integer distance = BreadthFirstSearch.calculateDistance(
                maze.getGrid(), maze.getEntryPosition(), maze.getExitPosition());

        assertNotNull(distance);
    }

    @Test
    void shouldFindPathInLargeMaze() {
        Maze maze = new Maze(MazeAlgorithmFactory.STANDARDLARGEUR, 20, 20, 0.3);

        Integer distance = BreadthFirstSearch.calculateDistance(
                maze.getGrid(), maze.getEntryPosition(), maze.getExitPosition());

        assertNotNull(distance);
    }

    @Test
    void shouldReturnPositiveDistance() {
        Maze maze = new Maze(MazeAlgorithmFactory.STANDARDLARGEUR, 10, 10, 0.2);

        Integer distance = BreadthFirstSearch.calculateDistance(
                maze.getGrid(), maze.getEntryPosition(), maze.getExitPosition());

        assertNotNull(distance);
        assertTrue(distance > 0);
    }

    @Test
    void shouldHandleDifferentMazeSizes() {
        Maze small = new Maze(MazeAlgorithmFactory.STANDARDLARGEUR, 5, 5, 0.1);
        Maze medium = new Maze(MazeAlgorithmFactory.STANDARDLARGEUR, 10, 10, 0.2);
        Maze large = new Maze(MazeAlgorithmFactory.STANDARDLARGEUR, 20, 20, 0.3);

        Integer distanceSmall = BreadthFirstSearch.calculateDistance(
                small.getGrid(), small.getEntryPosition(), small.getExitPosition());
        Integer distanceMedium = BreadthFirstSearch.calculateDistance(
                medium.getGrid(), medium.getEntryPosition(), medium.getExitPosition());
        Integer distanceLarge = BreadthFirstSearch.calculateDistance(
                large.getGrid(), large.getEntryPosition(), large.getExitPosition());

        assertNotNull(distanceSmall);
        assertNotNull(distanceMedium);
        assertNotNull(distanceLarge);
    }
}
