package fr.univlille.labyrinth.model;

import java.util.List;

public class PlayerProgress {
    private List<Stage> stageProgress;

    public PlayerProgress copy() {
        return null;
    }

    public void markChallengeCompleted(Challenge challenge) {

    }

    public List<Stage> getStageProgress() {
        return stageProgress;
    }
}
