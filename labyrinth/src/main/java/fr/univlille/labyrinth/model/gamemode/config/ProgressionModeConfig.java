package fr.univlille.labyrinth.model.gamemode.config;

import fr.univlille.labyrinth.model.algorithm.MazeAlgorithm;
import fr.univlille.labyrinth.model.save.Challenge;

/*
 * Configuration du mode progression dictée par un défi
 */
public class ProgressionModeConfig implements GameConfig {
    private Challenge challenge;

    public ProgressionModeConfig(Challenge challenge) {
        this.challenge = challenge;
    }

    @Override
    public MazeAlgorithm getAlgorithm() {
        return challenge.getAlgorithm();
    }

    @Override
    public int getWidth() {
        return challenge.getWidth();
    }

    @Override
    public int getHeight() {
        return challenge.getHeight();
    }

    @Override
    public double getWallPercentage() {
        return challenge.getWallPercentage();
    }

    @Override
    public int getDistanceBetweenEntryAndExit() {
        return challenge.getDistanceBetweenEntryAndExit();
    }

    @Override
    public String getEntitiesConfiguration() {
        String config = challenge.getEntitiesConfiguration();
        return config;
    }

    @Override
    public boolean isPerfectAlgorithm() {
        return true;
    }

    public Challenge getChallenge() {
        return challenge;
    }
}
