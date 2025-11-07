package fr.univlille.labyrinth.model.gamemode;

import fr.univlille.labyrinth.model.gamemode.config.ProgressionModeConfig;
import fr.univlille.labyrinth.model.gamemode.manager.MazeManager;
import fr.univlille.labyrinth.model.gamemode.victory.ProgressionModeVictoryHandler;
import fr.univlille.labyrinth.model.save.Challenge;
import fr.univlille.labyrinth.model.save.Player;
import fr.univlille.labyrinth.model.save.PlayerProgress;
import fr.univlille.labyrinth.utils.ProgressionLoader;
import fr.univlille.labyrinth.utils.Timer;

public class ProgressionMode extends GameMode {
    private ProgressionModeConfig config;
    private ProgressionModeVictoryHandler victoryHandler;
    public static PlayerProgress defaultProgress;

    // execute at start to init default progress
    static {
        ProgressionMode.initDefaultProgress();
    }

    public ProgressionMode(Player player, Challenge challenge) {
        super(new MazeManager(), new ProgressionModeVictoryHandler(player, challenge, null));
        this.config = new ProgressionModeConfig(challenge);
        this.victoryHandler = (ProgressionModeVictoryHandler) getVictoryHandler();
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

    public Challenge getChallenge() {
        return config.getChallenge();
    }

    public void setChronometre(Timer chrono) {
        if (victoryHandler != null) {
            victoryHandler.setTimer(chrono);
        }
    }

    public String toString() {
        Challenge challenge = config.getChallenge();
        String info = "Dimensions : " + challenge.getWidth() + "x" + challenge.getHeight();
        info += ", Pourcentage : " + (int) (challenge.getWallPercentage() * 100) + "%";
        info += ", Distance entrée/sortie : " + challenge.getDistanceBetweenEntryAndExit();
        return info;
    }
}
