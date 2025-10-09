package fr.univlille.labyrinth.model;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private int score;
    private PlayerProgress progress;

    public Player(String name, PlayerProgress defaultProgress) {
        this.name=name;
        progress=defaultProgress.copy(); // copy to avoid modifying initial reference (would interfere to other players in the session)
    }

    public int calculateScore() {
        int score = 0 ;
        for (Stage stage : progress.getStageProgress()) {
            for (Challenge chall : stage.getChallenges()) {
                score+=chall.getScoreValue();
            }
        }
        this.score=score;
        return score ;
    }

    public int getHighestStage() {
        return getProgress().getHighestStage() ;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return this.calculateScore();
    }

    public PlayerProgress getProgress() {
        return progress;
    }
}
