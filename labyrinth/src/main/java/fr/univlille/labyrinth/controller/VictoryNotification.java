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
            String color = isVictory ? "#4CAF50" : "#F44336";
            String text = isVictory ? "Le gagnant est : " + message : message;

            HBox notification = new HBox();
            notification.setAlignment(Pos.CENTER);
            notification.setStyle("-fx-background-color: " + color + "; -fx-padding: 20px; -fx-background-radius: 5px;");
            notification.setMaxWidth(400);

            Label label = new Label(text);
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
