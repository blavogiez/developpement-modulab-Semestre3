package fr.univlille.labyrinth.model.save.database;

import fr.univlille.labyrinth.model.save.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerStorage {
    private static final String SAVE_FILE = "res/saves/players.dat";

    static {
        File file = new File(SAVE_FILE);
        File directory = file.getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    /** 
     * @param players
     */
    public static void writeAll(List<Player> players){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            oos.writeObject(players);
        } catch (Exception e) {
            System.out.println("Erreur WriteAll"+e.getMessage());
        }
    }

    /** 
     * @return List<Player>
     */
    public static List<Player> readAll() {
        File file = new File(SAVE_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE))) {
            return (List<Player>) ois.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void deleteFile() {
        try {
            File file = new File(SAVE_FILE);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
