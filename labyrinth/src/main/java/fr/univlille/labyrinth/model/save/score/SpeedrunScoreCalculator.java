package fr.univlille.labyrinth.model.save.score;

import fr.univlille.labyrinth.model.save.Challenge;
import java.io.Serializable;

/**
 * Stratégie de calcul de score pour les défis en mode speedrun
 * Ajoute un bonus si le temps de complétion est rapide
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class SpeedrunScoreCalculator implements ScoreCalculator, Serializable {

    private static final long TIME_THRESHOLD = 60000L;
    private static final int SPEED_MULTIPLIER = 2;

    /**
     * Calcule le score avec un bonus si le temps est inférieur au seuil
     * Le temps est calculé avec le score en facteur
     *
     * @param challenge le challenge à évaluer
     * @return int le score calculé avec bonus éventuel
     */
    @Override
    public int calculateScore(Challenge challenge) {
        if (!challenge.isCompleted()) {
            return 0;
        }

        int baseScore = challenge.getWidth() * challenge.getHeight();
        baseScore *= (int)(challenge.getWallPercentage() * 100);

        long completionTime = challenge.getTimeCompleted();
        if (completionTime > 0 && completionTime < TIME_THRESHOLD) {
            double timeFactor = Math.max(0.5, TIME_THRESHOLD / (completionTime + TIME_THRESHOLD / 2.0));
            baseScore *= (int) (timeFactor * SPEED_MULTIPLIER);
        }
        return baseScore;
    }

    /** 
     * @return String
     */
    public String name() {
        return "Speedrun";
    }
}
