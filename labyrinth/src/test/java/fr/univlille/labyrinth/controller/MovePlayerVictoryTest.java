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
        assertFalse(gameMode.getCurrentMaze().isPlayerAtExit());

        ObservableMaze maze = gameMode.getCurrentMaze();
        Position start = maze.getEntryPosition();
        Position exit = maze.getExitPosition();

        List<Position> path = BreadthFirstSearch.pathFinder(maze, start, exit);
        assertNotNull(path, "A path should be found from start to exit.");
        assertFalse(path.isEmpty(), "Path should not be empty.");
        path.add(0, start);

        for (Position targetPosition : path) {
            Position currentPlayerPos = maze.getEntityManager().getPlayerEntityByID(0).getPosition();

            if (currentPlayerPos.equals(targetPosition)) {
                continue;
            }

            Direction direction = determineDirectionStep(currentPlayerPos, targetPosition);
            gameMode.movePlayerPosition(0,direction);

            if (!maze.getEntityManager().getPlayerEntityByID(0).getPosition().equals(exit)) {
                assertFalse(observer.isVictoryTriggered());
            }
        }

        assertTrue(observer.isVictoryTriggered());
        assertTrue(gameMode.getCurrentMaze().isPlayerAtExit());
        assertEquals(gameMode, observer.getNotifiedGameMode());
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
        Position start = maze.getEntryPosition();
        Position exit = maze.getExitPosition();

        List<Position> path = BreadthFirstSearch.pathFinder(maze, start, exit);
        assertNotNull(path);
        assertFalse(path.isEmpty());
        
        for (Position targetPosition : path) {
            Position currentPlayerPos = maze.getEntityManager().getPlayerEntityByID(0).getPosition();

            if (currentPlayerPos.equals(targetPosition)) {
                continue;
            }

            Direction direction = determineDirectionStep(currentPlayerPos, targetPosition);
            gameMode.movePlayerPosition(0, direction);
        }

        assertTrue(observer1.isVictoryTriggered());
        assertTrue(observer2.isVictoryTriggered());
    }

    /** 
     * @param from
     * @param to
     * @return Direction
     */
    // methode helper
    private Direction determineDirectionStep(Position from, Position to) {
        int dx = to.getX() - from.getX();
        int dy = to.getY() - from.getY();

        if (dx == 1 && dy == 0) return Direction.RIGHT;
        if (dx == -1 && dy == 0) return Direction.LEFT;
        if (dx == 0 && dy == 1) return Direction.DOWN;
        if (dx == 0 && dy == -1) return Direction.UP;

        throw new IllegalArgumentException("Positions non adjacentes : " + from + " to " + to);
    }
}