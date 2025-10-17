package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.Main;
import fr.univlille.labyrinth.model.Player;
import fr.univlille.labyrinth.model.PlayerDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;

public class ProgressionController {

    @FXML
    private Text playerNameLabel;
    @FXML
    private Text scoreLabel;

    @FXML
    private Button bouttonWorld1Challenge1;
    @FXML
    private Button bouttonWorld1Challenge2;
    @FXML
    private Button bouttonWorld1Challenge3;
    @FXML
    private Button bouttonWorld2Challenge1;
    @FXML
    private Button bouttonWorld2Challenge2;
    @FXML
    private Button bouttonWorld2Challenge3;
    @FXML
    private Button bouttonWorld3Challenge1;
    @FXML
    private Button bouttonWorld3Challenge2;
    @FXML
    private Button bouttonWorld3Challenge3;

    @FXML
    public void initialize() {
        String playerName = ProgressionEntreNomController.playerName;
        Player currentPlayer = PlayerDatabase.loadPlayer(playerName);
        if (currentPlayer == null) {
            currentPlayer = new Player(playerName);
            PlayerDatabase.savePlayer(currentPlayer);
        }
        playerNameLabel.setText(currentPlayer.getName());
        scoreLabel.setText("Score : " + currentPlayer.getScore());
    }

    @FXML
    private void goToWorld1Challenge1() throws IOException {
        LabyrinthModeProgressionController.selectedWorldIndex = 0;
        LabyrinthModeProgressionController.selectedChallengeIndex = 0;
        Main.goTo("LabyrinthModeProgression.fxml");
    }

    @FXML
    private void goToWorld1Challenge2() throws IOException {
        LabyrinthModeProgressionController.selectedWorldIndex = 0;
        LabyrinthModeProgressionController.selectedChallengeIndex = 1;
        Main.goTo("LabyrinthModeProgression.fxml");
    }

    @FXML
    private void goToWorld1Challenge3() throws IOException {
        LabyrinthModeProgressionController.selectedWorldIndex = 0;
        LabyrinthModeProgressionController.selectedChallengeIndex = 2;
        Main.goTo("LabyrinthModeProgression.fxml");
    }

    @FXML
    private void goToWorld2Challenge1() throws IOException {
        LabyrinthModeProgressionController.selectedWorldIndex = 1;
        LabyrinthModeProgressionController.selectedChallengeIndex = 0;
        Main.goTo("LabyrinthModeProgression.fxml");
    }

    @FXML
    private void goToWorld2Challenge2() throws IOException {
        LabyrinthModeProgressionController.selectedWorldIndex = 1;
        LabyrinthModeProgressionController.selectedChallengeIndex = 1;
        Main.goTo("LabyrinthModeProgression.fxml");
    }

    @FXML
    private void goToWorld2Challenge3() throws IOException {
        LabyrinthModeProgressionController.selectedWorldIndex = 1;
        LabyrinthModeProgressionController.selectedChallengeIndex = 2;
        Main.goTo("LabyrinthModeProgression.fxml");
    }

    @FXML
    private void goToWorld3Challenge1() throws IOException {
        LabyrinthModeProgressionController.selectedWorldIndex = 2;
        LabyrinthModeProgressionController.selectedChallengeIndex = 0;
        Main.goTo("LabyrinthModeProgression.fxml");
    }

    @FXML
    private void goToWorld3Challenge2() throws IOException {
        LabyrinthModeProgressionController.selectedWorldIndex = 2;
        LabyrinthModeProgressionController.selectedChallengeIndex = 1;
        Main.goTo("LabyrinthModeProgression.fxml");
    }

    @FXML
    private void goToWorld3Challenge3() throws IOException {
        LabyrinthModeProgressionController.selectedWorldIndex = 2;
        LabyrinthModeProgressionController.selectedChallengeIndex = 2;
        Main.goTo("LabyrinthModeProgression.fxml");
    }

    @FXML
    private void goToAccueil() throws IOException {
        Main.goTo("AccueilLabyrinth.fxml");
    }
}
