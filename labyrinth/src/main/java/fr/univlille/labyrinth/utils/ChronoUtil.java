package fr.univlille.labyrinth.utils;

import fr.univlille.labyrinth.model.Chronometre;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class ChronoUtil {
    public static Timeline initChrono(Chronometre chrono, Label chronoLabel) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            long elapsed = chrono.getChrono() / 1000;
            long minutes = elapsed / 60;
            long seconds = elapsed % 60;
            chronoLabel.setText(String.format("%02d:%02d", minutes, seconds));
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        return timeline;
    }
}
