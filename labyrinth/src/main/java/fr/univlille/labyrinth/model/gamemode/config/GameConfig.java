package fr.univlille.labyrinth.model.gamemode.config;

import fr.univlille.labyrinth.model.algorithm.MazeAlgorithm;

/*
 * Configuration donnée qui dictera les paramètres du labyrinthe.
 */
public interface GameConfig {
    MazeAlgorithm getAlgorithm();
    int getWidth();
    int getHeight();
    double getWallPercentage();
    int getDistanceBetweenEntryAndExit();
    boolean isPerfectAlgorithm();
    String getEntitiesConfiguration();
    String getTrapsConfiguration();
}
