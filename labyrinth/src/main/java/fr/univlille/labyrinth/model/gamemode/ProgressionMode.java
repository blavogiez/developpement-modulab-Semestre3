package fr.univlille.labyrinth.model.gamemode;

import fr.univlille.labyrinth.model.algorithmold.MazeAlgorithmFactory;
import fr.univlille.labyrinth.model.save.Challenge;
import fr.univlille.labyrinth.model.save.Player;
import fr.univlille.labyrinth.model.save.PlayerDatabase;
import fr.univlille.labyrinth.model.save.PlayerProgress;
import fr.univlille.labyrinth.utils.Timer;
import fr.univlille.labyrinth.utils.ProgressionLoader;

public class ProgressionMode extends GameMode {
    private Player player;
    private Challenge selectedChallenge ;
    public static PlayerProgress defaultProgress;
    private Timer chrono;

    public ProgressionMode(Player player, Challenge selectedChallenge) {
        this.player=player;
        this.selectedChallenge=selectedChallenge;
    }

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
        MazeAlgorithmFactory algorithm = chosenChallenge.getAlgorithm() ;
        int width = chosenChallenge.getWidth();
        int height = chosenChallenge.getHeight();
        double wallPercentage = chosenChallenge.getWallPercentage();
        int minPathLength = chosenChallenge.getDistanceBetweenEntryAndExit();
        //wallPercentage à gérer !
        createMaze(algorithm, width, height, minPathLength);
    }

    /**
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }

    public void setChronometre(Timer chrono) {
        this.chrono = chrono;
    }

    @Override
    protected void handleVictory() {
        long completionTime = chrono != null ? chrono.getChrono() : 0;
        player.getProgress().markChallengeCompleted(selectedChallenge, completionTime);
        PlayerDatabase.savePlayer(player);

        notifyVictory();
    }

    public String toString() {
        String info = "Dimensions : " + selectedChallenge.getWidth() + "x" + selectedChallenge.getHeight() ;
        info += ", Pourcentage : " + (int)(selectedChallenge.getWallPercentage() * 100) + "%" ;
        info += ", Distance entrée/sortie : " + selectedChallenge.getDistanceBetweenEntryAndExit();
        return info ;
    }
}
