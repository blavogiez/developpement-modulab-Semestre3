package fr.univlille.labyrinth.controller.progressionmode;

import fr.univlille.labyrinth.Main;
import fr.univlille.labyrinth.controller.AppState;
import fr.univlille.labyrinth.model.save.Challenge;
import fr.univlille.labyrinth.model.save.Player;
import fr.univlille.labyrinth.model.save.PlayerDatabase;
import fr.univlille.labyrinth.view.GameColors;
import fr.univlille.labyrinth.utils.ResizeUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Controller du menu progression
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class LevelSelectionController {
    @FXML
    private Text playerNameLabel;
    @FXML
    private Label scoreLabel;

    private List<List<Button>> levelButtons=new ArrayList<>();

    @FXML
    private Button bouttonLevel1Challenge1;
    @FXML
    private Button bouttonLevel1Challenge2;
    @FXML
    private Button bouttonLevel1Challenge3;
    @FXML
    private Button bouttonLevel2Challenge1;
    @FXML
    private Button bouttonLevel2Challenge2;
    @FXML
    private Button bouttonLevel2Challenge3;
    @FXML
    private Button bouttonLevel3Challenge1;
    @FXML
    private Button bouttonLevel3Challenge2;
    @FXML
    private Button bouttonLevel3Challenge3;
    @FXML
    public Label textProgressBar;
    @FXML
    private ProgressBar progressBar;

    @FXML
    private HBox menuEtape;

    @FXML
    private VBox etape1;

    @FXML
    private VBox etape2;

    @FXML
    private VBox etape3;

    /**
     * Initialisation de la scène
     */
    @FXML
    public void initialize() {
        String playerName = PlayerNameEntryController.playerName;
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
        addTooltips();
        playerNameLabel.setText(currentPlayer.getName());
        scoreLabel.setText("Score : " + currentPlayer.getScore());

        resize();
    }


    /**
     * @throws IOException Renvoie une IOException si la scène est inaccessible.
     */
    @FXML
    private void goToAccueil() throws IOException {
        Main.goTo("GameModeSelection.fxml");
    }


    /**
     * Colorie les boutons en vert s'ils ont étaient effectué auparavant
     */
    private void colorButtons() {
        double completedCount=0;
        double totalChallenges=0;
        Player currentPlayer=AppState.getInstance().getCurrentPlayer();

        for (int levelIndex=0;levelIndex<levelButtons.size();levelIndex++) {

            List<Button> challengeButtons=levelButtons.get(levelIndex);
            Challenge[] challenges=currentPlayer.getProgress().getLevelProgress()[levelIndex].getChallenges();

            for (int challengeIndex=0;challengeIndex<challengeButtons.size();challengeIndex++) {

                Button button=challengeButtons.get(challengeIndex);
                Challenge challenge=challenges[challengeIndex];
                totalChallenges++;

                if (challenge.isCompleted()) {
                    button.setStyle("-fx-background-color: "+GameColors.COMPLETED+";");
                    completedCount++;
                } else {
                    button.setStyle("");
                }
            }
        }
        progressBar.setProgress(completedCount/totalChallenges);
        textProgressBar.setText(String.format("%.2f%%", completedCount/totalChallenges* 100));
    }

    /**
     * Associe les boutons aux tooltips (Boite d'informations quand l'utilisateur survole (hover) un bouton)
     */
    private void addTooltips() {
        Player currentPlayer = AppState.getInstance().getCurrentPlayer();
        for (int levelIndex = 0; levelIndex < levelButtons.size(); levelIndex++) {
            List<Button> challengeButtons = levelButtons.get(levelIndex);
            Challenge[] challenges = currentPlayer.getProgress().getLevelProgress()[levelIndex].getChallenges();

            for (int challengeIndex = 0; challengeIndex < challengeButtons.size(); challengeIndex++) {
                Button button = challengeButtons.get(challengeIndex);
                Challenge challenge = challenges[challengeIndex];


                StringBuilder tooltipText = getTooltipText(challenge);

                addTextRecordTime(challenge, tooltipText);

                addToolTypeOnButton(tooltipText, button);
            }
        }
    }

    /**
     * Si le challenge à un record, l'ajoute dans le tooltip
     */
    private static void addTextRecordTime(Challenge challenge, StringBuilder tooltipText) {
        if (challenge.isCompleted() && challenge.getTimeCompleted() > 0) {
            double timeInSeconds = challenge.getTimeCompleted() / 1000.0;
            tooltipText.append("\nTemps : ").append(String.format("%.1fs", timeInSeconds));
        }
    }

    /**
     * Associe le bouton et le tooltip
     */
    private static void addToolTypeOnButton(StringBuilder tooltipText, Button button) {
        Tooltip tooltip = new Tooltip(tooltipText.toString());
        tooltip.setShowDelay(Duration.millis(200));
        tooltip.setShowDuration(Duration.seconds(30));
        tooltip.setHideDelay(Duration.millis(200));
        tooltip.setStyle("-fx-font-size: 12px; -fx-background-color: #333333; -fx-text-fill: white;");
        Tooltip.install(button, tooltip); // Utilise install() au lieu de setTooltip()
    }

    /**
     * Créer le texte, qui sera mis dans le futur tooltip
     */
    private static StringBuilder getTooltipText(Challenge challenge) {
        StringBuilder tooltipText = new StringBuilder();
        tooltipText.append("Difficulté : ").append(challenge.getDifficulty()).append("\n");
        tooltipText.append("Dimensions : ").append(challenge.getWidth())
                  .append("x").append(challenge.getHeight()).append("\n");
        tooltipText.append("Murs : ").append(String.format("%.0f%%", challenge.getWallPercentage() * 100)).append("\n");
        tooltipText.append("Distance : ").append(challenge.getDistanceBetweenEntryAndExit());
        return tooltipText;
    }

    private void initList(){
        levelButtons = List.of(
                List.of(bouttonLevel1Challenge1, bouttonLevel1Challenge2, bouttonLevel1Challenge3),
                List.of(bouttonLevel2Challenge1, bouttonLevel2Challenge2, bouttonLevel2Challenge3),
                List.of(bouttonLevel3Challenge1, bouttonLevel3Challenge2, bouttonLevel3Challenge3)
        );
    }

    private void initBoutons() {
        Object[][] challenges = {
            {bouttonLevel1Challenge1, 0, 0, false},
            {bouttonLevel1Challenge2, 0, 1, false},
            {bouttonLevel1Challenge3, 0, 2, false},
            {bouttonLevel2Challenge1, 1, 0, false},
            {bouttonLevel2Challenge2, 1, 1, false},
            {bouttonLevel2Challenge3, 1, 2, false},
            {bouttonLevel3Challenge1, 2, 0, true},
            {bouttonLevel3Challenge2, 2, 1, true},
            {bouttonLevel3Challenge3, 2, 2, true}
        };

        for (Object[] ch : challenges) {
            Button btn = (Button) ch[0];
            int levelIdx = (int) ch[1];
            int challengeIdx = (int) ch[2];
            boolean limited = (boolean) ch[3];
            btn.setOnAction(e -> {
                try {
                    goToChallenge(levelIdx, challengeIdx, limited);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        }
    }

    private void initEtapesAccessibles() {
        Player currentPlayer = AppState.getInstance().getCurrentPlayer();
        int highestLevel = currentPlayer.getHighestLevel();

        if (highestLevel < 1) {
            for (Button btn : levelButtons.get(1)) {
                btn.setDisable(true);
            }
        }

        if (highestLevel < 2) {
            for (Button btn : levelButtons.get(2)) {
                btn.setDisable(true);
            }
        }
    }


    /**
     * Cette méthode récupère les informations d'un challenge, et change la scène du stage en fonction de ses paramètres.
     *
     * @param levelIndex numéro de l'étape.
     * @param challengeIndex numéro du challenge.
     * @param limited si true, renvoie un labyrinthe avec la vue limité
     * @throws IOException Renvoie une IOException si la scène est inaccessible.
     */
    private void goToChallenge(int levelIndex, int challengeIndex, boolean limited) throws IOException {
        AppState state = AppState.getInstance();
        state.setSelectedLevelIndex(levelIndex);
        state.setSelectedChallengeIndex(challengeIndex);

        Challenge selectedChallenge = state.getCurrentPlayer()
            .getProgress()
            .getLevelProgress()[levelIndex]
            .getChallenges()[challengeIndex];
        state.setSelectedChallenge(selectedChallenge);

        if (limited) {
            Main.goTo("progressionmode/LimitedViewProgressionModeLabyrinth.fxml");
        } else {
            Main.goTo("progressionmode/ProgressionModeLabyrinth.fxml");
        }
    }

    /**
     * Cette méthode permet de rendre la scène responsive/dynamique
     */
    private void resize(){
        menuEtape.widthProperty().addListener((o, oldW, newW) -> ResizeUtil.redimensionnerPanePanes(menuEtape));
        menuEtape.heightProperty().addListener((o, oldH, newH) -> ResizeUtil.redimensionnerPanePanes(menuEtape));
        etape1.widthProperty().addListener((o, oldW, newW) -> ResizeUtil.redimensionnerVboxControles(etape1,0.08,0,0,0.14,0));
        etape1.heightProperty().addListener((o, oldH, newH) -> ResizeUtil.redimensionnerVboxControles(etape1,0.08,0,0,0.14,0));
        etape2.widthProperty().addListener((o, oldW, newW) -> ResizeUtil.redimensionnerVboxControles(etape2,0.08,0,0,0.14,0));
        etape2.heightProperty().addListener((o, oldH, newH) -> ResizeUtil.redimensionnerVboxControles(etape2,0.08,0,0,0.14,0));
        etape3.widthProperty().addListener((o, oldW, newW) -> ResizeUtil.redimensionnerVboxControles(etape3,0.08,0,0,0.14,0));
        etape3.heightProperty().addListener((o, oldH, newH) -> ResizeUtil.redimensionnerVboxControles(etape3,0.08,0,0,0.14,0));
    }
}
