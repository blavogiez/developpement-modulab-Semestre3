package fr.univlille.labyrinth.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Serialization static handler for the player (Object writing / reading)
public class PlayerDatabase {
    private static final String SAVE_FILE = "res/saves/players.dat";

    // Create saves directory if it doesn't exist
    static {
        File file = new File(SAVE_FILE);
        File directory = file.getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public static void savePlayer(Player player) {
        List<Player> players = loadAllPlayers();

        //search if player already exists ; replace it
        boolean found = false;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getName().equals(player.getName())) {
                players.set(i, player);
                found = true;
                break;
            }
        }

        // Otherwise add it
        if (!found) {
            players.add(player);
        }

        //save the complete list
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            oos.writeObject(players);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Player loadPlayer(String name) {
        List<Player> players = loadAllPlayers();
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    public static boolean playerExists(String name) {
        List<Player> players = loadAllPlayers();
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // Load all players from file
    private static List<Player> loadAllPlayers() {
        File file = new File(SAVE_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE))) {
            return (List<Player>) ois.readObject();
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }
}