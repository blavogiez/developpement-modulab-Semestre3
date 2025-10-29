package fr.univlille.labyrinth.model.save;

import java.io.Serializable;

import fr.univlille.labyrinth.model.algorithm.MazeAlgorithmFactory;

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
    private final double wallPercentage;
    private final int distanceBetweenEntryAndExit;
    private final MazeAlgorithmFactory algorithm ;
    private final ViewType viewType ;
    private long timeCompleted;
    private boolean completed;

    /**
     * Génère un challenge
     *
     * @param difficulty la difficulté du labyrinthe (indicateur visuel)
     * @param width Largeur du labyrinthe associé au challenge.
     * @param height Hauteur du labyrinthe associé au challenge.
     * @param wallPercentage Pourcentage de mur associé au challenge (entre 0 et 1).
     * @param distanceBetweenEntryAndExit Distance minimale entre l'entrée et la sortie.
     */
    public Challenge(MazeAlgorithmFactory algorithm, ViewType viewType, String difficulty, int width, int height, double wallPercentage, int distanceBetweenEntryAndExit) {
        this.algorithm=algorithm;
        this.viewType=viewType;
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
     * @param wallPercentage Pourcentage de mur associé au challenge (entre 0 et 1).
     */
    public Challenge(MazeAlgorithmFactory algorithm, ViewType viewType, String difficulty, int width, int height, double wallPercentage) {
        this(algorithm, viewType, difficulty, width, height, wallPercentage, 10); // 10 est la valeur par défaut actuelle
    }

    /**
     * valide challenge
     */
    public void validate() {
        this.setCompleted(true);
    }

    /** 
     * @return String
     */
    public String getDifficulty() {
        return difficulty;
    }

    /** 
     * @return int
     */
    public int getWidth() {
        return width;
    }

    /** 
     * @return algorithm
     */
    public MazeAlgorithmFactory getAlgorithm() {
        return algorithm;
    }

    /** 
     * @return int
     */
    public int getHeight() {
        return height;
    }

    /** 
     * @return double
     */
    public double getWallPercentage() {
        return wallPercentage;
    }

    /** 
     * @return int
     */
    public int getDistanceBetweenEntryAndExit() {
        return distanceBetweenEntryAndExit;
    }


    /** 
     * @return int
     */
    public ViewType getViewType() {
        return this.viewType;
    }

    /** 
     * @return long
     */
    public long getTimeCompleted() {
        return timeCompleted;
    }

    /**
     *
     * @param timeCompleted set time completed
     */
    public void setTimeCompleted(long timeCompleted) {
        this.timeCompleted = timeCompleted;
    }

    /** 
     * @return int
     */
    // calculate the score for the progress
    public int getScoreValue() {
        int score = 0 ;
        if(!completed){
            return score ;
        }
        score+=width*height ;
        score*= (int)(wallPercentage * 100); // Convertir le pourcentage en format entier pour le calcul
        return score ;
    }

    /** 
     * @return boolean
     */
    public boolean isCompleted() {
        return completed;
    }

    /** 
     * @param completed set challenge
     */
    public void setCompleted(boolean completed) {
        this.completed = completed ;
    }

    /*
     * @return String
     */
    public String toString() {
        StringBuilder text = new StringBuilder();
        text.append("Type de labyrinthe : ").append(this.getAlgorithm().name()).append("\n");
        text.append("Type de vue : ").append(this.getViewType().name()).append("\n");
        text.append("Difficulté : ").append(this.getDifficulty()).append("\n");
        text.append("Dimensions : ").append(this.getWidth()).append("x").append(this.getHeight()).append("\n");
        text.append("Murs : ").append(String.format("%.0f%%", this.getWallPercentage() * 100)).append("\n");
        text.append("Distance : ").append(this.getDistanceBetweenEntryAndExit());

        if (this.isCompleted() && this.getTimeCompleted() > 0) {
            double timeInSeconds = this.getTimeCompleted() / 1000.0;
            text.append("\nTemps : ").append(String.format("%.1fs", timeInSeconds));
        }
        return text.toString() ;
    }
}
