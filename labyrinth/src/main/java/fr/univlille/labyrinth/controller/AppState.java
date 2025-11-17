package fr.univlille.labyrinth.controller;

import fr.univlille.labyrinth.model.gamemode.config.FreeModeConfig;
import fr.univlille.labyrinth.model.save.Challenge;
import fr.univlille.labyrinth.model.save.Player;

public class AppState {
    private static AppState instance;

    private Player currentPlayer;
    private int selectedLevelIndex;
    private int selectedChallengeIndex;
    private Challenge selectedChallenge;
    private FreeModeConfig freeModeConfig;
    private String pendingNotificationMessage;
    private boolean pendingNotificationIsVictory = true;

    private AppState() {
        this.freeModeConfig = new FreeModeConfig();
    }

    /** 
     * @return retourne le singleton AppState, et en créer un s'il n'existait pas
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
        freeModeConfig = new FreeModeConfig();
        pendingNotificationMessage = null;
        pendingNotificationIsVictory = true;
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

    public FreeModeConfig getFreeModeConfig() {
        return freeModeConfig;
    }

    public void setFreeModeConfig(FreeModeConfig freeModeConfig) {
        this.freeModeConfig = freeModeConfig;
    }

    public String getPendingNotificationMessage() {
        String message = pendingNotificationMessage;
        pendingNotificationMessage = null;
        return message;
    }

    public void setPendingNotificationMessage(String message) {
        this.pendingNotificationMessage = message;
    }

    public boolean isPendingNotificationVictory() {
        return pendingNotificationIsVictory;
    }

    public void setPendingNotificationIsVictory(boolean isVictory) {
        this.pendingNotificationIsVictory = isVictory;
    }
}
