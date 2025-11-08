package fr.univlille.labyrinth.controller;

import java.io.IOException;

import fr.univlille.labyrinth.model.Observer;
import fr.univlille.labyrinth.model.gamemode.GameMode;
import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.utils.Timer;
import fr.univlille.labyrinth.utils.TimerFX;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

/**
 * Classe abstraite générique pour les contrôleurs de labyrinthe
 * Regroupe les fonctionnalités communes comme le mouvement du joueur et le chronomètre
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public abstract class LabyrinthController<T extends GameMode> implements Observer<GameMode> {
    
    @FXML
    protected BorderPane pane1;

    @FXML
    protected Label chronoLabel;

    @FXML
    private Label mazeInfoLabel;

    protected T gameMode;
    protected Timer chrono;
    protected Timeline chronoTimeline;
    private boolean isMoving = false;

    /**
     * Méthode d'initialisation commune à tous les contrôleurs de labyrinthe
     */
    @FXML
    public void initialize() {
        chrono = new Timer();
        chrono.start();
        chronoTimeline = TimerFX.initChrono(chrono, chronoLabel);
        initializeGameMode();
        
        mazeInfoLabel.setText(gameMode.toString());
        
        pane1.requestFocus();
        pane1.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            try {
                movement(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        gameMode.addVictoryObserver(this);
    }

    /**
     * Méthode abstraite pour initialiser le mode de jeu spécifique
     */
    protected abstract void initializeGameMode();

    /**
     * Déplace le joueur en fonction de la touche pressée
     */
    @FXML
    public void movement(KeyEvent e) throws IOException {
        Direction direction = null;
        KeyCode code = e.getCode();

        if (code == KeyCode.S || code == KeyCode.DOWN) direction = Direction.DOWN;
        else if (code == KeyCode.Z || code == KeyCode.UP) direction = Direction.UP;
        else if (code == KeyCode.Q || code == KeyCode.LEFT) direction = Direction.LEFT;
        else if (code == KeyCode.D || code == KeyCode.RIGHT) direction = Direction.RIGHT;

        if (direction != null) {
            animateMovement(direction);
            e.consume();
        }
    }

    /**
     * Arrête le chronomètre
     */
    protected void stopChrono() {
        chrono.stop();
        if (chronoTimeline != null) chronoTimeline.stop();
    }

    @Override
    public void update(GameMode gameMode) {
        stopChrono();
        handleVictory();
    }

    /**
     * Méthode abstraite à implémenter pour gérer la victoire
     */
    protected abstract void handleVictory();



private void animateMovement(Direction dir) {
    if (isMoving) {
        return;
    }
    isMoving = true;

    double deltaX = 0;
    double deltaY = 0;

    switch (dir) {
        case UP -> deltaY = -50;
        case DOWN -> deltaY = 50;
        case LEFT -> deltaX = -50;
        case RIGHT -> deltaX = 50;
    }

    Timeline timeline = new Timeline();
    KeyValue kvX = new KeyValue(playerImageView.translateXProperty(), playerImageView.getTranslateX() + deltaX);
    KeyValue kvY = new KeyValue(playerImageView.translateYProperty(), playerImageView.getTranslateY() + deltaY);

    KeyFrame kf = new KeyFrame(Duration.millis(200), kvX, kvY);
    timeline.getKeyFrames().add(kf);

    timeline.setOnFinished(evt -> {
        // Mise à jour de la position logique dans gameMode
        gameMode.movePlayerPosition(dir);
        // Réinitialisation de l'offset visuel pour éviter les cumul
        playerImageView.setTranslateX(0);
        playerImageView.setTranslateY(0);
        isMoving = false;
    });

    timeline.play();
}

}