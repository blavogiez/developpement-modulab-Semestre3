package fr.univlille.labyrinth.view.utils;

import fr.univlille.labyrinth.model.gamemode.Timer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * Classe utilitaire du FX du chronomètre.
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */

public class TimerFX {
    /**
     * Retourne une timeline JavaFX pour un chronomètre.
     * @param chrono
     * @param chronoLabel
     * @return Timeline
     */
    public static Timeline initChrono(Timer chrono, Label chronoLabel) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            long minutes = chrono.convertisseurMillisVersFormat("minutes");
            long seconds = chrono.convertisseurMillisVersFormat("secondes");
            chronoLabel.setText(String.format("%02d:%02d", minutes, seconds));
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        return timeline;
    }
}
