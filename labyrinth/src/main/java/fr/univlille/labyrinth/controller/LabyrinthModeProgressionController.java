package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.Main;
import fr.univlille.labyrinth.model.*;
import fr.univlille.labyrinth.parcours.BreadthFirstSearch;
import fr.univlille.labyrinth.utils.ChronoUtil;
import fr.univlille.labyrinth.view.LabyrinthGridView;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class LabyrinthModeProgressionController {

    @FXML
    private BorderPane pane1;

    @FXML
    private Button bouttonRetour;

    @FXML
    private Label challengeInfoLabel;

    @FXML
    private Label mazeInfoLabel;

    @FXML
    private Label chronoLabel;

    private LabyrinthGridView labyrinth;

    private ProgressionMode gameMode;

    private Chronometre chrono;
    private Timeline chronoTimeline;    
    
    @FXML
    public void initialize() {
        Challenge selectedChallenge = AppState.getInstance().getSelectedChallenge();
        int selectedLevelIndex = AppState.getInstance().getSelectedLevelIndex();
        int selectedChallengeIndex = AppState.getInstance().getSelectedChallengeIndex();

        challengeInfoLabel.setText("Étape " + (selectedLevelIndex + 1) + ", Défi " + (selectedChallengeIndex + 1) + ", vue limitée");
        
        String info = "Dimensions : " + selectedChallenge.getWidth() + "*" + selectedChallenge.getHeight() ;
        info += ", Pourcentage : " + (int)(selectedChallenge.getWallPercentage() * 100) + "%" ;
        info += ", Distance entrée/sortie : " + selectedChallenge.getDistanceBetweenEntryAndExit();
        // afficher la distance effective
        mazeInfoLabel.setText(info);

        gameMode = new ProgressionMode();
        gameMode.createMaze(selectedChallenge);
        info += " (effective : " + BreadthFirstSearch.calculateDistance(gameMode.getCurrentMaze().getGrid(), gameMode.getCurrentMaze().getEntryPosition(), gameMode.getCurrentMaze().getExitPosition()) + ")" ;
        mazeInfoLabel.setText(info);

        // ajouter l'observeur
        labyrinth = new LabyrinthGridView(gameMode.getCurrentMaze());
        gameMode.getCurrentMaze().add(labyrinth);

        pane1.setCenter(labyrinth.getGrid());
        pane1.requestFocus();
        labyrinth.update(gameMode.getCurrentMaze());
        chrono=new Chronometre();
        chrono.start();
        chronoTimeline = ChronoUtil.initChrono(chrono, chronoLabel);
    }

    /** 
     * @param e
     * @throws IOException
     */
    @FXML
    public void movement(KeyEvent e) throws IOException {
        System.out.println(e.getCode());
        if (e.getCode().equals(KeyCode.S)) gameMode.movePlayerPosition(Direction.DOWN);
        else if (e.getCode().equals(KeyCode.Z)) gameMode.movePlayerPosition(Direction.UP);
        else if (e.getCode().equals(KeyCode.Q)) gameMode.movePlayerPosition(Direction.LEFT);
        else if (e.getCode().equals(KeyCode.D)) gameMode.movePlayerPosition(Direction.RIGHT);

        if (gameMode.isPlayerAtEnd()) {
            chrono.stop();
            if (chronoTimeline != null) chronoTimeline.stop();

            Challenge selectedChallenge = AppState.getInstance().getSelectedChallenge();
            Player currentPlayer = AppState.getInstance().getCurrentPlayer();
            currentPlayer.getProgress().markChallengeCompleted(selectedChallenge, chrono.getChrono());

            PlayerDatabase.savePlayer(currentPlayer);

            goToProgression();

        }
    }

    /** 
     * @throws IOException
     */
    @FXML
    protected void goToProgression() throws IOException {
        Main.goTo("Progression.fxml");
    }
}
