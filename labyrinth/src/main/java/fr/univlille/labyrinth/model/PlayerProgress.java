package fr.univlille.labyrinth.model;

import java.util.List;

public class PlayerProgress {
    private Stage[] stageProgress;

    public PlayerProgress copy() {
        return null;
    }

    public void markChallengeCompleted(Challenge challenge) {
        challenge.setCompleted(true);
    }

    public int getHighestStage() {
        int max=0; // the first Stage
        for (Stage stage: stageProgress) {
            if(stage.isCompleted()) {
                max=stage.getNumber();
            }
        }
        return max;
    }

    public Stage[] getStageProgress() {
        return stageProgress;
    }
}
