package fr.univlille.labyrinth.model.save;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.univlille.labyrinth.model.algorithm.MazeAlgorithmFactory;
import fr.univlille.labyrinth.model.save.score.StandardScoreCalculator;

class ChallengeTest {

    private Challenge challenge;
    private final String algo = MazeAlgorithmFactory.PERFECT.name();

    @BeforeEach
    void init() {
        challenge = new Challenge(algo, ViewType.NORMAL,"Facile", 10, 8, 30, new StandardScoreCalculator());
    }

    @Test
    void testConstructorAndGetters() {
        assertAll("Vérif attributs challenge",
                () -> assertEquals("Facile", challenge.getDifficulty()),
                () -> assertEquals("NORMAL", challenge.getViewType().name()),
                () -> assertEquals(10, challenge.getWidth()),
                () -> assertEquals(8, challenge.getHeight()),
                () -> assertEquals(30, challenge.getWallPercentage()),
                () -> assertFalse(challenge.isCompleted()),
                () -> assertEquals(0, challenge.getTimeCompleted())
        );
    }

    @Test
    void testValidateSetsCompletedTrue() {
        challenge.validate();
        assertTrue(challenge.isCompleted(), "Après validation, le challenge devrait être complété");
    }

    @Test
    void testSetAndGetTimeCompleted() {
        long expectedTime = 120000L;
        challenge.setTimeCompleted(expectedTime);
        assertEquals(expectedTime, challenge.getTimeCompleted(), "Le temps complété devrait correspondre à la valeur définie");
    }

    @Test
    void testGetScoreValueNotCompletedReturnsZero() {
        assertEquals(0, challenge.getScoreValue(), "Le score devrait être 0 si le challenge n'est pas complété");
    }
    
    @Test
    void testSetCompletedChangesState() {
        assertFalse(challenge.isCompleted(), "Le challenge ne devrait pas être complété initialement");
        challenge.setCompleted(true);
        assertTrue(challenge.isCompleted(), "Le challenge devrait être complété après setCompleted(true)");
        challenge.setCompleted(false);
        assertFalse(challenge.isCompleted(), "Le challenge devrait redevenir non complété après setCompleted(false)");
    }

    @Test
    void testScoreValueChangesWithParameters() {
        Challenge smallChallenge = new Challenge(algo, ViewType.NORMAL,"Moyen", 5, 5, 0.2, new StandardScoreCalculator());
        smallChallenge.setCompleted(true);
        assertEquals(5 * 5 * 20, smallChallenge.getScoreValue(), "Le score devrait dépendre des dimensions et du pourcentage de murs");

        Challenge largeChallenge = new Challenge(algo, ViewType.NORMAL,"Difficile", 15, 15, 0.5, new StandardScoreCalculator());
        largeChallenge.setCompleted(true);
        assertEquals(15 * 15 * 50, largeChallenge.getScoreValue(), "Un grand labyrinthe devrait rapporter plus de points");
    }
}
