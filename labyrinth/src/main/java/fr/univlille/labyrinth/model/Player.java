package fr.univlille.labyrinth.model;

import java.io.Serializable;

/**
 * Player est le modèle de la situation actuelle du joueur. Il suivra sa progression et l'associera avec un nom et un score dans le but d'être sérialisé.
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class Player implements Serializable {
    private final String name;
    private PlayerProgress progress;

    /**
     * Cette méthode génère une nouvelle instance de progression
     *
     * @param name nom passé par le joueur lors d'une nouvelle partie, associé avec la progression de base.
     */
    public Player(String name) {
        this.name=name;
        progress=ProgressionMode.defaultProgress.copy(); // copy to avoid modifying initial reference (would interfere to other players in the session)
    }

    /**
     * Calcul et renvoie le score en fonction de la progression
     */
    public int calculateScore() {
        int score = 0 ;
        for (World world : progress.getStageProgress()) {
            for (Challenge chall : world.getChallenges()) {
                score+=chall.getScoreValue();
            }
        }
        return score ;
    }

    /** 
     * @return int
     */
    public int getHighestStage() {
        return getProgress().getHighestStage() ;
    }

    /** 
     * @return String
     */
    public String getName() {
        return name;
    }

    /** 
     * @return int
     */
    public int getScore() {
        return this.calculateScore();
    }

    /** 
     * @return PlayerProgress
     */
    public PlayerProgress getProgress() {
        return progress;
    }
}
