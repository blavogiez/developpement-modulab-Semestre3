package fr.univlille.labyrinth.controller;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class VictoryNotification {

    private static String pendingWinner = null;

    public static void setPendingWinner(String winner) {
        pendingWinner = winner;
    }

    public static String getPendingWinner() {
        String winner = pendingWinner;
        pendingWinner = null;
        return winner;
    }

    public static void show(Pane parent, String winner) {
        Platform.runLater(() -> {
            HBox notification = new HBox();
            notification.setAlignment(Pos.CENTER);
            notification.setStyle("-fx-background-color: #4CAF50; -fx-padding: 20px; -fx-background-radius: 5px;");
            notification.setMaxWidth(400);

            Label label = new Label("Le gagnant est : " + winner);
            label.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");
            notification.getChildren().add(label);

            StackPane overlay = new StackPane();
            overlay.setPickOnBounds(false);
            overlay.setMouseTransparent(true);
            overlay.getChildren().add(notification);
            StackPane.setAlignment(notification, Pos.TOP_CENTER);

            if (parent instanceof BorderPane) {
                BorderPane bp = (BorderPane) parent;
                Node oldTop = bp.getTop();
                StackPane topContainer = new StackPane();
                if (oldTop != null) {
                    topContainer.getChildren().add(oldTop);
                }
                topContainer.getChildren().add(overlay);
                StackPane.setAlignment(overlay, Pos.TOP_CENTER);
                bp.setTop(topContainer);

                notification.setTranslateY(-80);

                TranslateTransition slideDown = new TranslateTransition(Duration.millis(400), notification);
                slideDown.setToY(20);

                PauseTransition pause = new PauseTransition(Duration.seconds(2));

                TranslateTransition slideUp = new TranslateTransition(Duration.millis(400), notification);
                slideUp.setToY(-80);

                FadeTransition fadeOut = new FadeTransition(Duration.millis(400), notification);
                fadeOut.setToValue(0.0);

                SequentialTransition sequence = new SequentialTransition(slideDown, pause, slideUp, fadeOut);
                sequence.setOnFinished(e -> bp.setTop(oldTop));
                sequence.play();
            } else {
                StackPane root = (StackPane) parent.getParent();
                root.getChildren().add(overlay);
                overlay.toFront();

                notification.setTranslateY(-80);

                TranslateTransition slideDown = new TranslateTransition(Duration.millis(400), notification);
                slideDown.setToY(20);

                PauseTransition pause = new PauseTransition(Duration.seconds(2));

                TranslateTransition slideUp = new TranslateTransition(Duration.millis(400), notification);
                slideUp.setToY(-80);

                FadeTransition fadeOut = new FadeTransition(Duration.millis(400), notification);
                fadeOut.setToValue(0.0);

                SequentialTransition sequence = new SequentialTransition(slideDown, pause, slideUp, fadeOut);
                sequence.setOnFinished(e -> root.getChildren().remove(overlay));
                sequence.play();
            }
        });
    }
}
