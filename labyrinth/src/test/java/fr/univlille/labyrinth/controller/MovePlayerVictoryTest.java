package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.model.Observer;
import fr.univlille.labyrinth.model.gamemode.FreeMode;
import fr.univlille.labyrinth.model.gamemode.GameMode;
import fr.univlille.labyrinth.model.gamemode.config.FreeModeConfig;
import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;
import fr.univlille.labyrinth.model.algorithm.MazeAlgorithmFactory;
import fr.univlille.labyrinth.model.algorithm.pathsearch.BreadthFirstSearch;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MovePlayerVictoryTest {

    //mock rapide d'un observer
    private static class VictoryObserver implements Observer<GameMode> {
        private boolean victoryTriggered = false;
        private GameMode notifiedGameMode = null;

        @Override
        public void update(GameMode gameMode) {
            victoryTriggered = true;
            notifiedGameMode = gameMode;
        }

        public boolean isVictoryTriggered() {
            return victoryTriggered;
        }

        public GameMode getNotifiedGameMode() {
            return notifiedGameMode;
        }
    }

    @Test
    public void testMovePlayerToExitTriggersVictory() {
        FreeModeConfig config = new FreeModeConfig(MazeAlgorithmFactory.PERFECT, 50, 50, 0.4, 30);
        FreeMode gameMode = new FreeMode(config);
        gameMode.createMaze();

        VictoryObserver observer = new VictoryObserver();
        gameMode.addVictoryObserver(observer);

        assertFalse(observer.isVictoryTriggered());
        assertFalse(gameMode.isPlayerAtEnd());

        ObservableMaze maze = gameMode.getCurrentMaze();
        Position start = maze.getPlayerPosition();
        Position exit = maze.getExitPosition();

        // Use BFS to find path from current player position to exit
        List<Position> path = BreadthFirstSearch.pathFinder(maze, start, exit);
        assertNotNull(path, "A path should be found from start to exit.");
        assertFalse(path.isEmpty(), "Path should not be empty.");

        // Execute the moves in the path
        for (Position targetPosition : path) {
            Position currentPlayerPos = maze.getPlayerPosition();

            if (currentPlayerPos.equals(targetPosition)) {
                continue; // Already at this position or overshoot corrected
            }

            Direction direction = determineDirectionStep(currentPlayerPos, targetPosition);
            gameMode.movePlayerPosition(direction);

            // If this was not the last move (not at exit yet), victory should not be triggered
            if (!maze.getPlayerPosition().equals(exit)) {
                assertFalse(observer.isVictoryTriggered(),
                    "Victory should not be triggered before reaching the exit");
            }
        }

        assertTrue(observer.isVictoryTriggered(), "Victory should be triggered when reaching the exit");
        assertTrue(gameMode.isPlayerAtEnd(), "Player should be at the end");
        assertEquals(gameMode, observer.getNotifiedGameMode(), "Observer should receive the correct game mode");
    }

    @Test
    public void testMultipleObserversAreNotified() {
        FreeModeConfig config = new FreeModeConfig(MazeAlgorithmFactory.PERFECT, 50, 50, 0.4, 30);
        FreeMode gameMode = new FreeMode(config);
        gameMode.createMaze();

        VictoryObserver observer1 = new VictoryObserver();
        VictoryObserver observer2 = new VictoryObserver();
        gameMode.addVictoryObserver(observer1);
        gameMode.addVictoryObserver(observer2);

        ObservableMaze maze = gameMode.getCurrentMaze();
        Position start = maze.getPlayerPosition();
        Position exit = maze.getExitPosition();

        List<Position> path = BreadthFirstSearch.pathFinder(maze, start, exit);
        assertNotNull(path, "A path should be found from start to exit.");
        assertFalse(path.isEmpty(), "Path should not be empty.");

        // Execute the moves in the path
        for (Position targetPosition : path) {
            Position currentPlayerPos = maze.getPlayerPosition();

            if (currentPlayerPos.equals(targetPosition)) {
                continue; // Already at this position or overshoot corrected
            }

            Direction direction = determineDirectionStep(currentPlayerPos, targetPosition);
            gameMode.movePlayerPosition(direction);
        }

        assertTrue(observer1.isVictoryTriggered(), "First observer should be notified");
        assertTrue(observer2.isVictoryTriggered(), "Second observer should be notified");
    }

    // Determine the single step direction between two adjacent positions
    private Direction determineDirectionStep(Position from, Position to) {
        int dx = to.getX() - from.getX();
        int dy = to.getY() - from.getY();

        if (dx == 1 && dy == 0) return Direction.RIGHT;
        if (dx == -1 && dy == 0) return Direction.LEFT;
        if (dx == 0 && dy == 1) return Direction.DOWN;
        if (dx == 0 && dy == -1) return Direction.UP;

        throw new IllegalArgumentException("Positions are not adjacent: " + from + " to " + to);
    }
}