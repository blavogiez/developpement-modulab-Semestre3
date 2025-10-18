package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.Main;
import fr.univlille.labyrinth.model.*;
import fr.univlille.labyrinth.view.LabyrinthGridView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class LabyrinthModeProgressionController {

    public static int selectedWorldIndex = 0;
    public static int selectedChallengeIndex = 0;

    @FXML
    private BorderPane pane1;

    @FXML
    private Button bouttonRetour;

    @FXML
    private Label challengeInfoLabel;

    @FXML
    private Label mazeInfoLabel;

    private LabyrinthGridView labyrinth;
    private ProgressionMode gameMode;
    private Chronometre chrono;

    @FXML
    public void initialize() {
        Challenge selectedChallenge = ProgressionMode.defaultProgress.getStageProgress()[selectedWorldIndex].getChallenges()[selectedChallengeIndex];

        challengeInfoLabel.setText("Étape " + (selectedWorldIndex + 1) + ", Défi " + (selectedChallengeIndex + 1));
        mazeInfoLabel.setText("Dimensions : " + selectedChallenge.getWidth() + "*" + selectedChallenge.getHeight() + ", Pourcentage : " + selectedChallenge.getWallPercentage() + "%");

        gameMode = new ProgressionMode();
        gameMode.createMaze(selectedChallenge.getWidth(), selectedChallenge.getHeight(), selectedChallenge.getWallPercentage());

        labyrinth = new LabyrinthGridView(gameMode.getCurrentMaze());
        gameMode.getCurrentMaze().add(labyrinth);

        pane1.setCenter(labyrinth.getGrid());
        pane1.requestFocus();
        labyrinth.update(gameMode.getCurrentMaze());
        chrono.start();
    }

    @FXML
    public void movement(KeyEvent e){
        System.out.println(e.getCode());
        if (e.getCode().equals(KeyCode.S)) gameMode.movePlayerPosition(Direction.DOWN);
        else if (e.getCode().equals(KeyCode.Z)) gameMode.movePlayerPosition(Direction.UP);
        else if (e.getCode().equals(KeyCode.Q)) gameMode.movePlayerPosition(Direction.LEFT);
        else if (e.getCode().equals(KeyCode.D)) gameMode.movePlayerPosition(Direction.RIGHT);

        if (gameMode.isPlayerAtEnd()) {
            //
            chrono.stop();
        }
    }

    @FXML
    private void goToProgression() throws IOException {
        Main.goTo("Progression.fxml");
    }

}
