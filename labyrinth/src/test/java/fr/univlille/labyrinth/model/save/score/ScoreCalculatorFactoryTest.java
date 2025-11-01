package fr.univlille.labyrinth.model.save.score;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreCalculatorFactoryTest {

    @Test
    void shouldCreateStandardCalculator() {
        ScoreCalculator calculateur = ScoreCalculatorFactory.STANDARD.create();

        assertNotNull(calculateur);
        assertInstanceOf(StandardScoreCalculator.class, calculateur);
    }

    @Test
    void shouldCreateSpeedrunCalculator() {
        ScoreCalculator calculateur = ScoreCalculatorFactory.SPEEDRUN.create();

        assertNotNull(calculateur);
        assertInstanceOf(SpeedrunScoreCalculator.class, calculateur);
    }

    @Test
    void shouldCreateDifferentInstances() {
        ScoreCalculator calc1 = ScoreCalculatorFactory.STANDARD.create();
        ScoreCalculator calc2 = ScoreCalculatorFactory.STANDARD.create();

        // nouvelles instances à chaque fois
        assertNotSame(calc1, calc2);
    }

    @Test
    void shouldHaveTwoFactoryTypes() {
        ScoreCalculatorFactory[] factories = ScoreCalculatorFactory.values();

        assertEquals(2, factories.length);
    }

    @Test
    void shouldGetFactoryByName() {
        ScoreCalculatorFactory standard = ScoreCalculatorFactory.valueOf("STANDARD");
        ScoreCalculatorFactory speedrun = ScoreCalculatorFactory.valueOf("SPEEDRUN");

        assertEquals(ScoreCalculatorFactory.STANDARD, standard);
        assertEquals(ScoreCalculatorFactory.SPEEDRUN, speedrun);
    }

    @Test
    void shouldCreateFunctionalCalculators() {
        ScoreCalculator calcStandard = ScoreCalculatorFactory.STANDARD.create();
        ScoreCalculator calcSpeedrun = ScoreCalculatorFactory.SPEEDRUN.create();

        assertEquals("Standard", calcStandard.name());
        assertEquals("Speedrun", calcSpeedrun.name());
    }
}
