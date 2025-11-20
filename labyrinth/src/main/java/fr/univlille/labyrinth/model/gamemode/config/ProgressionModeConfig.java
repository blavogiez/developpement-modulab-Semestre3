package fr.univlille.labyrinth.model.gamemode.config;

import fr.univlille.labyrinth.model.algorithm.MazeAlgorithm;
import fr.univlille.labyrinth.model.algorithm.MazeAlgorithmFactory;
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
        return challenge.getEntitiesConfiguration();
    }

    /** 
     * @return boolean
     */
    @Override
    public String getTrapsConfiguration(){
        return challenge.getTrapsConfiguration();
    }

    /** 
     * @return boolean
     */
    @Override
    public boolean isPerfectAlgorithm() {
        return MazeAlgorithmFactory.valueOf(challenge.getAlgorithmName()).isPerfect();
    }

    /** 
     * @return Challenge
     */
    public Challenge getChallenge() {
        return challenge;
    }
}
