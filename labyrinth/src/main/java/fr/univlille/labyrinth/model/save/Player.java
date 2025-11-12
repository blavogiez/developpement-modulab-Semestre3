package fr.univlille.labyrinth.model.save;

import fr.univlille.labyrinth.model.gamemode.ProgressionMode;

import java.io.Serializable;

/**
 * Player est le modèle de la situation actuelle du joueur. Il suivra sa progression et l'associera avec un nom et un score dans le but d'être sérialisé.
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class Player implements Serializable , Comparable<Player>{
    private final String name;
    private PlayerProgress progress;

    /**
     * Cette méthode génère une nouvelle instance de progression
     *
     * @param name nom passé par le joueur lors d'une nouvelle partie, associé avec la progression de base.
     */
    public Player(String name) {
        this.name=name;
        progress= ProgressionMode.defaultProgress.copy(); // copy to avoid modifying initial reference (would interfere to other players in the session)
    }

    /**
     * Calcul et renvoie le score en fonction de la progression
     */
    public int calculateScore() {
        int score = 0 ;
        for (Level level : progress.getLevelProgress()) {
            for (Challenge chall : level.getChallenges()) {
                score+=chall.getScoreValue();
            }
        }
        return score ;
    }

    /**
     * @return int
     */
    public int getHighestLevel() {
        return getProgress().getHighestLevel() ;
    }

    /**
     * Détermine si l'étape d'index levelIdx est accessible selon le niveau atteint (demande du sujet)
     * @param levelIdx 
     * @return true
     */
    public boolean isLevelLocked(int levelIdx) {
        //return getHighestLevel() < levelIdx;
        return false ; // debug
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

    /** 
     * @param other
     * @return int
     */
    public int compareTo(Player other) {
        return Integer.compare(other.getScore(),this.calculateScore());
    }

    /** 
     * @return String
     */
    @Override
    public String toString() {
        return name + " avec un score de : " + getScore() ;
    }

    
}
