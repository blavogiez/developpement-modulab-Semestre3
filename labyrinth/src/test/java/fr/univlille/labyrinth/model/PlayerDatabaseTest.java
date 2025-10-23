package fr.univlille.labyrinth.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.univlille.labyrinth.model.gamemode.ProgressionMode;
import fr.univlille.labyrinth.model.save.Challenge;
import fr.univlille.labyrinth.model.save.Player;
import fr.univlille.labyrinth.model.save.PlayerDatabase;
import fr.univlille.labyrinth.model.save.PlayerProgress;

// test de la persistance des joueurs
// met à l'épreuve toutes les situations possibles !
// la persistance est très importante ; elle doit être parfaite. Du TDD est également appréciable ici !
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

        // Test de la bonne sauvegarde des défis
        // Test d'un défi (étape 2, défi 2)
        Challenge unDefi = player1.getProgress().getLevelProgress()[1].getChallenges()[1];
        player1.getProgress().markChallengeCompleted(unDefi, 500);
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
    }

    @Test
    public void testLoadPlayerAndChallenge() {
        assertDoesNotThrow(() -> PlayerDatabase.savePlayer(player1));

        Player loadedPlayer = PlayerDatabase.loadPlayer(playerName1);

        assertNotNull(loadedPlayer);

        // le challenge est bien sauvegardé si l'étape maximum est égale à 2 (défi de l'étape 2)
        assertEquals(2, loadedPlayer.getHighestLevel());
    }
}