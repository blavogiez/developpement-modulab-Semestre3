package fr.univlille.labyrinth.model;

public class Challenge {
    private String difficulty;
    private int width;
    private int height;
    private int wallPercentage;
    private boolean completed;

    public Challenge(String difficulty) {

    }

    public void validate() {

    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getWallPercentage() {
        return wallPercentage;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {

    }
}
