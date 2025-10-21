package fr.univlille.labyrinth.model;

import fr.univlille.labyrinth.utils.ProgressionLoader;

public class ProgressionMode extends GameMode {
    private Player player;
    public static PlayerProgress defaultProgress;

    // execute at start to init default progress
    static {
        ProgressionMode.initDefaultProgress();
    }

    // Initialise l'objet defaultProgress à un objet PlayerProgress basé sur le fichier "default_progression.csv", par la classe utilitaire ProgressionLoader
    public static void initDefaultProgress() {
        ProgressionMode.defaultProgress = ProgressionLoader.loadDefaultProgress();
    }

    /** 
     * Surcharge qui prend les attributs dans le Défi passé en paramètre
     * @param chosenChallenge
     */
    public void createMaze(Challenge chosenChallenge) {
        int width = chosenChallenge.getWidth();
        int height = chosenChallenge.getHeight();
        double wallPercentage = chosenChallenge.getWallPercentage();
        int minPathLength = chosenChallenge.getDistanceBetweenEntryAndExit();
        createMaze(width, height, wallPercentage, minPathLength);
    }

    /** 
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }
}
