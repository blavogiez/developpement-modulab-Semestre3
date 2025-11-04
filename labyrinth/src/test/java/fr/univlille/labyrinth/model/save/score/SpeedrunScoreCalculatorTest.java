package fr.univlille.labyrinth.model.save.score;

import fr.univlille.labyrinth.model.algorithmold.MazeAlgorithmFactory;
import fr.univlille.labyrinth.model.save.Challenge;
import fr.univlille.labyrinth.model.save.ViewType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpeedrunScoreCalculatorTest {

    private SpeedrunScoreCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new SpeedrunScoreCalculator();
    }

    @Test
    void shouldReturnZeroWhenChallengeNotCompleted() {
        Challenge defi = new Challenge(MazeAlgorithmFactory.STANDARDLARGEUR, ViewType.NORMAL,
                "Test", 10, 10, 0.3, calculator);

        int score = calculator.calculateScore(defi);

        assertEquals(0, score);
    }

    @Test
    void shouldCalculateBaseScoreWhenNoTimeSet() {
        Challenge defi = new Challenge(MazeAlgorithmFactory.STANDARDLARGEUR, ViewType.NORMAL,
                "Test", 10, 10, 0.3, calculator);
        defi.setCompleted(true);

        int score = calculator.calculateScore(defi);

        // score de base sans bonus temporel
        assertEquals(10 * 10 * 30, score);
    }

    @Test
    void shouldApplyBonusWhenTimeBelowThreshold() {
        Challenge defi = new Challenge(MazeAlgorithmFactory.STANDARDLARGEUR, ViewType.NORMAL,
                "Fast", 10, 10, 0.3, calculator);
        defi.setCompleted(true);
        defi.setTimeCompleted(30000L);

        int score = calculator.calculateScore(defi);

        // bonus appliqué car < 60s
        assertTrue(score > 10 * 10 * 30);
    }

    @Test
    void shouldNotApplyBonusWhenTimeAboveThreshold() {
        Challenge defi = new Challenge(MazeAlgorithmFactory.STANDARDLARGEUR, ViewType.NORMAL,
                "Slow", 10, 10, 0.3, calculator);
        defi.setCompleted(true);
        defi.setTimeCompleted(70000L);

        int score = calculator.calculateScore(defi);

        assertEquals(10 * 10 * 30, score);
    }

    @Test
    void shouldGiveHigherBonusForFasterCompletion() {
        Challenge rapide = new Challenge(MazeAlgorithmFactory.STANDARDLARGEUR, ViewType.NORMAL,
                "tropvite", 10, 10, 0.3, calculator);
        rapide.setCompleted(true);
        rapide.setTimeCompleted(10000L);

        Challenge moyen = new Challenge(MazeAlgorithmFactory.STANDARDLARGEUR, ViewType.NORMAL,
                "tropvite", 10, 10, 0.3, calculator);
        moyen.setCompleted(true);
        moyen.setTimeCompleted(40000L);

        int scoreRapide = calculator.calculateScore(rapide);
        int scoreMoyen = calculator.calculateScore(moyen);

        // plus rapide = meilleur score
        assertTrue(scoreRapide > scoreMoyen);
    }

    @Test
    void shouldCalculateScoreAtExactThreshold() {
        Challenge defi = new Challenge(MazeAlgorithmFactory.STANDARDLARGEUR, ViewType.NORMAL,
                "totocaca", 10, 10, 0.3, calculator);
        defi.setCompleted(true);
        defi.setTimeCompleted(60000L);

        int score = calculator.calculateScore(defi);

        // exactement au seuil = pas de bonus
        assertEquals(10 * 10 * 30, score);
    }

    @Test
    void shouldWorkWithDifferentMazeSizes() {
        Challenge petit = new Challenge(MazeAlgorithmFactory.STANDARDLARGEUR, ViewType.NORMAL,
                "Small", 5, 5, 0.2, calculator);
        petit.setCompleted(true);
        petit.setTimeCompleted(20000L);

        Challenge grand = new Challenge(MazeAlgorithmFactory.STANDARDLARGEUR, ViewType.NORMAL,
                "Large", 20, 15, 0.4, calculator);
        grand.setCompleted(true);
        grand.setTimeCompleted(20000L);

        int scorePetit = calculator.calculateScore(petit);
        int scoreGrand = calculator.calculateScore(grand);

        assertTrue(scoreGrand > scorePetit);
        assertTrue(scorePetit > 5 * 5 * 20);
        assertTrue(scoreGrand > 20 * 15 * 40);
    }

    @Test
    void shouldHaveCorrectName() {
        assertEquals("Speedrun", calculator.name());
    }

    @Test
    void shouldHandleVeryFastTime() {
        Challenge defi = new Challenge(MazeAlgorithmFactory.STANDARDLARGEUR, ViewType.NORMAL,
                "tropvite", 10, 10, 0.3, calculator);
        defi.setCompleted(true);
        defi.setTimeCompleted(1000L);

        int score = calculator.calculateScore(defi);

        // temps très rapide = gros bonus
        assertTrue(score > 10 * 10 * 30 * 2);
    }
}
