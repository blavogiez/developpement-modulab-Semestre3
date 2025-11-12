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

    /** 
     * @return MazeAlgorithm
     */
    @Override
    public MazeAlgorithm getAlgorithm() {
        return challenge.getAlgorithm();
    }

    /** 
     * @return int
     */
    @Override
    public int getWidth() {
        return challenge.getWidth();
    }

    /** 
     * @return int
     */
    @Override
    public int getHeight() {
        return challenge.getHeight();
    }

    /** 
     * @return double
     */
    @Override
    public double getWallPercentage() {
        return challenge.getWallPercentage();
    }

    /** 
     * @return int
     */
    @Override
    public int getDistanceBetweenEntryAndExit() {
        return challenge.getDistanceBetweenEntryAndExit();
    }

    /** 
     * @return String
     */
    @Override
    public String getEntitiesConfiguration() {
        String config = challenge.getEntitiesConfiguration();
        return config;
    }

    /** 
     * @return boolean
     */
    @Override
    public boolean isPerfectAlgorithm() {
        return true;
    }

    /** 
     * @return Challenge
     */
    public Challenge getChallenge() {
        return challenge;
    }
}
