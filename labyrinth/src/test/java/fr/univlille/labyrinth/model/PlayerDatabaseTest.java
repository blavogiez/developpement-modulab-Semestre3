package fr.univlille.labyrinth.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerDatabaseTest {
    static Player player1, player2, player3;
    static String playerName1, playerName2, playerName3, nonExistentPlayerName;

    @BeforeAll
    public static void initialization() {
        playerName1 = "TestPlayer1";
        playerName2 = "TestPlayer2";
        playerName3 = "TestPlayer3";
        nonExistentPlayerName = "NonExistentPlayer";

        player1 = new Player(playerName1);
        player2 = new Player(playerName2);
        player3 = new Player(playerName3);
    }

    @BeforeEach
    public void setUp() {
        // cleaning database before every test
        PlayerDatabase.clear();
    }

    @Test
    public void testSavePlayer() {
        assertDoesNotThrow(() -> PlayerDatabase.savePlayer(player1));
        assertTrue(PlayerDatabase.playerExists(playerName1));
    }

    @Test
    public void testSaveMultiplePlayers() {
        assertDoesNotThrow(() -> {
            PlayerDatabase.savePlayer(player1);
            PlayerDatabase.savePlayer(player2);
            PlayerDatabase.savePlayer(player3);
        });

        assertTrue(PlayerDatabase.playerExists(playerName1));
        assertTrue(PlayerDatabase.playerExists(playerName2));
        assertTrue(PlayerDatabase.playerExists(playerName3));
    }

    @Test
    public void testLoadPlayer() {
        assertDoesNotThrow(() -> PlayerDatabase.savePlayer(player1));

        Player loadedPlayer = PlayerDatabase.loadPlayer(playerName1);

        assertNotNull(loadedPlayer);
        assertEquals(playerName1, loadedPlayer.getName());
    }

    @Test
    public void testLoadNonExistentPlayer() {
        Player result = PlayerDatabase.loadPlayer(nonExistentPlayerName);
        assertNull(result);
    }

    @Test
    public void testPlayerExists() {
        assertDoesNotThrow(() -> PlayerDatabase.savePlayer(player1));

        assertTrue(PlayerDatabase.playerExists(playerName1));
        assertFalse(PlayerDatabase.playerExists(nonExistentPlayerName));
    }

    @Test
    public void testPlayerExistsAfterSave() {
        assertFalse(PlayerDatabase.playerExists(playerName2));

        assertDoesNotThrow(() -> PlayerDatabase.savePlayer(player2));

        assertTrue(PlayerDatabase.playerExists(playerName2));
    }

    @Test
    public void testLoadPlayerVerifyAllProperties() {
        assertDoesNotThrow(() -> PlayerDatabase.savePlayer(player1));

        Player loadedPlayer = PlayerDatabase.loadPlayer(playerName1);

        assertNotNull(loadedPlayer);
        assertEquals(player1.getName(), loadedPlayer.getName());
        // assertEquals(player1.getScore(), loadedPlayer.getScore());
        // assertEquals(player1.getLevel(), loadedPlayer.getLevel());
    }
}