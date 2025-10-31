package fr.univlille.labyrinth.model.save.score;

import fr.univlille.labyrinth.model.save.Challenge;
import java.io.Serializable;

/**
 * Stratégie de calcul de score standard pour les défis
 * Formule : largeur × hauteur × pourcentage_murs
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class StandardScoreCalculator implements ScoreCalculator, Serializable {

    /**
     * Calcule le score basé sur les dimensions et la difficulté du labyrinthe
     * @param challenge le challenge à évaluer
     * @return int le score calculé
     */
    @Override
    public int calculateScore(Challenge challenge) {
        if (!challenge.isCompleted()) {
            return 0;
        }

        int score = challenge.getWidth() * challenge.getHeight();
        score *= (int)(challenge.getWallPercentage() * 100);

        return score;
    }

    public String name() {
        return "Standard" ;
    }
}
