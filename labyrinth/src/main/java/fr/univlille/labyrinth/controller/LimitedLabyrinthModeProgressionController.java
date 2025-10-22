package fr.univlille.labyrinth.controller;

import java.io.IOException;

import fr.univlille.labyrinth.Main;
import fr.univlille.labyrinth.model.Challenge;
import fr.univlille.labyrinth.model.Direction;
import fr.univlille.labyrinth.model.Player;
import fr.univlille.labyrinth.model.PlayerDatabase;
import fr.univlille.labyrinth.model.ProgressionMode;
import fr.univlille.labyrinth.model.VictoryObserver;
import fr.univlille.labyrinth.utils.Chronometre;
import fr.univlille.labyrinth.utils.ChronometreFX;
import fr.univlille.labyrinth.view.LimitedLabyrinthGridView;
import fr.univlille.labyrinth.view.LocalPlayerView;

import fr.univlille.labyrinth.parcours.BreadthFirstSearch;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

// controlleur pour la vue limitée en deux parties : labyrinthe complet sans joueur + vue locale 3x3
public class LimitedLabyrinthModeProgressionController implements VictoryObserver {

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

    private LimitedLabyrinthGridView fullMazeView;
    private LocalPlayerView localView;
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

        // création des deux vues
        fullMazeView = new LimitedLabyrinthGridView(gameMode.getCurrentMaze());
        localView = new LocalPlayerView(gameMode.getCurrentMaze());

        // ajout des observeurs
        gameMode.getCurrentMaze().add(fullMazeView);
        gameMode.getCurrentMaze().add(localView);

        // organisation des deux vues cote a cote : limited à gauche, local à droite
        HBox viewContainer = new HBox(150);
        viewContainer.setAlignment(Pos.CENTER);
        viewContainer.getChildren().addAll(fullMazeView.getGrid(), localView.getGrid());

        pane1.setCenter(viewContainer);
        pane1.requestFocus();

        chrono = new Chronometre();
        chrono.start();
        chronoTimeline = ChronometreFX.initChrono(chrono, chronoLabel);

        gameMode.setChronometre(chrono);
        gameMode.addVictoryObserver(this);
    }

    /** 
     * @param e
     * @throws IOException
     */
    @FXML
    public void movement(KeyEvent e) throws IOException {
        if (e.getCode().equals(KeyCode.S)) gameMode.movePlayerPosition(Direction.DOWN);
        else if (e.getCode().equals(KeyCode.Z)) gameMode.movePlayerPosition(Direction.UP);
        else if (e.getCode().equals(KeyCode.Q)) gameMode.movePlayerPosition(Direction.LEFT);
        else if (e.getCode().equals(KeyCode.D)) gameMode.movePlayerPosition(Direction.RIGHT);
    }

    @Override
    public void onVictory() {
        chrono.stop();
        if (chronoTimeline != null) chronoTimeline.stop();
        try {
            goToProgression();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @throws IOException
     */
    @FXML
    private void goToProgression() throws IOException {
        Main.goTo("Progression.fxml");
    }
}
