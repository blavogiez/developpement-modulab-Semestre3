package fr.univlille.labyrinth.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import fr.univlille.labyrinth.controller.freemode.FreeModeLabyrinthController;
import fr.univlille.labyrinth.controller.progressionmode.ProgressionModeLabyrinthController;
import fr.univlille.labyrinth.model.Observer;
import fr.univlille.labyrinth.model.gamemode.GameMode;

// on vérifie que les contrôleurs implémentent correctement Observer et possèdent la méthode update

public class VictoryControllerTest {

    @Test
    public void testFreeModeLabyrinthControllerImplementsObserver() {
        assertTrue(Observer.class.isAssignableFrom(FreeModeLabyrinthController.class),
                "FreeModeLabyrinthController doit implémenter Observer");
    }

    @Test
    public void testProgressionModeLabyrinthControllerImplementsObserver() {
        assertTrue(Observer.class.isAssignableFrom(ProgressionModeLabyrinthController.class),
                "ProgressionModeLabyrinthController doit implémenter Observer");
    }

    @Test
    public void testFreeModeLabyrinthControllerHasUpdateMethod() {
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
    public void testProgressionModeLabyrinthControllerHasUpdateMethod() {
        try {
            Method updateMethod = ProgressionModeLabyrinthController.class.getMethod("update", GameMode.class);
            assertNotNull(updateMethod, "La méthode update(GameMode) doit exister");
            assertEquals(void.class, updateMethod.getReturnType(),
                    "La méthode update doit retourner void");
        } catch (NoSuchMethodException e) {
            fail("La méthode update(GameMode) devrait exister dans ProgressionModeLabyrinthController");
        }
    }

    @Test
    public void testFreeModeLabyrinthControllerHasMovementMethod() {
        try {
            Method movementMethod = FreeModeLabyrinthController.class.getMethod("movement",
                    javafx.scene.input.KeyEvent.class);
            assertNotNull(movementMethod, "La méthode movement devrait exister");
        } catch (NoSuchMethodException e) {
            fail("La méthode movement(KeyEvent) devrait exister dans FreeModeLabyrinthController");
        }
    }

    @Test
    public void testProgressionModeLabyrinthControllerHasMovementMethod() {
        try {
            Method movementMethod = ProgressionModeLabyrinthController.class.getMethod("movement",
                    javafx.scene.input.KeyEvent.class);
            assertNotNull(movementMethod, "La méthode movement devrait exister");
        } catch (NoSuchMethodException e) {
            fail("La méthode movement(KeyEvent) devrait exister dans ProgressionModeLabyrinthController");
        }
    }
}
