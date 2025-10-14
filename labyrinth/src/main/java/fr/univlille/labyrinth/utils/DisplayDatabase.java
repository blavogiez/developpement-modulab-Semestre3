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
            Stage[] stages = player.getProgress().getStageProgress();
            for (Stage stage : stages) {
                System.out.println("  Stage " + stage.getNumber() + " (completed: " + stage.isCompleted() + ")");
                for (Challenge challenge : stage.getChallenges()) {
                    System.out.println("    Challenge: " + challenge.getDifficulty() +
                        " " + challenge.getWidth() + "x" + challenge.getHeight() +
                        " walls:" + challenge.getWallPercentage() + "% completed:" + challenge.isCompleted());
                }
            }
        }
    }
}
