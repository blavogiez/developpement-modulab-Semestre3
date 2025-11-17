package fr.univlille.labyrinth.controller;

import java.io.IOException;

import fr.univlille.labyrinth.controller.input.PlayerMovementHandler;
import fr.univlille.labyrinth.model.VictoryObserver;
import fr.univlille.labyrinth.model.gamemode.GameMode;
import fr.univlille.labyrinth.utils.Timer;
import fr.univlille.labyrinth.utils.TimerFX;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
public abstract class LabyrinthController<T extends GameMode> implements VictoryObserver<GameMode> {
    
    @FXML
    protected BorderPane pane1;

    @FXML
    protected Label chronoLabel;

    @FXML
    private Label mazeInfoLabel;

    protected T gameMode;
    protected Timer chrono;
    protected Timeline chronoTimeline;
    protected PlayerMovementHandler movementHandler;

    /**
     * Méthode d'initialisation commune à tous les contrôleurs de labyrinthe
     */
    @FXML
    public void initialize() {
        chrono = new Timer();
        chrono.start();
        chronoTimeline = TimerFX.initChrono(chrono, chronoLabel);
        movementHandler = new PlayerMovementHandler();
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
     * @param e
     * @throws IOException
     */
    /**
     * Méthode abstraite pour initialiser le mode de jeu spécifique
     */
    protected abstract void initializeGameMode();

    /** 
     * @param e
     * @throws IOException
     */
    @FXML
    public void movement(KeyEvent e) throws IOException {
        movementHandler.handleMovement(e, gameMode);
    }

    /**
     * Arrête le chronomètre
     */
    protected void stopChrono() {
        chrono.stop();
        if (chronoTimeline != null) chronoTimeline.stop();
    }

    @Override
    public void onVictory() {
        stopChrono();
        handleVictory();
    }

    /** 
     * @param observable
     */
    @Override
    public void onDefeat(GameMode observable) {
        stopChrono();
        handleDefeat();
    }

    public abstract void handleVictory();
    public abstract void handleDefeat();
}