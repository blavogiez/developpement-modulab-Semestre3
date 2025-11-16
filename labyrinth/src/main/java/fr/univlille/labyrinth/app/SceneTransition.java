package fr.univlille.labyrinth.app;

import javafx.animation.FadeTransition;
import javafx.scene.Parent;
import javafx.util.Duration;

/*
 * Transition entre chaque scène
 */
public class SceneTransition {
    private static final double FADE_DURATION_MS = 200;

    public void transitionTo(Parent currentContent, Parent newContent, Runnable onComplete) {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(FADE_DURATION_MS), currentContent);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        fadeOut.setOnFinished(e -> {
            newContent.setOpacity(0.0);
            onComplete.run();

            FadeTransition fadeIn = new FadeTransition(Duration.millis(FADE_DURATION_MS), newContent);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        });

        fadeOut.play();
    }
}
