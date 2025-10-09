package fr.univlille.labyrinth.model;

public class Player {
    private String name;
    private int score;
    private PlayerProgress progress;

    public Player(String name, PlayerProgress defaultProgress) {
        this.name=name;
        progress=defaultProgress.copy(); // copy to avoid modifying initial reference (would interfere to other players in the session)
    }

    public int calculateScore() {
        return 0;
    }

    public int getHighestStage() {
        return getProgress().getHighestStage() ;
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
