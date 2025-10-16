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

    @FXML
    public void initialize() {
        Challenge selectedChallenge = ProgressionMode.defaultProgress.getStageProgress()[selectedWorldIndex].getChallenges()[selectedChallengeIndex];

        challengeInfoLabel.setText("Étape " + (selectedWorldIndex + 1) + ", Défi " + (selectedChallengeIndex + 1));
        mazeInfoLabel.setText("Dimensions : " + selectedChallenge.getWidth() + "*" + selectedChallenge.getHeight() + ", Pourcentage : " + selectedChallenge.getWallPercentage() + "%");

        ProgressionMode gameMode = new ProgressionMode();
        gameMode.createMaze(selectedChallenge.getWidth(), selectedChallenge.getHeight(), selectedChallenge.getWallPercentage());

        labyrinth = new LabyrinthGridView(gameMode.getCurrentMaze());
        gameMode.getCurrentMaze().add(labyrinth);
        labyrinth.setControler(new LabyrinthControler(gameMode));

        pane1.setCenter(labyrinth.getCompletePane());
        pane1.requestFocus();
        labyrinth.update(gameMode.getCurrentMaze());
    }

    @FXML
    public void movement(KeyEvent e){
        System.out.println(e.getCode());
        if (e.getCode().equals(KeyCode.S)) labyrinth.getControler().movePlayer(Direction.DOWN);
        else if (e.getCode().equals(KeyCode.Z)) labyrinth.getControler().movePlayer(Direction.UP);
        else if (e.getCode().equals(KeyCode.Q)) labyrinth.getControler().movePlayer(Direction.LEFT);
        else if (e.getCode().equals(KeyCode.D)) labyrinth.getControler().movePlayer(Direction.RIGHT);
    }

    @FXML
    private void goToProgression() throws IOException {
        Main.goTo("Progression.fxml");
    }

}
