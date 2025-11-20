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

    /** 
     * @param message
     * @param isVictory
     */
    public static void setPendingWinner(String message, boolean isVictory) {
        AppState state = AppState.getInstance();
        state.setPendingNotificationMessage(message);
        state.setPendingNotificationIsVictory(isVictory);
    }

    /** 
     * @return String
     */
    public static String getPendingWinner() {
        return AppState.getInstance().getPendingNotificationMessage();
    }

    /** 
     * @return boolean
     */
    public static boolean getPendingIsVictory() {
        return AppState.getInstance().isPendingNotificationVictory();
    }

    /** 
     * @param parent
     * @param message
     * @param isVictory
     */
    public static void show(Pane parent, String message, boolean isVictory) {
        Platform.runLater(() -> {
            String gradient = isVictory
                ? "linear-gradient(to right, #10b981, #059669)"
                : "linear-gradient(to right, #ef4444, #dc2626)";
            String text = isVictory ? "Le gagnant est : " + message : message;

            HBox notification = new HBox();
            notification.setAlignment(Pos.CENTER);
            notification.setStyle(
                "-fx-background-color: " + gradient + ";" +
                "-fx-padding: 24px 32px;" +
                "-fx-background-radius: 10px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 15, 0.4, 0, 3);"
            );
            notification.setMaxWidth(420);

            Label label = new Label(text);
            label.setStyle(
                "-fx-text-fill: white;" +
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-font-family: 'Segoe UI';" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 2, 0.5, 0, 1);"
            );
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
                notification.setOpacity(0);

                TranslateTransition slideDown = new TranslateTransition(Duration.millis(500), notification);
                slideDown.setToY(20);

                FadeTransition fadeIn = new FadeTransition(Duration.millis(500), notification);
                fadeIn.setToValue(1.0);

                PauseTransition pause = new PauseTransition(Duration.seconds(2.5));

                TranslateTransition slideUp = new TranslateTransition(Duration.millis(400), notification);
                slideUp.setToY(-80);

                FadeTransition fadeOut = new FadeTransition(Duration.millis(400), notification);
                fadeOut.setToValue(0.0);

                SequentialTransition sequence = new SequentialTransition(slideDown, pause, slideUp, fadeOut);
                fadeIn.play();
                sequence.setOnFinished(e -> bp.setTop(oldTop));
                sequence.play();
            } else {
                StackPane root = (StackPane) parent.getParent();
                root.getChildren().add(overlay);
                overlay.toFront();

                notification.setTranslateY(-80);
                notification.setOpacity(0);

                TranslateTransition slideDown = new TranslateTransition(Duration.millis(500), notification);
                slideDown.setToY(20);

                FadeTransition fadeIn = new FadeTransition(Duration.millis(500), notification);
                fadeIn.setToValue(1.0);

                PauseTransition pause = new PauseTransition(Duration.seconds(2.5));

                TranslateTransition slideUp = new TranslateTransition(Duration.millis(400), notification);
                slideUp.setToY(-80);

                FadeTransition fadeOut = new FadeTransition(Duration.millis(400), notification);
                fadeOut.setToValue(0.0);

                SequentialTransition sequence = new SequentialTransition(slideDown, pause, slideUp, fadeOut);
                fadeIn.play();
                sequence.setOnFinished(e -> root.getChildren().remove(overlay));
                sequence.play();
            }
        });
    }
}
