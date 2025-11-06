package fr.univlille.labyrinth.model.save.score;

import fr.univlille.labyrinth.model.algorithm.MazeAlgorithm;
import fr.univlille.labyrinth.model.algorithm.MazeAlgorithmFactory;
import fr.univlille.labyrinth.model.save.Challenge;
import fr.univlille.labyrinth.model.save.ViewType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StandardScoreCalculatorTest {

    private StandardScoreCalculator calculator;
    private MazeAlgorithm algo = MazeAlgorithmFactory.PERFECT.getAlgorithm();

    @BeforeEach
    void setUp() {
        calculator = new StandardScoreCalculator();
    }

    @Test
    void shouldReturnZeroWhenChallengeNotCompleted() {
        Challenge defi = new Challenge(algo, ViewType.NORMAL,
                "Test", 10, 10, 0.3, calculator);

        int score = calculator.calculateScore(defi);

        assertEquals(0, score);
    }

    @Test
    void shouldCalculateScoreWhenChallengeCompleted() {
        Challenge defi = new Challenge(algo, ViewType.NORMAL,
                "Test", 10, 10, 0.3, calculator);
        defi.setCompleted(true);

        int score = calculator.calculateScore(defi);

        // formule: largeur * hauteur * pourcentage_murs
        assertEquals(10 * 10 * 30, score);
    }

    @Test
    void shouldCalculateScoreWithDifferentDimensions() {
        Challenge petit = new Challenge(algo, ViewType.NORMAL,
                "Small", 5, 5, 0.2, calculator);
        petit.setCompleted(true);

        Challenge grand = new Challenge(algo, ViewType.NORMAL,
                "Large", 20, 15, 0.4, calculator);
        grand.setCompleted(true);

        assertEquals(5 * 5 * 20, calculator.calculateScore(petit));
        assertEquals(20 * 15 * 40, calculator.calculateScore(grand));
    }

    @Test
    void shouldCalculateScoreWithDifferentWallPercentages() {
        Challenge peuDeMurs = new Challenge(algo, ViewType.NORMAL,
                "Low", 10, 10, 0.1, calculator);
        peuDeMurs.setCompleted(true);

        Challenge beaucoupDeMurs = new Challenge(algo, ViewType.NORMAL,
                "High", 10, 10, 0.5, calculator);
        beaucoupDeMurs.setCompleted(true);

        assertEquals(10 * 10 * 10, calculator.calculateScore(peuDeMurs));
        assertEquals(10 * 10 * 50, calculator.calculateScore(beaucoupDeMurs));
    }

    @Test
    void shouldReturnZeroForZeroWallPercentage() {
        Challenge sansMurs = new Challenge(algo, ViewType.NORMAL,
                "NoWalls", 10, 10, 0.0, calculator);
        sansMurs.setCompleted(true);

        // aucun mur = score 0
        assertEquals(0, calculator.calculateScore(sansMurs));
    }

    @Test
    void shouldHaveCorrectName() {
        assertEquals("Standard", calculator.name());
    }

    @Test
    void shouldCalculateHigherScoreForLargerMaze() {
        Challenge petit = new Challenge(algo, ViewType.NORMAL,
                "Small", 5, 5, 0.3, calculator);
        petit.setCompleted(true);

        Challenge grand = new Challenge(algo, ViewType.NORMAL,
                "Large", 10, 10, 0.3, calculator);
        grand.setCompleted(true);

        // grand labyrinthe = plus de points
        assertTrue(calculator.calculateScore(grand) > calculator.calculateScore(petit));
    }
}
