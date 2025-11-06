package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.model.Observer;
import fr.univlille.labyrinth.model.algorithmold.MazeAlgorithmFactory;
import fr.univlille.labyrinth.model.gamemode.FreeMode;
import fr.univlille.labyrinth.model.gamemode.GameMode;
import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.Position;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

// cette classe teste le déplacement du joueur et le déclenchement de la victoire (observé par un controleur)
// On crée un petit labyrinthe, on déplace le joueur jusqu'à la sortie, et on vérifie que l'observateur de victoire est bien appelé

public class MovePlayerVictoryTest {

    // mock simple pour détecter la victoire
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
        FreeMode gameMode = new FreeMode();
        gameMode.createMaze(MazeAlgorithmFactory.STANDARDLARGEUR,5, 5, 0.0);

        VictoryObserver observer = new VictoryObserver();
        gameMode.addVictoryObserver(observer);

        assertFalse(observer.isVictoryTriggered(), "La victoire ne devrait pas être déclenchée au début");
        assertFalse(gameMode.isPlayerAtEnd(), "Le joueur ne devrait pas être à la fin au début");

        Position start = gameMode.getCurrentMaze().getPlayerPosition();
        Position exit = gameMode.getCurrentMaze().getExitPosition();
        List<Direction> path = findPath(gameMode.getCurrentMaze().getGrid(), start, exit);

        assertNotNull(path, "Un chemin devrait exister entre l'entrée et la sortie");
        assertTrue(path.size() > 0, "Le chemin devrait avoir au moins un mouvement");

        // deplacer le joueur le long du chemin
        for (int i = 0; i < path.size() - 1; i++) {
            Direction dir = path.get(i);
            gameMode.movePlayerPosition(dir);
            assertFalse(observer.isVictoryTriggered(), "La victoire ne devrait pas être déclenchée avant d'atteindre la sortie");
        }

        // faire le dernier mouvement qui devrait déclencher la victoire
        Direction lastMove = path.get(path.size() - 1);
        gameMode.movePlayerPosition(lastMove);

        assertTrue(observer.isVictoryTriggered(), "La victoire devrait être déclenchée après avoir atteint la sortie");
        assertTrue(gameMode.isPlayerAtEnd(), "Le joueur devrait être à la fin");
        assertEquals(gameMode, observer.getNotifiedGameMode(), "L'observateur devrait recevoir le bon GameMode");
    }

    @Test
    public void testMultipleObserversAreNotified() {
        // Teste que plusieurs observateurs sont notifiés en cas de victoire
        FreeMode gameMode = new FreeMode();
        gameMode.createMaze(MazeAlgorithmFactory.STANDARDLARGEUR,5, 5, 0.0);

        VictoryObserver observer1 = new VictoryObserver();
        VictoryObserver observer2 = new VictoryObserver();
        gameMode.addVictoryObserver(observer1);
        gameMode.addVictoryObserver(observer2);

        Position start = gameMode.getCurrentMaze().getPlayerPosition();
        Position exit = gameMode.getCurrentMaze().getExitPosition();
        List<Direction> path = findPath(gameMode.getCurrentMaze().getGrid(), start, exit);

        for (Direction dir : path) {
            gameMode.movePlayerPosition(dir);
        }

        assertTrue(observer1.isVictoryTriggered(), "Le premier observateur devrait être notifié");
        assertTrue(observer2.isVictoryTriggered(), "Le deuxième observateur devrait être notifié");
    }
}
