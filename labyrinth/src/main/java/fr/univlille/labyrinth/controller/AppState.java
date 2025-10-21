package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.model.Challenge;
import fr.univlille.labyrinth.model.Player;

// singleton qui gere l'etat globale de l'application
// developpé suite à la réalisation que les variables static dans les controlleurs étaient difficiles à transmettre entre chacun et peu sécurisées

// évite donc l'utilisation de variables static à passer partout et rend l'app testable
public class AppState {
    private static AppState instance;

    private Player currentPlayer;
    private int selectedLevelIndex;
    private int selectedChallengeIndex;
    private Challenge selectedChallenge;

    // constructeur par défaut
    private AppState() {}

    /** 
     * @return AppState
     */
    // Retourne l'instance unique du singleton
    public static AppState getInstance() {
        if (instance == null) {
            instance = new AppState();
        }
        return instance;
    }

    public void reset() {
        currentPlayer = null;
        selectedLevelIndex = 0;
        selectedChallengeIndex = 0;
        selectedChallenge = null;
    }

    /** 
     * @return Player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /** 
     * @param currentPlayer
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * @return int
     */
    public int getSelectedLevelIndex() {
        return selectedLevelIndex;
    }

    /**
     * @param selectedLevelIndex
     */
    public void setSelectedLevelIndex(int selectedLevelIndex) {
        this.selectedLevelIndex = selectedLevelIndex;
    }

    /** 
     * @return int
     */
    public int getSelectedChallengeIndex() {
        return selectedChallengeIndex;
    }

    /** 
     * @param selectedChallengeIndex
     */
    public void setSelectedChallengeIndex(int selectedChallengeIndex) {
        this.selectedChallengeIndex = selectedChallengeIndex;
    }

    /** 
     * @return Challenge
     */
    public Challenge getSelectedChallenge() {
        return selectedChallenge;
    }

    /** 
     * @param selectedChallenge
     */
    public void setSelectedChallenge(Challenge selectedChallenge) {
        this.selectedChallenge = selectedChallenge;
    }
}
