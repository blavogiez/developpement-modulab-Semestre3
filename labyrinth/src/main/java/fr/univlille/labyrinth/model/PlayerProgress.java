package fr.univlille.labyrinth.model;

import java.io.Serializable;
import java.util.List;

public class PlayerProgress implements Serializable {
    private World[] stageProgress;

    public PlayerProgress(World[] stageProgress) {
        this.stageProgress=stageProgress;
    }

    /** 
     * @return PlayerProgress
     */
    // Copying a progress ; useful to use a progress without modifying its reference
    // Typical usecase is defaultProgress handed to a created player
    // if there were multiple players in the same session, defaultProgress would be a mess as they'd all have the same object reference
    public PlayerProgress copy() {
        World[] newStages = new World[stageProgress.length];
        for (int i = 0; i < stageProgress.length; i++) {
            World originalStage = stageProgress[i];
            World newStage = new World(originalStage.getNumber());
            Challenge[] originalChallenges = originalStage.getChallenges();

            for (int j = 0; j < originalChallenges.length; j++) {
                Challenge original = originalChallenges[j];
                newStage.getChallenges()[j] = new Challenge(
                    original.getDifficulty(),
                    original.getWidth(),
                    original.getHeight(),
                    original.getWallPercentage(),
                    original.getDistanceBetweenEntryAndExit()
                );
            }
            newStages[i] = newStage;
        }
        return new PlayerProgress(newStages);
    }

    /** 
     * @param challenge
     * @param time
     */
    public void markChallengeCompleted(Challenge challenge, long time) {
        // debug :
        System.out.println(time + " " + challenge.getTimeCompleted());
        // nouveau temps est uniquement sauvegardé s'il est inférieur à l'ancien temps
        
        // premiere completion ; aucun temps en mémoire donc on l'ignore
        if(challenge.getTimeCompleted()<=0) {
            challenge.setTimeCompleted(time);
        }
        // il y a un temps, donc on le compare
        else if(time<challenge.getTimeCompleted()) {
            challenge.setTimeCompleted(time);
        }

        // dans tous les cas, on le complète
        challenge.setCompleted(true);
    }

    /** 
     * @return int
     */
    public int getHighestStage() {
        int max=0; // the first Stage
        for (World stage: stageProgress) {
            if(stage.isCompleted()) {
                max=stage.getNumber();
            }
        }
        return max;
    }

    /** 
     * @return World[]
     */
    public World[] getStageProgress() {
        return stageProgress;
    }
}
