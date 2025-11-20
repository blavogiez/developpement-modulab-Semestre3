package fr.univlille.labyrinth.controller.input;

import fr.univlille.labyrinth.model.maze.Direction;
import javafx.scene.input.KeyCode;

import java.util.EnumMap;
import java.util.Map;

/*
 * Classe de configuration pour les touches des joueurs
 * Utile pour le multijoueur
 */
public abstract class PlayerInputConfig {
    private PlayerInputConfig(){}
    private static final Map<KeyCode, Integer> keyToPlayer = new EnumMap<>(KeyCode.class);
    private static final Map<KeyCode, Direction> keyToDirection = new EnumMap<>(KeyCode.class);

    static {
        initializePlayer(0, KeyCode.UP, KeyCode.LEFT, KeyCode.DOWN, KeyCode.RIGHT);
        initializePlayer(1, KeyCode.Z, KeyCode.Q, KeyCode.S, KeyCode.D);
        initializePlayer(2, KeyCode.I, KeyCode.J, KeyCode.K, KeyCode.L);
    }

    /** 
     * @param playerId
     * @param up
     * @param left
     * @param down
     * @param right
     */
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

    /** 
     * @param keyCode
     * @return Integer
     */
    public static Integer getPlayerIdForKey(KeyCode keyCode) {
        return keyToPlayer.get(keyCode);
    }

    /** 
     * @param keyCode
     * @return Direction
     */
    public static Direction getDirectionForKey(KeyCode keyCode) {
        return keyToDirection.get(keyCode);
    }

    /** 
     * @param keyCode
     * @return boolean
     */
    public static boolean isValidKey(KeyCode keyCode) {
        return keyToPlayer.containsKey(keyCode);
    }
}
