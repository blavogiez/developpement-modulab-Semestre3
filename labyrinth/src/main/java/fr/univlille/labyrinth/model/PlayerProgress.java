package fr.univlille.labyrinth.model;

import java.util.List;

public class PlayerProgress {
    private Stage[] stageProgress;

    public PlayerProgress(Stage[] stageProgress) {
        this.stageProgress=stageProgress;
    }

    // Copying a progress ; useful to use a progress without modifying its reference
    // Typical usecase is defaultProgress handed to a created player
    // if there were multiple players in the same session, defaultProgress would be a mess as they'd all have the same object reference
    public PlayerProgress copy() {
        Stage[] newStages = new Stage[stageProgress.length];
        for (int i = 0; i < stageProgress.length; i++) {
            Stage originalStage = stageProgress[i];
            Stage newStage = new Stage(originalStage.getNumber());
            Challenge[] originalChallenges = originalStage.getChallenges();

            for (int j = 0; j < originalChallenges.length; j++) {
                Challenge original = originalChallenges[j];
                newStage.getChallenges()[j] = new Challenge(
                    original.getDifficulty(),
                    original.getWidth(),
                    original.getHeight(),
                    original.getWallPercentage()
                );
            }
            newStages[i] = newStage;
        }
        return new PlayerProgress(newStages);
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
