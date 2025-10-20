package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.Main;
import fr.univlille.labyrinth.model.Challenge;
import fr.univlille.labyrinth.model.Player;
import fr.univlille.labyrinth.model.PlayerDatabase;
import fr.univlille.labyrinth.view.GameColors;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProgressionController {
    @FXML
    private Text playerNameLabel;
    @FXML
    private Text scoreLabel;

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
        Player currentPlayer = PlayerDatabase.loadPlayer(playerName);
        if (currentPlayer == null) {
            currentPlayer = new Player(playerName);
            PlayerDatabase.savePlayer(currentPlayer);
        }
        AppState.getInstance().setCurrentPlayer(currentPlayer);
        initList();
        initBoutons();
        initEtapesAccessibles();
        colorButtons();
        playerNameLabel.setText(currentPlayer.getName());
        scoreLabel.setText("Score : " + currentPlayer.getScore());
    }

    /** 
     * @throws IOException
     */
    @FXML
    private void goToAccueil() throws IOException {
        Main.goTo("AccueilLabyrinth.fxml");
    }


    // colore en vert les boutons des défis complétés
    private void colorButtons() {
        Player currentPlayer = AppState.getInstance().getCurrentPlayer();
        for (int worldIndex = 0; worldIndex < worldButtons.size(); worldIndex++) {
            List<Button> challengeButtons = worldButtons.get(worldIndex);
            Challenge[] challenges = currentPlayer.getProgress().getStageProgress()[worldIndex].getChallenges();

            for (int challengeIndex = 0; challengeIndex < challengeButtons.size(); challengeIndex++) {
                Button button = challengeButtons.get(challengeIndex);
                Challenge challenge = challenges[challengeIndex];

                if (challenge.isCompleted()) {
                    button.setStyle("-fx-background-color: " + GameColors.COMPLETED + ";");
                } else {
                    button.setStyle("");
                }
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

    private void initBoutons() {
        // associer un index d'étape et de défi aux boutons
        Object[][] challenges = {
            {bouttonWorld1Challenge1, 0, 0, false},
            {bouttonWorld1Challenge2, 0, 1, false},
            {bouttonWorld1Challenge3, 0, 2, false},
            {bouttonWorld2Challenge1, 1, 0, false},
            {bouttonWorld2Challenge2, 1, 1, false},
            {bouttonWorld2Challenge3, 1, 2, false},
            {bouttonWorld3Challenge1, 2, 0, true},
            {bouttonWorld3Challenge2, 2, 1, true},
            {bouttonWorld3Challenge3, 2, 2, true}
        };

        // associer la fonction selon l'index d'étape et de défi associés
        for (Object[] ch : challenges) {
            Button btn = (Button) ch[0];
            int worldIdx = (int) ch[1];
            int challengeIdx = (int) ch[2];
            boolean limited = (boolean) ch[3];
            btn.setOnAction(e -> {
                try {
                    goToChallenge(worldIdx, challengeIdx, limited);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        }
    }

    // desactive les boutons des etapes 2 et 3 si le joueur n'a pas fait au moin un defi de l'etape precedente
    private void initEtapesAccessibles() {
        Player currentPlayer = AppState.getInstance().getCurrentPlayer();
        int highestStage = currentPlayer.getHighestStage();

        // etape 2 accessible seulement si au moin un defi de l'etape 1 est complété (highestStage >= 1)
        if (highestStage < 1) {
            for (Button btn : worldButtons.get(1)) {
                btn.setDisable(true);
            }
        }

        // etape 3 accessible seulement si au moin un defi de l'etape 2 est complété (highestStage >= 2)
        if (highestStage < 2) {
            for (Button btn : worldButtons.get(2)) {
                btn.setDisable(true);
            }
        }
    }


    /** 
     * @param worldIndex
     * @param challengeIndex
     * @param limited
     * @throws IOException
     */
    private void goToChallenge(int worldIndex, int challengeIndex, boolean limited) throws IOException {
        AppState state = AppState.getInstance();
        state.setSelectedWorldIndex(worldIndex);
        state.setSelectedChallengeIndex(challengeIndex);

        // calculer le challenge selectionné avant la navigation
        Challenge selectedChallenge = state.getCurrentPlayer()
            .getProgress()
            .getStageProgress()[worldIndex]
            .getChallenges()[challengeIndex];
        state.setSelectedChallenge(selectedChallenge);

        if (limited) {
            Main.goTo("LimitedLabyrinthModeProgression.fxml");
        } else {
            Main.goTo("LabyrinthModeProgression.fxml");
        }
    }
}
