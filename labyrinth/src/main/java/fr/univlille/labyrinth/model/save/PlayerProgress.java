package fr.univlille.labyrinth.model.save;

import java.io.Serializable;

public class PlayerProgress implements Serializable {
    private Level[] levelProgress;

    public PlayerProgress(Level[] levelProgress) {
        this.levelProgress=levelProgress;
    }

    /** 
     * @return PlayerProgress
     */
    // Copying a progress ; useful to use a progress without modifying its reference
    // Typical usecase is defaultProgress handed to a created player
    // if there were multiple players in the same session, defaultProgress would be a mess as they'd all have the same object reference
    public PlayerProgress copy() {
        Level[] newLevels = new Level[levelProgress.length];
        for (int i = 0; i < levelProgress.length; i++) {
            Level originalLevel = levelProgress[i];
            Level newLevel = new Level(originalLevel.getNumber());
            Challenge[] originalChallenges = originalLevel.getChallenges();

            for (int j = 0; j < originalChallenges.length; j++) {
                Challenge original = originalChallenges[j];
                newLevel.getChallenges()[j] = new Challenge(
                    original.getDifficulty(),
                    original.getWidth(),
                    original.getHeight(),
                    original.getWallPercentage(),
                    original.getDistanceBetweenEntryAndExit()
                );
            }
            newLevels[i] = newLevel;
        }
        return new PlayerProgress(newLevels);
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
    public int getHighestLevel() {
        int max=0;
        for (Level level: levelProgress) {
            if(level.isCompleted()) {
                max=level.getNumber();
            }
        }
        return max;
    }

    /**
     * @return Level[]
     */
    public Level[] getLevelProgress() {
        return levelProgress;
    }
}
