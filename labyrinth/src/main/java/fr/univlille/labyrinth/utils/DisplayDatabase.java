package fr.univlille.labyrinth.utils;

import fr.univlille.labyrinth.model.*;
import java.util.List;

public class DisplayDatabase {
    public static void main(String[] args) {
        List<Player> players = PlayerDatabase.loadAllPlayers();
        for (Player player : players) {
            System.out.println("Player: " + player.getName());
            System.out.println("Score: " + player.getScore());
            System.out.println("Highest Stage: " + player.getHighestStage());
            World[] stages = player.getProgress().getStageProgress();
            for (World stage : stages) {
                System.out.println("  Stage " + stage.getNumber() + " (completed: " + stage.isCompleted() + ")");
                for (Challenge challenge : stage.getChallenges()) {
                    long time = challenge.getTimeCompleted();
                    long seconds = time / 1000;
                    long minutes = seconds / 60;
                    long secs = seconds % 60;
                    String timeStr = challenge.isCompleted() ? String.format(" time:%02d:%02d", minutes, secs) : "";
                    System.out.println("    Challenge: " + challenge.getDifficulty() +
                        " " + challenge.getWidth() + "x" + challenge.getHeight() +
                        " walls:" + challenge.getWallPercentage() + "% completed:" + challenge.isCompleted() + timeStr);
                }
            }
        }
    }
}
