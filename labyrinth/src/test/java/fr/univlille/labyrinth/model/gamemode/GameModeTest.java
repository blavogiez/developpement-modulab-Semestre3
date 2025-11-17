package fr.univlille.labyrinth.model.gamemode;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import fr.univlille.labyrinth.model.VictoryObserver;
import fr.univlille.labyrinth.model.algorithm.MazeAlgorithmFactory;
import fr.univlille.labyrinth.model.algorithm.pathsearch.BreadthFirstSearch;
import fr.univlille.labyrinth.model.gamemode.config.FreeModeConfig;
import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.model.maze.Position;

/*
 * Test de GameMode, où un "mock" d'observer est créé.
 * La sortie est cherchée, puis remontée et nous testons si l'observeur est prévenu.
 * C'est donc également un test implicite pour les controllers, puisqu'ils sont des victory observer.
 */
public class GameModeTest {

    //mock rapide d'un observer
    private static class MockVictoryObserver implements VictoryObserver<GameMode> {
        private boolean victoryTriggered = false;

        public void onVictory() {
            this.victoryTriggered = true;
        }

        public void onDefeat(GameMode observable) {
        }

        public boolean isVictoryTriggered() {
            return victoryTriggered;
        }
    }

    @Test
    public void should_player_move_to_exit_trigger_victory() {
        FreeModeConfig config = new FreeModeConfig(MazeAlgorithmFactory.PERFECT, 50, 50, 0.4, 31);
        FreeMode gameMode = new FreeMode(config);
        gameMode.createMaze();

        MockVictoryObserver observer = new MockVictoryObserver();
        gameMode.addVictoryObserver(observer);

        assertFalse(observer.isVictoryTriggered());
        assertFalse(gameMode.getCurrentMaze().getPlayerAtExit() != null);

        ObservableMaze maze = gameMode.getCurrentMaze();
        Position start = maze.getEntryPosition();
        Position exit = maze.getExitPosition();

        List<Position> path = BreadthFirstSearch.pathFinder(maze, start, exit);
        assertNotNull(path);
        assertFalse(path.isEmpty());
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
        assertTrue(gameMode.getCurrentMaze().getPlayerAtExit() != null);
    }

    @Test
    public void should_multiple_observers_be_notified() {
        FreeModeConfig config = new FreeModeConfig(MazeAlgorithmFactory.PERFECT, 50, 50, 0.4, 30);
        FreeMode gameMode = new FreeMode(config);
        gameMode.createMaze();

        MockVictoryObserver observer1 = new MockVictoryObserver();
        MockVictoryObserver observer2 = new MockVictoryObserver();
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