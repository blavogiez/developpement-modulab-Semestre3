package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.controller.freemode.FreeModeLabyrinthController;
import fr.univlille.labyrinth.controller.progressionmode.AbstractProgressionModeLabyrinthController;
import fr.univlille.labyrinth.model.Observer;
import fr.univlille.labyrinth.model.gamemode.GameMode;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

// Cette classe teste les contrôleurs liés à la victoire du joueur
// On vérifie que les contrôleurs implémentent correctement Observer et possèdent la méthode update

public class VictoryControllerTest {

    @Test
    public void testFreeModeLabyrinthControllerImplementsObserver() {
        // Vérifie que FreeModeLabyrinthController implémente Observer<GameMode>
        assertTrue(Observer.class.isAssignableFrom(FreeModeLabyrinthController.class),
                "FreeModeLabyrinthController doit implémenter Observer");
    }

    @Test
    public void testAbstractProgressionModeLabyrinthControllerImplementsObserver() {
        // Vérifie que AbstractProgressionModeLabyrinthController implémente Observer<GameMode>
        assertTrue(Observer.class.isAssignableFrom(AbstractProgressionModeLabyrinthController.class),
                "AbstractProgressionModeLabyrinthController doit implémenter Observer");
    }

    @Test
    public void testFreeModeLabyrinthControllerHasUpdateMethod() {
        // Vérifie que FreeModeLabyrinthController possède la méthode update(GameMode)
        try {
            Method updateMethod = FreeModeLabyrinthController.class.getMethod("update", GameMode.class);
            assertNotNull(updateMethod, "La méthode update(GameMode) doit exister");
            assertEquals(void.class, updateMethod.getReturnType(),
                    "La méthode update doit retourner void");
        } catch (NoSuchMethodException e) {
            fail("La méthode update(GameMode) devrait exister dans FreeModeLabyrinthController");
        }
    }

    @Test
    public void testAbstractProgressionModeLabyrinthControllerHasUpdateMethod() {
        // Vérifie que AbstractProgressionModeLabyrinthController possède la méthode update(GameMode)
        try {
            Method updateMethod = AbstractProgressionModeLabyrinthController.class.getMethod("update", GameMode.class);
            assertNotNull(updateMethod, "La méthode update(GameMode) doit exister");
            assertEquals(void.class, updateMethod.getReturnType(),
                    "La méthode update doit retourner void");
        } catch (NoSuchMethodException e) {
            fail("La méthode update(GameMode) devrait exister dans AbstractProgressionModeLabyrinthController");
        }
    }

    @Test
    public void testFreeModeLabyrinthControllerHasMovementMethod() {
        // Vérifie que FreeModeLabyrinthController possède une méthode movement pour gérer les déplacements
        try {
            Method movementMethod = FreeModeLabyrinthController.class.getMethod("movement",
                    javafx.scene.input.KeyEvent.class);
            assertNotNull(movementMethod, "La méthode movement devrait exister");
        } catch (NoSuchMethodException e) {
            fail("La méthode movement(KeyEvent) devrait exister dans FreeModeLabyrinthController");
        }
    }

    @Test
    public void testAbstractProgressionModeLabyrinthControllerHasMovementMethod() {
        // Vérifie que AbstractProgressionModeLabyrinthController possède une méthode movement pour gérer les déplacements
        try {
            Method movementMethod = AbstractProgressionModeLabyrinthController.class.getMethod("movement",
                    javafx.scene.input.KeyEvent.class);
            assertNotNull(movementMethod, "La méthode movement devrait exister");
        } catch (NoSuchMethodException e) {
            fail("La méthode movement(KeyEvent) devrait exister dans AbstractProgressionModeLabyrinthController");
        }
    }
}
