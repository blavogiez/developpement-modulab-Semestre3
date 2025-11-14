package fr.univlille.labyrinth.controller.progressionmode;

import java.io.IOException;

import fr.univlille.labyrinth.App;
import fr.univlille.labyrinth.controller.AppState;
import fr.univlille.labyrinth.controller.LabyrinthController;
import fr.univlille.labyrinth.controller.PopupVictoryController;
import fr.univlille.labyrinth.model.gamemode.GameMode;
import fr.univlille.labyrinth.model.gamemode.ProgressionMode;
import fr.univlille.labyrinth.model.save.Challenge;
import fr.univlille.labyrinth.model.save.Player;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Classe abstraite pour les controller des labyrinthes du mode progression regroupant les comportements communs
 * Les types hérités diffèreront surtout dans leur représentation des vues (Différents types selon l'étape)
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public abstract class ProgressionModeLabyrinthController extends LabyrinthController<ProgressionMode> {

    @FXML
    protected Button bouttonRetour;

    @FXML
    protected Label challengeInfoLabel;

    /** 
     * @param initializeGameMode(
     * @return Node
     */
    // Initialise les vues de labyrinthe nécessaires (abstrait car différents comportements possibles)
    protected abstract Node setupViews(ProgressionMode gameMode);

    /** 
     * @param initializeGameMode(
     * @return String
     */
    // Dans les informations (Label), on ajoute le suffixe correspondant au type de vue (donc, abstrait)
    protected abstract String getViewSuffix();

    @Override
    protected void initializeGameMode() {
        Challenge selectedChallenge = AppState.getInstance().getSelectedChallenge();
        int selectedLevelIndex = AppState.getInstance().getSelectedLevelIndex();
        int selectedChallengeIndex = AppState.getInstance().getSelectedChallengeIndex();
        Player currentPlayer = AppState.getInstance().getCurrentPlayer();

        challengeInfoLabel.setText("Étape " + (selectedLevelIndex + 1) + ", Défi " + (selectedChallengeIndex + 1) + getViewSuffix());

        gameMode = new ProgressionMode(currentPlayer, selectedChallenge);
        gameMode.createMaze();

        pane1.setCenter(setupViews(gameMode));

        gameMode.setChronometre(chrono);
    }

    @Override
    public void handleVictory() {
        try {
            PopupVictoryController.openPopup(gameMode.getPlayer().getName());
            goToProgression();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 
     * @throws IOException
     */
    @FXML
    public void goToProgression() throws IOException {
        App.goTo("progressionmode/LevelSelection.fxml");
    }
}