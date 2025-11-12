package fr.univlille.labyrinth.model.gamemode.config;

import fr.univlille.labyrinth.model.algorithm.MazeAlgorithm;
import fr.univlille.labyrinth.model.algorithm.MazeAlgorithmFactory;

public class FreeModeConfig implements GameConfig {
    private MazeAlgorithmFactory algorithmFactory;
    private int width;
    private int height;
    private double wallPercentage;
    private int distanceBetweenEntryAndExit;
    private String entityConfiguration ;

    /*
     * Constructeur par défaut
     */
    public FreeModeConfig() {
        this(MazeAlgorithmFactory.PERFECT, 20, 20, 0.4, 10);
    }

    public FreeModeConfig(MazeAlgorithmFactory algorithmFactory, int width, int height,
                          double wallPercentage, int distanceBetweenEntryAndExit) {
        this.algorithmFactory = algorithmFactory;
        this.width = width;
        this.height = height;
        this.wallPercentage = wallPercentage;
        this.distanceBetweenEntryAndExit = distanceBetweenEntryAndExit;
    }

    /** 
     * @return MazeAlgorithm
     */
    @Override
    public MazeAlgorithm getAlgorithm() {
        return algorithmFactory.getAlgorithm();
    }

    /** 
     * @return int
     */
    @Override
    public int getWidth() {
        return width;
    }

    /** 
     * @return int
     */
    @Override
    public int getHeight() {
        return height;
    }

    /** 
     * @return double
     */
    @Override
    public double getWallPercentage() {
        return wallPercentage;
    }

    /** 
     * @return int
     */
    @Override
    public int getDistanceBetweenEntryAndExit() {
        return distanceBetweenEntryAndExit;
    }

    /** 
     * @return boolean
     */
    @Override
    public boolean isPerfectAlgorithm() {
        return algorithmFactory.isPerfect();
    }

    /** 
     * @return MazeAlgorithmFactory
     */
    public MazeAlgorithmFactory getAlgorithmFactory() {
        return algorithmFactory;
    }

    /** 
     * @param algorithmFactory
     */
    public void setAlgorithmFactory(MazeAlgorithmFactory algorithmFactory) {
        this.algorithmFactory = algorithmFactory;
    }

    /** 
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /** 
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /** 
     * @param wallPercentage
     */
    public void setWallPercentage(double wallPercentage) {
        this.wallPercentage = wallPercentage;
    }

    /** 
     * @param distanceBetweenEntryAndExit
     */
    public void setDistanceBetweenEntryAndExit(int distanceBetweenEntryAndExit) {
        this.distanceBetweenEntryAndExit = distanceBetweenEntryAndExit;
    }

    /** 
     * @return String
     */
    public String getEntitiesConfiguration() {
        return entityConfiguration;
    }

    public void setEntitiesConfiguration(String entityConfiguration) {
        this.entityConfiguration = entityConfiguration;
    }
}
