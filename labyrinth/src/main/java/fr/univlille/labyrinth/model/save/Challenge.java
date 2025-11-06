package fr.univlille.labyrinth.model.save;

import java.io.Serializable;

import fr.univlille.labyrinth.model.algorithm.MazeAlgorithm;
import fr.univlille.labyrinth.model.save.score.ScoreCalculator;
import fr.univlille.labyrinth.model.save.score.StandardScoreCalculator;

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
    private final MazeAlgorithm algorithm ;
    private final ViewType viewType ;
    private long timeCompleted;
    private boolean completed;
    private ScoreCalculator scoreCalculator;

    /**
     * Génère un challenge avec injection de la stratégie de calcul de score
     * Permet l'inversion de dépendance pour faciliter les tests et l'extensibilité
     *
     * @param algorithm Algorithme de génération du labyrinthe
     * @param viewType Type de vue du challenge
     * @param difficulty la difficulté du labyrinthe (indicateur visuel)
     * @param width Largeur du labyrinthe associé au challenge.
     * @param height Hauteur du labyrinthe associé au challenge.
     * @param wallPercentage Pourcentage de mur associé au challenge (entre 0 et 1).
     * @param distanceBetweenEntryAndExit Distance minimale entre l'entrée et la sortie.
     * @param scoreCalculator Stratégie de calcul de score à utiliser
     */
    public Challenge(MazeAlgorithm algorithm, ViewType viewType, String difficulty, int width, int height, double wallPercentage, int distanceBetweenEntryAndExit, ScoreCalculator scoreCalculator) {
        this.algorithm=algorithm;
        this.viewType=viewType;
        this.difficulty=difficulty;
        this.width=width;
        this.height=height;
        this.wallPercentage=wallPercentage;
        this.distanceBetweenEntryAndExit = distanceBetweenEntryAndExit;
        this.scoreCalculator = scoreCalculator;
    }

    /**
     * Génère un challenge avec la distance minimale par défaut
     */
    public Challenge(MazeAlgorithm algorithm, ViewType viewType, String difficulty, int width, int height, double wallPercentage, ScoreCalculator scoreCalculator) {
        this(algorithm, viewType, difficulty, width, height, wallPercentage, 10, scoreCalculator);
    }

    /**
     * Génère un challenge avec la stratégie de scoring par défaut
     */
    public Challenge(MazeAlgorithm algorithm, ViewType viewType, String difficulty, int width, int height, double wallPercentage, int distanceBetweenEntryAndExit) {
        this(algorithm, viewType, difficulty, width, height, wallPercentage, distanceBetweenEntryAndExit, new StandardScoreCalculator());
    }

    /**
     * Génère un challenge avec la stratégie de scoring et la distance minimale par défaut
     */
    public Challenge(MazeAlgorithm algorithm, ViewType viewType, String difficulty, int width, int height, double wallPercentage) {
        this(algorithm, viewType, difficulty, width, height, wallPercentage, 10);
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
    public MazeAlgorithm getAlgorithm() {
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
     * Récupère la stratégie de calcul de score actuelle.
     *
     * @return ScoreCalculator la stratégie utilisée
     */
    public ScoreCalculator getScoreCalculator() {
        return scoreCalculator;
    }

    /**
     * Définit la stratégie de calcul de score
     * Permet de changer dynamiquement le mode de calcul (standard, speedrun, expert...)
     *
     * @param scoreCalculator la stratégie à utiliser
     */
    public void setScoreCalculator(ScoreCalculator scoreCalculator) {
        this.scoreCalculator = scoreCalculator;
    }

    /**
     * @return int
     */
    public int getScoreValue() {
        return scoreCalculator.calculateScore(this);
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

    /**
     * Formate un challenge en chaîne de caractères lisible
     *
     * @param challenge le challenge à formater
     * @return String représentation textuelle du challenge
     */
    public String toString() {
        StringBuilder text = new StringBuilder();
        text.append("Type de labyrinthe : ").append(this.getAlgorithm()).append("\n");
        text.append("Mode de score : ").append(this.getScoreCalculator().name()).append("\n");
        text.append("Type de vue : ").append(this.getViewType().name()).append("\n");
        text.append("Difficulté : ").append(this.getDifficulty()).append("\n");
        text.append("Dimensions : ").append(this.getWidth()).append("x").append(this.getHeight()).append("\n");
        text.append("Murs : ").append(String.format("%.0f%%", this.getWallPercentage() * 100)).append("\n");
        text.append("Distance : ").append(this.getDistanceBetweenEntryAndExit());

        if (this.isCompleted() && this.getTimeCompleted() > 0) {
            double timeInSeconds = this.getTimeCompleted() / 1000.0;
            text.append("\nTemps : ").append(String.format("%.1fs", timeInSeconds));
        }
        return text.toString();
    }
}
