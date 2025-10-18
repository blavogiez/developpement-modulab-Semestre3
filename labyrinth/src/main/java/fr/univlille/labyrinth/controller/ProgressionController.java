package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.Main;
import fr.univlille.labyrinth.model.Player;
import fr.univlille.labyrinth.model.PlayerDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProgressionController {
    public static String sheet;
    @FXML
    private Text playerNameLabel;
    @FXML
    private Text scoreLabel;
    public static Player currentPlayer;
    private List<List<Button>> worldButtons=new ArrayList<>();
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
        currentPlayer = PlayerDatabase.loadPlayer(playerName);
        if (currentPlayer == null) {
            currentPlayer = new Player(playerName);
            PlayerDatabase.savePlayer(currentPlayer);
        }
        initList();
        colorButtons();
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

    // les défis de l'étape 3 utilisent une autre vue, donc encapsulée dans un autre controleur
    @FXML
    private void goToWorld3Challenge1() throws IOException {
        LimitedLabyrinthModeProgressionController.selectedWorldIndex = 2;
        LimitedLabyrinthModeProgressionController.selectedChallengeIndex = 0;
        Main.goTo("LimitedLabyrinthModeProgression.fxml");
    }


    @FXML
    private void goToWorld3Challenge2() throws IOException {
        LimitedLabyrinthModeProgressionController.selectedWorldIndex = 2;
        LimitedLabyrinthModeProgressionController.selectedChallengeIndex = 1;
        Main.goTo("LimitedLabyrinthModeProgression.fxml");
    }

    @FXML
    private void goToWorld3Challenge3() throws IOException {
        LimitedLabyrinthModeProgressionController.selectedWorldIndex = 2;
        LimitedLabyrinthModeProgressionController.selectedChallengeIndex = 2;
        Main.goTo("LimitedLabyrinthModeProgression.fxml");
    }

    @FXML
    private void goToAccueil() throws IOException {
        Main.goTo("AccueilLabyrinth.fxml");
    }


    private void colorButtons() {
        String[] colors = {"#1aff00", "#f9ff25", "#ff0000"};
        int world = LabyrinthModeProgressionController.selectedWorldIndex;
        int chall = LabyrinthModeProgressionController.selectedChallengeIndex;
        if (world >= 0 && world < worldButtons.size() && chall >= 0 && chall < colors.length) {
            Button btn = worldButtons.get(world).get(chall);
            if (btn.getStyle() == null || btn.getStyle().isEmpty()) {
                btn.setStyle("-fx-background-color: " + colors[chall] + ";");
            }
        }
    }

    private void initList(){
        worldButtons = List.of(
                List.of(bouttonWorld1Challenge1, bouttonWorld1Challenge2, bouttonWorld1Challenge3),
                List.of(bouttonWorld2Challenge1, bouttonWorld2Challenge2, bouttonWorld2Challenge3),
                List.of(bouttonWorld3Challenge1, bouttonWorld3Challenge2, bouttonWorld3Challenge3)
        );
    }
}
