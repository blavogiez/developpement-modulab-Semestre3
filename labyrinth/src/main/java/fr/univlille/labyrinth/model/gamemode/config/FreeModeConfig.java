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

    @Override
    public MazeAlgorithm getAlgorithm() {
        return algorithmFactory.getAlgorithm();
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public double getWallPercentage() {
        return wallPercentage;
    }

    @Override
    public int getDistanceBetweenEntryAndExit() {
        return distanceBetweenEntryAndExit;
    }

    @Override
    public boolean isPerfectAlgorithm() {
        return algorithmFactory.isPerfect();
    }

    public MazeAlgorithmFactory getAlgorithmFactory() {
        return algorithmFactory;
    }

    public void setAlgorithmFactory(MazeAlgorithmFactory algorithmFactory) {
        this.algorithmFactory = algorithmFactory;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWallPercentage(double wallPercentage) {
        this.wallPercentage = wallPercentage;
    }

    public void setDistanceBetweenEntryAndExit(int distanceBetweenEntryAndExit) {
        this.distanceBetweenEntryAndExit = distanceBetweenEntryAndExit;
    }

    public String getEntitiesConfiguration() {
        return entityConfiguration;
    }
}
