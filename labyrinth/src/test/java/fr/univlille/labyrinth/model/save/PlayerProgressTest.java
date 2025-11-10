package fr.univlille.labyrinth.model.save;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.univlille.labyrinth.model.algorithm.MazeAlgorithmFactory;
import fr.univlille.labyrinth.model.save.score.StandardScoreCalculator;

class PlayerProgressTest {

    private PlayerProgress progress;
    private Level[] levels;

    @BeforeEach
    void setUp() {
        levels = new Level[2];
        for (int i = 0; i < levels.length; i++) {
            levels[i] = new Level(i + 1);
            for (int j = 0; j < 3; j++) {
                levels[i].getChallenges()[j] = new Challenge(MazeAlgorithmFactory.PERFECT.name(), ViewType.NORMAL, "trèèèès difficile" + j, 10, 10, 20, new StandardScoreCalculator());
            }
        }
        progress = new PlayerProgress(levels);
    }

    @Test
    void testConstructorStoresLevels() {
        assertNotNull(progress.getLevelProgress());
        assertEquals(2, progress.getLevelProgress().length);
    }

    @Test
    void testCopyCreatesDeepCopy() {
        PlayerProgress copy = progress.copy();

        assertNotSame(progress.getLevelProgress(), copy.getLevelProgress());

        for (int i = 0; i < levels.length; i++) {
            Level originalLevel = progress.getLevelProgress()[i];
            Level copiedLevel = copy.getLevelProgress()[i];
            assertNotSame(originalLevel, copiedLevel);
            assertEquals(originalLevel.getNumber(), copiedLevel.getNumber());

            for (int j = 0; j < 3; j++) {
                Challenge origChall = originalLevel.getChallenges()[j];
                Challenge copiedChall = copiedLevel.getChallenges()[j];
                assertNotSame(origChall, copiedChall);
                assertEquals(origChall.getDifficulty(), copiedChall.getDifficulty());
                assertEquals(origChall.getWidth(), copiedChall.getWidth());
                assertEquals(origChall.getHeight(), copiedChall.getHeight());
                assertEquals(origChall.getWallPercentage(), copiedChall.getWallPercentage());
            }
        }
    }

    @Test
    void testMarkChallengeCompletedFirstTimeSetsTime() {
        Challenge challenge = levels[0].getChallenges()[0];
        progress.markChallengeCompleted(challenge, 1500L);
        assertTrue(challenge.isCompleted());
        assertEquals(1500L, challenge.getTimeCompleted());
    }

    @Test
    void testMarkChallengeCompletedSecondTimeKeepsBetterTime() {
        Challenge challenge = levels[0].getChallenges()[0];
        progress.markChallengeCompleted(challenge, 2000L);
        assertEquals(2000L, challenge.getTimeCompleted());
        progress.markChallengeCompleted(challenge, 1500L);
        assertEquals(1500L, challenge.getTimeCompleted());
        progress.markChallengeCompleted(challenge, 2500L);
        assertEquals(1500L, challenge.getTimeCompleted());
    }

    @Test
    void testGetHighestLevelNoLevelCompletedReturnsZero() {
        assertEquals(0, progress.getHighestLevel());
    }

    @Test
    void testGetHighestLevelOneLevelCompleted() {
        levels[0].getChallenges()[1].setCompleted(true);
        assertEquals(1, progress.getHighestLevel());
    }

    @Test
    void testGetHighestLevelMultipleLevelsCompleted() {
        levels[0].getChallenges()[1].setCompleted(true);
        levels[1].getChallenges()[2].setCompleted(true);
        assertEquals(2, progress.getHighestLevel());
    }
}

