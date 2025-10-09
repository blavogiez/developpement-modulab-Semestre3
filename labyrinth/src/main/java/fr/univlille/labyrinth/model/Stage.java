package fr.univlille.labyrinth.model;

import java.util.List;

public class Stage {
    private int number;
    private Challenge[] challenges;

    public Stage(int number) {
        number=number;
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
