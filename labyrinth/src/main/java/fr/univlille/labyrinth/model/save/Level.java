package fr.univlille.labyrinth.model.save;

import java.io.Serializable;

public class Level implements Serializable {
    private int number;
    private Challenge[] challenges;

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
     * @return boolean
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
