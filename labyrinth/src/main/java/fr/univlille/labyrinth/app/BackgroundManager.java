package fr.univlille.labyrinth.app;

import javafx.scene.effect.GaussianBlur;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

/*
 * Vidéo animée en fond, restant en dessous de toutes les scènes.
 */
public class BackgroundManager {
    private final MediaView mediaView;
    private final MediaPlayer mediaPlayer;

    public BackgroundManager(String videoPath, Stage stage) {
        Media media = new Media(getClass().getResource(videoPath).toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));

        mediaView = new MediaView(mediaPlayer);
        mediaView.setPreserveRatio(false);
        mediaView.fitWidthProperty().bind(stage.widthProperty());
        mediaView.fitHeightProperty().bind(stage.heightProperty());
        mediaView.setEffect(new GaussianBlur(15));
    }

    public MediaView getMediaView() {
        return mediaView;
    }

    public void setVisible(boolean visible) {
        mediaView.setVisible(visible);
    }

    public boolean shouldShowBackground(String page) {
        return !page.contains("Labyrinth");
    }
}
