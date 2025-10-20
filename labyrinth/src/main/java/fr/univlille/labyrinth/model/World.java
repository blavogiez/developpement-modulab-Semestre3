package fr.univlille.labyrinth.model;
import java.io.Serializable;
import java.util.List;

public class World implements Serializable {
   //Can you rename this class since it already exist in Javafx? ...
    private int number;
    private Challenge[] challenges;

    public World(int number) {
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
    // stage is completed only if at least one of its challenges is completed (Document expectations)
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
