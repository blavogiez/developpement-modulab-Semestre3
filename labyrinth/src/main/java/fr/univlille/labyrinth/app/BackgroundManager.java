package fr.univlille.labyrinth.app;

import javafx.scene.effect.GaussianBlur;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BackgroundManager {
    private static final int GAUSSIAN_BLUR_VALUE = 0;

    private final MediaView mediaView;
    private MediaPlayer mediaPlayer;

    public BackgroundManager(String videoPath, Stage stage) {
        MediaView view = new MediaView();
        mediaView = view;

        try {
            if (getClass().getResource(videoPath) != null) {
                Media media = new Media(getClass().getResource(videoPath).toExternalForm());

                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                mediaPlayer.setAutoPlay(true);
                mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));

                view.setMediaPlayer(mediaPlayer);
                view.setPreserveRatio(false);
                view.fitWidthProperty().bind(stage.widthProperty());
                view.fitHeightProperty().bind(stage.heightProperty());
                view.setEffect(new GaussianBlur(GAUSSIAN_BLUR_VALUE));
            } else {
                System.out.println("Fichier vidéo introuvable : " + videoPath);
            }
        } catch (MediaException e) {
            System.err.println("Lecture vidéo désactivée : " + e.getMessage());
            mediaPlayer = null;
        } catch (Exception e) {
            System.err.println("Erreur inattendue lors du chargement de la vidéo : " + e.getMessage());
            mediaPlayer = null;
        }
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
