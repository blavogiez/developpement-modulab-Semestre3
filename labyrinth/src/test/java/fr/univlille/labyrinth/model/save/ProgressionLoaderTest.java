package fr.univlille.labyrinth.model.save;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * Test pour la progression par défaut (csv) et son chargement 
 */
class ProgressionLoaderTest {

    private static final String TEST_FILE = "res/test_progression.csv";
    private File testFile;
    private String HEADER = "MonHeaderPasImportant";

    /** 
     * @throws IOException
     */
    @BeforeEach
    void setUp() throws IOException {
        new File(TEST_FILE).mkdirs();
        Files.copy(Paths.get("res/default_progression.csv"), Paths.get(TEST_FILE), StandardCopyOption.REPLACE_EXISTING);
        ProgressionLoader.setDefaultProgressPath(TEST_FILE);
        testFile = new File(TEST_FILE);
    }

    @AfterEach
    void tearDown() {
        if (testFile.exists()) {
            testFile.delete();
        }
        ProgressionLoader.setDefaultProgressPath("res/default_progression.csv");
    }

    /** 
     * @throws IOException
     */
    private void createValidCSV() throws IOException {
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write(HEADER + "\n");
            writer.write("STANDARD,PERFECT,NORMAL,1,0,EASY,10,10,0.2,5,config1,DEFAULT\n");
            writer.write("SPEEDRUN,FUSION,LOCAL,1,1,MEDIUM,15,15,0.3,7,config2,DEFAULT\n");
            writer.write("STANDARD,PERFECT,EXPLORATION,2,0,HARD,20,20,0.4,10,config3,DEFAULT\n");
        }
    }

    @Test
    void testFileNotExists() {
        if (testFile.exists()) {
            testFile.delete();
        }
        assertThrows(RuntimeException.class, () -> ProgressionLoader.loadDefaultProgress());
    }

    /** 
     * @throws IOException
     */
    @Test
    void testLoadValidFile() throws IOException {
        createValidCSV();
        PlayerProgress progress = ProgressionLoader.loadDefaultProgress();
        assertNotNull(progress);
        assertEquals(2, progress.getLevelProgress().length);
    }

    /** 
     * @throws IOException
     */
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

    /** 
     * @throws IOException
     */
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

    /** 
     * @throws IOException
     */
    @Test
    void testEmptyLinesIgnored() throws IOException {
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write(HEADER + "\n");
            writer.write("STANDARD,PERFECT,NORMAL,1,0,EASY,10,10,0.2,5,config1,DEFAULT\n");
            writer.write("\n");
            writer.write("   \n");
            writer.write("SPEEDRUN,FUSION,LOCAL,1,1,MEDIUM,15,15,0.3,7,config2,DEFAULT\n");
        }
        PlayerProgress progress = ProgressionLoader.loadDefaultProgress();
        assertNotNull(progress.getLevelProgress()[0].getChallenges()[0]);
        assertNotNull(progress.getLevelProgress()[0].getChallenges()[1]);
    }

    /** 
     * @throws IOException
     */
    @Test
    void testInvalidLineLengthSkipped() throws IOException {
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write(HEADER + "\n");
            writer.write("STANDARD,PERFECT,NORMAL,1,0,EASY\n");
            writer.write("SPEEDRUN,FUSION,LOCAL,1,1,MEDIUM,15,15,0.3,7,config2,DEFAULT\n");
        }
        PlayerProgress progress = ProgressionLoader.loadDefaultProgress();
        assertNull(progress.getLevelProgress()[0].getChallenges()[0]);
        assertNotNull(progress.getLevelProgress()[0].getChallenges()[1]);
    }

    /** 
     * @throws IOException
     */
    @Test
    void testInvalidNumberFormat() throws IOException {
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write(HEADER + "\n");
            writer.write("STANDARD,PERFECT,NORMAL,abc,0,EASY,10,10,0.2,5,config1,DEFAULT\n");
        }
        assertThrows(RuntimeException.class, () -> ProgressionLoader.loadDefaultProgress());
    }

    /** 
     * @throws IOException
     */
    @Test
    void testMaxLevelDetection() throws IOException {
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write(HEADER + "\n");
            writer.write("STANDARD,PERFECT,NORMAL,1,0,EASY,10,10,0.2,5,config1,DEFAULT\n");
            writer.write("STANDARD,PERFECT,NORMAL,5,0,EASY,10,10,0.2,5,config2,DEFAULT\n");
        }
        PlayerProgress progress = ProgressionLoader.loadDefaultProgress();
        assertEquals(5, progress.getLevelProgress().length);
    }

    /** 
     * @throws IOException
     */
    @Test
    void testAllViewTypes() throws IOException {
        createValidCSV();
        PlayerProgress progress = ProgressionLoader.loadDefaultProgress();
        Challenge[] challenges = progress.getLevelProgress()[0].getChallenges();
        assertEquals(ViewType.NORMAL, challenges[0].getViewType());
        assertEquals(ViewType.LOCAL, challenges[1].getViewType());
    }

    /** 
     * @throws IOException
     */
    @Test
    void testScoreCalculatorSet() throws IOException {
        createValidCSV();
        PlayerProgress progress = ProgressionLoader.loadDefaultProgress();
        assertNotNull(progress.getLevelProgress()[0].getChallenges()[0].getScoreCalculator());
        assertNotNull(progress.getLevelProgress()[0].getChallenges()[1].getScoreCalculator());
    }
}
