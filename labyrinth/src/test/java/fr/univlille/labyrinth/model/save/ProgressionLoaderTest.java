package fr.univlille.labyrinth.model.save;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.univlille.labyrinth.model.save.Challenge;
import fr.univlille.labyrinth.model.save.Level;
import fr.univlille.labyrinth.model.save.PlayerProgress;
import fr.univlille.labyrinth.model.save.ViewType;
import fr.univlille.labyrinth.utils.ProgressionLoader;

/**
 * Tests unitaires pour ProgressionLoaderTestTest
 */
class ProgressionLoaderTest {
    
    private static final String TEST_FILE = "res/default_progression.csv";
    private static final String HEADER = "ScoreFactory,Algorithm,ViewType,Level,ChallengeIndex,Difficulty,Width,Height,WallPercentage,Distance,EntitiesConfig";
    private File testFile;

    @BeforeEach
    void setUp() {
        new File("res").mkdirs();
        testFile = new File(TEST_FILE);
    }

    @AfterEach
    void tearDown() {
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    private void createValidCSV() throws IOException {
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write(HEADER + "\n");
            writer.write("STANDARD,PERFECT,NORMAL,1,0,EASY,10,10,0.2,5,config1\n");
            writer.write("SPEEDRUN,FUSION,LOCAL,1,1,MEDIUM,15,15,0.3,7,config2\n");
            writer.write("STANDARD,PERFECT,EXPLORATION,2,0,HARD,20,20,0.4,10,config3\n");
        }
    }

    @Test
    void testFileNotExists() {
        assertThrows(RuntimeException.class, () -> ProgressionLoader.loadDefaultProgress());
    }

    @Test
    void testLoadValidFile() throws IOException {
        createValidCSV();
        PlayerProgress progress = ProgressionLoader.loadDefaultProgress();
        assertNotNull(progress);
        assertEquals(2, progress.getLevelProgress().length);
    }

    @Test
    void testLevelsInitialized() throws IOException {
        createValidCSV();
        PlayerProgress progress = ProgressionLoader.loadDefaultProgress();
        Level[] levels = progress.getLevelProgress();
        for (int i = 0; i < levels.length; i++) {
            assertNotNull(levels[i]);
            assertEquals(i + 1, levels[i].getNumber());
            assertEquals(3, levels[i].getChallenges().length);
        }
    }

    @Test
    void testChallengesLoaded() throws IOException {
        createValidCSV();
        PlayerProgress progress = ProgressionLoader.loadDefaultProgress();
        Challenge challenge = progress.getLevelProgress()[0].getChallenges()[0];
        assertNotNull(challenge);
        assertEquals("PERFECT", challenge.getAlgorithmName());
        assertEquals(ViewType.NORMAL, challenge.getViewType());
        assertEquals(10, challenge.getWidth());
        assertEquals(10, challenge.getHeight());
        assertEquals(0.2, challenge.getWallPercentage(), 0.001);
        assertFalse(challenge.isCompleted());
    }

    @Test
    void testEmptyLinesIgnored() throws IOException {
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write(HEADER + "\n");
            writer.write("STANDARD,PERFECT,NORMAL,1,0,EASY,10,10,0.2,5,config1\n");
            writer.write("\n");
            writer.write("   \n");
            writer.write("SPEEDRUN,FUSION,LOCAL,1,1,MEDIUM,15,15,0.3,7,config2\n");
        }
        PlayerProgress progress = ProgressionLoader.loadDefaultProgress();
        assertNotNull(progress.getLevelProgress()[0].getChallenges()[0]);
        assertNotNull(progress.getLevelProgress()[0].getChallenges()[1]);
    }

    @Test
    void testInvalidLineLengthSkipped() throws IOException {
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write(HEADER + "\n");
            writer.write("STANDARD,PERFECT,NORMAL,1,0,EASY\n");
            writer.write("SPEEDRUN,FUSION,LOCAL,1,1,MEDIUM,15,15,0.3,7,config2\n");
        }
        PlayerProgress progress = ProgressionLoader.loadDefaultProgress();
        assertNull(progress.getLevelProgress()[0].getChallenges()[0]);
        assertNotNull(progress.getLevelProgress()[0].getChallenges()[1]);
    }

    @Test
    void testInvalidNumberFormat() throws IOException {
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write(HEADER + "\n");
            writer.write("STANDARD,PERFECT,NORMAL,abc,0,EASY,10,10,0.2,5,config1\n");
        }
        assertThrows(RuntimeException.class, () -> ProgressionLoader.loadDefaultProgress());
    }

    @Test
    void testMaxLevelDetection() throws IOException {
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write(HEADER + "\n");
            writer.write("STANDARD,PERFECT,NORMAL,1,0,EASY,10,10,0.2,5,config1\n");
            writer.write("STANDARD,PERFECT,NORMAL,5,0,EASY,10,10,0.2,5,config2\n");
        }
        PlayerProgress progress = ProgressionLoader.loadDefaultProgress();
        assertEquals(5, progress.getLevelProgress().length);
    }

    @Test
    void testAllViewTypes() throws IOException {
        createValidCSV();
        PlayerProgress progress = ProgressionLoader.loadDefaultProgress();
        Challenge[] challenges = progress.getLevelProgress()[0].getChallenges();
        assertEquals(ViewType.NORMAL, challenges[0].getViewType());
        assertEquals(ViewType.LOCAL, challenges[1].getViewType());
    }

    @Test
    void testScoreCalculatorSet() throws IOException {
        createValidCSV();
        PlayerProgress progress = ProgressionLoader.loadDefaultProgress();
        assertNotNull(progress.getLevelProgress()[0].getChallenges()[0].getScoreCalculator());
        assertNotNull(progress.getLevelProgress()[0].getChallenges()[1].getScoreCalculator());
    }
}
