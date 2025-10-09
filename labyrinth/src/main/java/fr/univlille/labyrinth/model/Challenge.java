package fr.univlille.labyrinth.model;

public class Challenge {
    private String difficulty;
    private int width;
    private int height;
    private int wallPercentage;
    private boolean completed;

    public Challenge(String difficulty, int width, int height, int wallPercentage) {
        this.difficulty=difficulty;
        this.width=width;
        this.height=height;
        this.wallPercentage=wallPercentage;
        // load width and height data;
    }

    public void validate() {
        this.setCompleted(true);
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
        this.completed = completed ;
    }
}
