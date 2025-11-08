package fr.univlille.labyrinth.model.gamemode.config;

import fr.univlille.labyrinth.model.algorithm.MazeAlgorithm;

public interface GameConfig {
    MazeAlgorithm getAlgorithm();
    int getWidth();
    int getHeight();
    double getWallPercentage();
    int getDistanceBetweenEntryAndExit();
    boolean isPerfectAlgorithm();
    String getEntitiesConfiguration();
}
