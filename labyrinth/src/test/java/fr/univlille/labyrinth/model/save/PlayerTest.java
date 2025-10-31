package fr.univlille.labyrinth.model.save;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("TestPlayer");
    }

    @Test
    void shouldStorePlayerName() {
        assertEquals("TestPlayer", player.getName());
    }

    @Test
    void shouldInitializeProgressOnCreation() {
        assertNotNull(player.getProgress());
    }

    @Test
    void shouldHaveZeroHighestLevelInitially() {
        assertEquals(0, player.getHighestLevel());
    }

    @Test
    void shouldHaveZeroScoreInitially() {
        assertEquals(0, player.getScore());
    }

    @Test
    void shouldCalculateScoreFromCompletedChallenges() {
        player.getProgress().getLevelProgress()[0].getChallenges()[0].setCompleted(true);

        assertTrue(player.getScore() > 0);
    }

    @Test
    void shouldUpdateHighestLevelWhenChallengeCompleted() {
        player.getProgress().getLevelProgress()[1].getChallenges()[0].setCompleted(true);

        assertEquals(2, player.getHighestLevel());
    }
}
