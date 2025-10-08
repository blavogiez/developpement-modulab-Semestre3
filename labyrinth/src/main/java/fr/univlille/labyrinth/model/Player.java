package fr.univlille.labyrinth.model;

public class Player {
    private String name;
    private int score;
    private PlayerProgress progress;

    public Player(String name, PlayerProgress defaultProgress) {

    }

    public int calculateScore() {
        return 0;
    }

    public int getHighestStage() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public PlayerProgress getProgress() {
        return progress;
    }
}
