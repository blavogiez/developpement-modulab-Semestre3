package fr.univlille.labyrinth.controller.input;

import fr.univlille.labyrinth.model.maze.Direction;
import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.Map;

/*
 * Classe de configuration pour les touches des joueurs
 * Utile pour le multijoueur
 */
public class PlayerInputConfig {
    private static final Map<KeyCode, Integer> keyToPlayer = new HashMap<>();
    private static final Map<KeyCode, Direction> keyToDirection = new HashMap<>();

    static {
        initializePlayer(0, KeyCode.UP, KeyCode.LEFT, KeyCode.DOWN, KeyCode.RIGHT);
        initializePlayer(1, KeyCode.Z, KeyCode.Q, KeyCode.S, KeyCode.D);
        initializePlayer(2, KeyCode.I, KeyCode.J, KeyCode.K, KeyCode.L);
    }

    private static void initializePlayer(int playerId, KeyCode up, KeyCode left, KeyCode down, KeyCode right) {
        keyToPlayer.put(up, playerId);
        keyToPlayer.put(left, playerId);
        keyToPlayer.put(down, playerId);
        keyToPlayer.put(right, playerId);

        keyToDirection.put(up, Direction.UP);
        keyToDirection.put(left, Direction.LEFT);
        keyToDirection.put(down, Direction.DOWN);
        keyToDirection.put(right, Direction.RIGHT);
    }

    public static Integer getPlayerIdForKey(KeyCode keyCode) {
        return keyToPlayer.get(keyCode);
    }

    public static Direction getDirectionForKey(KeyCode keyCode) {
        return keyToDirection.get(keyCode);
    }

    public static boolean isValidKey(KeyCode keyCode) {
        return keyToPlayer.containsKey(keyCode);
    }
}
