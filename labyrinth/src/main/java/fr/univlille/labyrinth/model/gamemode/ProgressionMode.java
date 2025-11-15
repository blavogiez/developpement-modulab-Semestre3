package fr.univlille.labyrinth.model.gamemode;


import fr.univlille.labyrinth.model.algorithm.MazeAlgorithm;
import fr.univlille.labyrinth.model.algorithm.MazeAlgorithmFactory;
import fr.univlille.labyrinth.model.gamemode.config.ProgressionModeConfig;
import fr.univlille.labyrinth.model.gamemode.manager.MazeManager;
import fr.univlille.labyrinth.model.gamemode.victory.ProgressionModeVictoryHandler;
import fr.univlille.labyrinth.model.gamemode.victory.VictoryHandler;

import fr.univlille.labyrinth.model.save.Challenge;
import fr.univlille.labyrinth.model.save.Player;
import fr.univlille.labyrinth.model.save.PlayerProgress;
import fr.univlille.labyrinth.utils.ProgressionLoader;
import fr.univlille.labyrinth.utils.Timer;

public class ProgressionMode extends GameMode {
    private Challenge challenge;
    private ProgressionModeVictoryHandler victoryHandler;
    public static PlayerProgress defaultProgress;

    // execute at start to init default progress
    static {
        ProgressionMode.initDefaultProgress();
    }

    /**
     * Constructeur principal avec injection complète des dépendances (respecte le DIP)
     * @param mazeManager le gestionnaire de labyrinthe
     * @param victoryHandler le gestionnaire de victoire
     * @param player le joueur
     * @param challenge le défi
     */
    public ProgressionMode(MazeManager mazeManager, ProgressionModeVictoryHandler victoryHandler, Player player, Challenge challenge) {
        super(mazeManager, victoryHandler, new ProgressionModeConfig(challenge));
        this.challenge = challenge;
        this.victoryHandler = victoryHandler;
    }

    /**
     * Constructeur de compatibilité avec création des dépendances
     * @param player le joueur
     * @param challenge le défi
     */
    public ProgressionMode(Player player, Challenge challenge) {
        this(new MazeManager(), new ProgressionModeVictoryHandler(player, challenge, null), player, challenge);
    }

    // Initialise l'objet defaultProgress à un objet PlayerProgress basé sur le fichier "default_progression.csv", par la classe utilitaire ProgressionLoader
    public static void initDefaultProgress() {
        ProgressionMode.defaultProgress = ProgressionLoader.loadDefaultProgress();
    }


    public void createMaze() {
        getMazeManager().createMaze(config);
    }

    /**
     * @return Player
     */
    public Player getPlayer() {
        return victoryHandler != null ? victoryHandler.getPlayer() : null;
    }

    /**
     * @return Challenge
     */
    public Challenge getChallenge() {
        return challenge;
    }

    /**
     * @param chrono
     */
    public void setChronometre(Timer chrono) {
        if (victoryHandler != null) {
            victoryHandler.setTimer(chrono);
        }
    }

    /**
     * @return String
     */
    public String toString() {
        String info = "Dimensions : " + config.getWidth() + "x" + config.getHeight();
        info += ", Pourcentage : " + (int) (config.getWallPercentage() * 100) + "%";
        int distance = getCurrentMaze()!=null ? getCurrentMaze().getDistanceBetweenEntryAndExit() : config.getDistanceBetweenEntryAndExit();
        info += ", Distance entrée/sortie : " + distance;
        return info;
    }
}
