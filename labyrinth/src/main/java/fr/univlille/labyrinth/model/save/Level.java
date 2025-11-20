package fr.univlille.labyrinth.model.save;

import java.io.Serializable;

/**
 * Représente un niveau du jeu avec ses challenges.
 * Chaque niveau possède un numéro et un ensemble de challenges.
 * 
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class Level implements Serializable {

    private final int number;
    private final Challenge[] challenges;

    /**
     * Crée un nouveau niveau avec le numéro donné et initialise
     * un tableau de 3 challenges.
     *
     * @param number le numéro du niveau
     */
    public Level(int number) {
        this.number = number;
        this.challenges = new Challenge[3];
    }

    /**
     * Retourne le numéro du niveau.
     *
     * @return le numéro du niveau
     */
    public int getNumber() {
        return number;
    }

    /**
     * Indique si au moins un challenge du niveau a été complété.
     *
     * @return true si au moins un challenge est complété, false sinon
     */
    public boolean isCompleted() {
        for (Challenge chall : challenges) {
            if (chall != null && chall.isCompleted()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retourne le tableau des challenges de ce niveau.
     *
     * @return le tableau de challenges
     */
    public Challenge[] getChallenges() {
        return challenges;
    }
}
