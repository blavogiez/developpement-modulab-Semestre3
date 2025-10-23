package fr.univlille.labyrinth.model.save;

import java.io.Serializable;

/**
 *  Level représente les différentes étapes.
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class Level implements Serializable {
    private final int number;
    private final Challenge[] challenges;

    public Level(int number) {
        this.number=number;
        this.challenges = new Challenge[3];
    }

    /**
     * @return int
     */
    public int getNumber() {
        return number;
    }

    /**
     * @return boolean qui représente si UN seul challenge à était fait
     */
    public boolean isCompleted() {
        for (Challenge chall: challenges) {
            if (chall.isCompleted()) return true ;
        }
        return false ;
    }

    /**
     * @return Challenge[]
     */
    public Challenge[] getChallenges() {
        return challenges;
    }
}
