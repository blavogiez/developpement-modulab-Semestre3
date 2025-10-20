package fr.univlille.labyrinth.model;

import java.io.Serializable;

/**
 * Modèle des différents challenges du mode de progression.
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class Challenge implements Serializable {
    private final String difficulty;
    private final int width;
    private final int height;
    private final int wallPercentage;
    private final int distanceBetweenEntryAndExit;
    private long timeCompleted;
    private boolean completed;

    /**
     * Génère un challenge
     *
     * @param difficulty la difficulté du labyrinthe (indicateur visuel)
     * @param width Largeur du labyrinthe associé au challenge.
     * @param height Hauteur du labyrinthe associé au challenge.
     * @param wallPercentage Pourcentage de mur associé au challenge.
     * @param distanceBetweenEntryAndExit Distance minimale entre l'entrée et la sortie.
     */
    public Challenge(String difficulty, int width, int height, int wallPercentage, int distanceBetweenEntryAndExit) {
        this.difficulty=difficulty;
        this.width=width;
        this.height=height;
        this.wallPercentage=wallPercentage;
        this.distanceBetweenEntryAndExit = distanceBetweenEntryAndExit;
    }
    
    /**
     * Génère un challenge avec la distance minimale par défaut
     *
     * @param difficulty la difficulté du labyrinthe (indicateur visuel)
     * @param width Largeur du labyrinthe associé au challenge.
     * @param height Hauteur du labyrinthe associé au challenge.
     * @param wallPercentage Pourcentage de mur associé au challenge.
     */
    public Challenge(String difficulty, int width, int height, int wallPercentage) {
        this(difficulty, width, height, wallPercentage, 10); // 10 est la valeur par défaut actuelle
    }

    public void validate() {
        this.setCompleted(true);
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getWallPercentage() {
        return wallPercentage;
    }

    public int getDistanceBetweenEntryAndExit() {
        return distanceBetweenEntryAndExit;
    }

    public long getTimeCompleted() {
        return timeCompleted;
    }

    public void setTimeCompleted(long timeCompleted) {
        this.timeCompleted = timeCompleted;
    }

    // calculate the score for the progress
    public int getScoreValue() {
        int score = 0 ;
        if(!completed){
            return score ;
        }
        score+=width*height ;
        score*= wallPercentage ;
        return score ;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed ;
    }
}
