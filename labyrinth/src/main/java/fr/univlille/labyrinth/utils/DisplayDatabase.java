package fr.univlille.labyrinth.utils;

import fr.univlille.labyrinth.model.*;
import java.util.List;

// Petite classe utilitaire qui affiche l'état des joueurs, utile pour tester !
public class DisplayDatabase {
    /** 
     * @param args
     */
    public static void main(String[] args) {
        List<Player> players = PlayerDatabase.loadAllPlayers();
        for (Player player : players) {
            System.out.println("Player: " + player.getName());
            System.out.println("Score: " + player.getScore());
            System.out.println("Highest Level: " + player.getHighestLevel());
            Level[] levels = player.getProgress().getLevelProgress();
            for (Level level : levels) {
                System.out.println("  Level " + level.getNumber() + " (completed: " + level.isCompleted() + ")");
                for (Challenge challenge : level.getChallenges()) {
                    long time = challenge.getTimeCompleted();
                    long seconds = time / 1000;
                    long minutes = seconds / 60;
                    long secs = seconds % 60;
                    String timeStr = challenge.isCompleted() ? String.format(" time:%02d:%02d", minutes, secs) : "";
                    System.out.println("    Challenge: " + challenge.getDifficulty() +
                        " " + challenge.getWidth() + "x" + challenge.getHeight() +
                        " walls:" + (int)(challenge.getWallPercentage() * 100) + "% completed:" + challenge.isCompleted() + timeStr);
                }
            }
        }
    }
}
