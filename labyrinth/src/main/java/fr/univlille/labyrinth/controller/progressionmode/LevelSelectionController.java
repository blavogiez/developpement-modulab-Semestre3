package fr.univlille.labyrinth.controller.progressionmode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.univlille.labyrinth.App;
import fr.univlille.labyrinth.controller.AppState;
import fr.univlille.labyrinth.controller.VictoryNotification;
import fr.univlille.labyrinth.controller.progressionmode.labyrinthviewtype.ViewTypeFactory;
import fr.univlille.labyrinth.model.save.Challenge;
import fr.univlille.labyrinth.model.save.Level;
import fr.univlille.labyrinth.model.save.Player;
import fr.univlille.labyrinth.model.save.PlayerDatabase;
import fr.univlille.labyrinth.model.save.ViewType;
import fr.univlille.labyrinth.utils.ResizeUtil;
import fr.univlille.labyrinth.view.GameViewConfig;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class LevelSelectionController {
    private static final double BUTTON_SCALE = 2.0;
    private static final double ETAPE_PREF_HEIGHT = 300.0;
    private static final double ETAPE_PREF_WIDTH = 200.0;

    @FXML
    private javafx.scene.layout.BorderPane fond;
    @FXML
    private Text playerNameLabel;
    @FXML
    private Label scoreLabel;
    @FXML
    public Label textProgressBar;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private HBox menuEtape;

    private Player currentPlayer;
    private Level[] levels;
    private List<List<Button>> levelButtons = new ArrayList<>();
    private List<VBox> etapeVBoxes = new ArrayList<>();

    @FXML
    public void initialize() {
        String playerName = PlayerNameEntryController.playerName;
        currentPlayer = PlayerDatabase.loadPlayer(playerName);
        if (currentPlayer == null) {
            currentPlayer = new Player(playerName);
            PlayerDatabase.savePlayer(currentPlayer);
        }
        AppState.getInstance().setCurrentPlayer(currentPlayer);
        levels = currentPlayer.getProgress().getLevelProgress();

        generateLevelUI();
        setupButtons();
        playerNameLabel.setText(currentPlayer.getName());
        scoreLabel.setText("Score : " + currentPlayer.getScore());
        resize();

        String winner = VictoryNotification.getPendingWinner();
        if (winner != null) {
            VictoryNotification.show(fond, winner);
        }
    }

    private void generateLevelUI() {
        for (int levelIndex = 0; levelIndex < levels.length; levelIndex++) {
            VBox etapeVBox = createEtapeVBox(levelIndex);
            List<Button> challengeButtons = new ArrayList<>();

            for (int challengeIndex = 0; challengeIndex < levels[levelIndex].getChallenges().length; challengeIndex++) {
                Button btn = createChallengeButton(challengeIndex);
                challengeButtons.add(btn);
                etapeVBox.getChildren().add(btn);
            }

            levelButtons.add(challengeButtons);
            etapeVBoxes.add(etapeVBox);
            menuEtape.getChildren().add(etapeVBox);
        }
    }

    private VBox createEtapeVBox(int levelIndex) {
        VBox etapeVBox = new VBox();
        etapeVBox.setAlignment(javafx.geometry.Pos.CENTER);
        etapeVBox.getStyleClass().add("etapes");

        Label etapeLabel = new Label("Étape " + (levelIndex + 1));
        etapeLabel.setAlignment(javafx.geometry.Pos.CENTER);
        etapeVBox.getChildren().add(etapeLabel);

        return etapeVBox;
    }

    private Button createChallengeButton(int challengeIndex) {
        return new Button(String.valueOf(challengeIndex + 1));
    }

    @FXML
    private void goToAccueil() throws IOException {
        App.goTo("GameModeSelection.fxml");
    }

    private void setupButtons() {
        double completedCount = 0;
        double totalChallenges = 0;

        for (int levelIdx = 0; levelIdx < levelButtons.size(); levelIdx++) {
            List<Button> challengeButtons = levelButtons.get(levelIdx);
            Challenge[] challenges = levels[levelIdx].getChallenges();
            boolean levelLocked = currentPlayer.isLevelLocked(levelIdx);

            for (int challengeIdx = 0; challengeIdx < challengeButtons.size(); challengeIdx++) {
                Button btn = challengeButtons.get(challengeIdx);

                final int finalLevelIdx = levelIdx;
                final int finalChallengeIdx = challengeIdx;
                final Challenge challenge = challenges[challengeIdx];
                totalChallenges++;

                btn.setOnAction(e -> {
                    try {
                        goToChallenge(finalLevelIdx, finalChallengeIdx, challenge.getViewType());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });

                if (challenge.isCompleted()) {
                    btn.setStyle("-fx-background-color: " + GameViewConfig.COMPLETED.getColorCode() + ";");
                    completedCount++;
                } else {
                    btn.setStyle("");
                }

                Tooltip.install(btn, createTooltip(challenge));
                btn.setDisable(levelLocked);
            }
        }

        progressBar.setProgress(completedCount / totalChallenges);
        textProgressBar.setText(String.format("%.2f%%", completedCount / totalChallenges * 100));
    }

    private Tooltip createTooltip(Challenge challenge) {
        Tooltip tooltip = new Tooltip(challenge.toString());
        tooltip.setShowDelay(Duration.millis(200));
        tooltip.setShowDuration(Duration.seconds(30));
        tooltip.setHideDelay(Duration.millis(200));
        tooltip.setStyle("-fx-font-size: 12px; -fx-background-color: #333333; -fx-text-fill: white;");
        return tooltip;
    }

    /* fonction déterminant la page où mènera le bouton ; cela sera selon son type de vue, avec une page donnée par une Factory
    /* le défi sélectionné sera marqué dans AppState afin que le mode de progression puisse le retrouver facilement
     * 
     */
    private void goToChallenge(int levelIndex, int challengeIndex, ViewType viewType) throws IOException {
        AppState state = AppState.getInstance();
        state.setSelectedLevelIndex(levelIndex);
        state.setSelectedChallengeIndex(challengeIndex);

        Challenge selectedChallenge = state.getCurrentPlayer().getProgress().getLevelProgress()[levelIndex].getChallenges()[challengeIndex];
        state.setSelectedChallenge(selectedChallenge);

        App.goTo(ViewTypeFactory.getCorrespondingPage(viewType));
    }

    public void goToPlayerRanking() throws IOException {
        App.goTo("progressionmode/PlayerRanking.fxml");
    }
    
    private void resize(){
        menuEtape.widthProperty().addListener((o, oldW, newW) -> resizeEtapePanesInPane(menuEtape));
        menuEtape.heightProperty().addListener((o, oldH, newH) -> resizeEtapePanesInPane(menuEtape));

        for (VBox etape : etapeVBoxes) {
            etape.widthProperty().addListener((o, oldW, newW) -> resizeEtapeControlsInPane( etape));
            etape.heightProperty().addListener((o, oldH, newH) -> resizeEtapeControlsInPane( etape));
        }
    }

    public static void resizeEtapeControlsInPane(Pane parent){
        double width = parent.getWidth();
        double height = parent.getHeight();
        boolean isHBox = parent instanceof HBox;

        ObservableList<Node> childs = parent.getChildren();
        int nbChilds = childs.size();
        double boutonSize = Double.min(width*0.70,(height/nbChilds)*0.70);
        ResizeUtil.resizeControlsInPane( parent,boutonSize,boutonSize, 0, 0, (height/nbChilds)*0.2, 0);
    }

    public static void resizeEtapePanesInPane(Pane parent){
        double width = parent.getWidth();
        double height = parent.getHeight();

        ObservableList<Node> childs = parent.getChildren();
        int nbChilds = childs.size();
        ResizeUtil.resizePanesInPane( parent,(width/nbChilds)*0.80,height*0.95, 0, 0, 0, (width/nbChilds)*0.15);
    }
}
