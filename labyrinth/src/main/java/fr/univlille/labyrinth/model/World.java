package fr.univlille.labyrinth.model;

public class World {
    //Can you rename this class since it already exist in Javafx? ...
    private int number;
    private Challenge[] challenges;

    public World(int number) {
        this.number=number;
        this.challenges = new Challenge[3];
    }

    public int getNumber() {
        return number;
    }

    // stage is completed only if at least one of its challenges is completed (Document expectations)
    public boolean isCompleted() {
        for (Challenge chall: challenges) {
            if (chall.isCompleted()) return true ;
        }
        return false ;
    }

    public Challenge[] getChallenges() {
        return challenges;
    }
}
