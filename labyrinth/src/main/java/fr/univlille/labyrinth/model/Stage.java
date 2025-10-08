package fr.univlille.labyrinth.model;

import java.util.List;

public class Stage {
    private int number;
    private List<Challenge> challenges;

    public Stage(int number) {

    }

    public int getNumber() {
        return number;
    }

    public List<Challenge> getChallenges() {
        return challenges;
    }
}
