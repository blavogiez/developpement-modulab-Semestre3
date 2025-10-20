package fr.univlille.labyrinth.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerProgressTest {

    private PlayerProgress progress;
    private World[] worlds;

    @BeforeEach
    void setUp() {
        worlds = new World[2];
        for (int i = 0; i < worlds.length; i++) {
            worlds[i] = new World(i + 1);
            for (int j = 0; j < 3; j++) {
                worlds[i].getChallenges()[j] = new Challenge("Diff" + j, 10, 10, 20);
            }
        }
        progress = new PlayerProgress(worlds);
    }

    @Test
    void testConstructorStoresWorlds() {
        assertNotNull(progress.getStageProgress());
        assertEquals(2, progress.getStageProgress().length);
    }

    @Test
    void testCopyCreatesDeepCopy() {
        PlayerProgress copy = progress.copy();

        assertNotSame(progress.getStageProgress(), copy.getStageProgress());

        for (int i = 0; i < worlds.length; i++) {
            World originalWorld = progress.getStageProgress()[i];
            World copiedWorld = copy.getStageProgress()[i];
            assertNotSame(originalWorld, copiedWorld);
            assertEquals(originalWorld.getNumber(), copiedWorld.getNumber());

            for (int j = 0; j < 3; j++) {
                Challenge origChall = originalWorld.getChallenges()[j];
                Challenge copiedChall = copiedWorld.getChallenges()[j];
                assertNotSame(origChall, copiedChall);
                assertEquals(origChall.getDifficulty(), copiedChall.getDifficulty());
                assertEquals(origChall.getWidth(), copiedChall.getWidth());
                assertEquals(origChall.getHeight(), copiedChall.getHeight());
                assertEquals(origChall.getWallPercentage(), copiedChall.getWallPercentage());
            }
        }
    }

    @Test
    void testMarkChallengeCompleted_FirstTimeSetsTime() {
        Challenge challenge = worlds[0].getChallenges()[0];
        progress.markChallengeCompleted(challenge, 1500L);
        assertTrue(challenge.isCompleted());
        assertEquals(1500L, challenge.getTimeCompleted());
    }

    @Test
    void testMarkChallengeCompleted_SecondTimeKeepsBetterTime() {
        Challenge challenge = worlds[0].getChallenges()[0];
        progress.markChallengeCompleted(challenge, 2000L);
        assertEquals(2000L, challenge.getTimeCompleted());
        progress.markChallengeCompleted(challenge, 1500L);
        assertEquals(1500L, challenge.getTimeCompleted());
        progress.markChallengeCompleted(challenge, 2500L);
        assertEquals(1500L, challenge.getTimeCompleted());
    }

    @Test
    void testGetHighestStage_NoStageCompleted_ReturnsZero() {
        assertEquals(0, progress.getHighestStage());
    }

    @Test
    void testGetHighestStage_OneWorldCompleted() {
        worlds[0].getChallenges()[1].setCompleted(true);
        assertEquals(1, progress.getHighestStage());
    }

    @Test
    void testGetHighestStage_MultipleWorldsCompleted() {
        worlds[0].getChallenges()[1].setCompleted(true);
        worlds[1].getChallenges()[2].setCompleted(true);
        assertEquals(2, progress.getHighestStage());
    }
}

