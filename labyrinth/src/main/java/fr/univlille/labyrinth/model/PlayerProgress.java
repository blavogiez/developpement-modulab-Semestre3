package fr.univlille.labyrinth.model;

public class PlayerProgress {
    private World[] worldProgresses;

    public PlayerProgress(World[] worldProgresses) {
        this.worldProgresses = worldProgresses;
    }
    public PlayerProgress copy() {
        return null;
    }

    public void markChallengeCompleted(Challenge challenge) {
        challenge.setCompleted(true);
    }

    public int getHighestStage() {
        int max=0; // the first Stage
        for (World world : worldProgresses) {
            if(world.isCompleted()) {
                max= world.getNumber();
            }
        }
        return max;
    }

    public World[] getStageProgress() {
        return worldProgresses;
    }
}
