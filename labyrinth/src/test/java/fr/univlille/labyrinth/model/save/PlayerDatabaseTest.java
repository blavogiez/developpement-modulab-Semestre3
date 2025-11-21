package fr.univlille.labyrinth.model.save;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerDatabaseTest {
    static Player player1, player2, player3, player4;
    static String playerName1, playerName2, playerName3, playerName4, nonExistentPlayerName;

    @BeforeAll
    static void initialization() {
        playerName1 = "TestPlayer1";
        playerName2 = "TestPlayer2";
        playerName3 = "TestPlayer3";
        playerName4 = "Admin" ;
        nonExistentPlayerName = "NonExistentPlayer";

        player1 = new Player(playerName1);
        player2 = new Player(playerName2);
        player3 = new Player(playerName3);
        player4 = new Player(playerName4);

        Challenge unDefi = player1.getProgress().getLevelProgress()[1].getChallenges()[1];
        player1.getProgress().markChallengeCompleted(unDefi, 500);
    }

    // joueur "Admin" avec accès à tout pour debugger apres
    @AfterAll
    static void admin() {
        for(int i = 0 ; i < 9 ; i++){
            for (int j = 0 ; j < 3 ; j++) {
                Challenge unDefi = player4.getProgress().getLevelProgress()[i].getChallenges()[j];
                player4.getProgress().markChallengeCompleted(unDefi, 500);
                System.out.println(unDefi);
            }
            
        }
        PlayerDatabase.savePlayer(player4);
    }

    @BeforeEach
    void setUp() {
        PlayerDatabase.clear();
    }

    @Test
    void testSavePlayer() {
        assertDoesNotThrow(() -> PlayerDatabase.savePlayer(player1));
        assertTrue(PlayerDatabase.playerExists(playerName1));
    }

    @Test
    void testSaveMultiplePlayers() {
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
    void testLoadPlayer() {
        assertDoesNotThrow(() -> PlayerDatabase.savePlayer(player1));

        Player loadedPlayer = PlayerDatabase.loadPlayer(playerName1);

        assertNotNull(loadedPlayer);
        assertEquals(playerName1, loadedPlayer.getName());
    }

    @Test
    void testLoadNonExistentPlayer() {
        Player result = PlayerDatabase.loadPlayer(nonExistentPlayerName);
        assertNull(result);
    }

    @Test
    void testPlayerExists() {
        assertDoesNotThrow(() -> PlayerDatabase.savePlayer(player1));

        assertTrue(PlayerDatabase.playerExists(playerName1));
        assertFalse(PlayerDatabase.playerExists(nonExistentPlayerName));
    }

    @Test
    void testPlayerExistsAfterSave() {
        assertFalse(PlayerDatabase.playerExists(playerName2));

        assertDoesNotThrow(() -> PlayerDatabase.savePlayer(player2));

        assertTrue(PlayerDatabase.playerExists(playerName2));
    }

    @Test
    void testLoadPlayerVerifyAllProperties() {
        assertDoesNotThrow(() -> PlayerDatabase.savePlayer(player1));

        Player loadedPlayer = PlayerDatabase.loadPlayer(playerName1);

        assertNotNull(loadedPlayer);
        assertEquals(player1.getName(), loadedPlayer.getName());
    }

    @Test
    void testLoadPlayerAndChallenge() {
        assertDoesNotThrow(() -> PlayerDatabase.savePlayer(player1));

        Player loadedPlayer = PlayerDatabase.loadPlayer(playerName1);

        assertNotNull(loadedPlayer);

        assertEquals(2, loadedPlayer.getHighestLevel());
    }

    // test si le joueur est mis à jour par son nom après une autre session de jeu théorique
    @Test
    void testUpdatePlayer() {
        PlayerDatabase.savePlayer(player1);

        Player autreSessionDuPlayer1 = new Player("TestPlayer1");
        Challenge unDefi = autreSessionDuPlayer1.getProgress().getLevelProgress()[0].getChallenges()[1];
        autreSessionDuPlayer1.getProgress().markChallengeCompleted(unDefi, 1000);

        PlayerDatabase.savePlayer(autreSessionDuPlayer1);

        List<Player> charges = PlayerDatabase.loadAllPlayers();
        assertEquals(1, charges.size());
    }
}