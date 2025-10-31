package fr.univlille.labyrinth.model.save.score;

import fr.univlille.labyrinth.model.save.Challenge;

/**
 * Strategy pour le calcul du score d'un challenge
 * Permet de définir différentes stratégies de scoring selon le mode de jeu
 * Améliore la testabilité en permettant l'injection de dépendances dans Challenge
 * Permet le S et le D de S O L I D
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public interface ScoreCalculator {

    /**
     * Calcule le score d'un challenge.
     *
     * @param challenge le challenge dont on calcule le score
     * @return le score calculé, 0 si le challenge n'est pas complété
     */
    int calculateScore(Challenge challenge);

    /**
     * @return String (nom de la stratégie)
     */
    String name() ;
}
