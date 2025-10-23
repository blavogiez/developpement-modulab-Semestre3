package fr.univlille.labyrinth.model.save;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * PlayerDatabase gère la serialisation du joueur. Elle permet de load/save une progression par un nom.
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
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

    /**
     * Sauvegarde, ou remplace automatiquement une progression dans le dossier associé
     *
     * @param player progression sauvegardée.
     */
    public static void savePlayer(Player player) {
        List<Player> players = loadAllPlayers();

        //search if player already exists ; replace it
        boolean found = isFound(player, players);

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

    /** 
     * @param player
     * @param players
     * @return boolean
     */
    private static boolean isFound(Player player, List<Player> players) {
        boolean found = false;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getName().equals(player.getName())) {
                players.set(i, player);
                found = true;
                break;
            }
        }
        return found;
    }

    /**
     * Charge une partie grâce au nom du joueur
     *
     * @param name nom de la progression chargé.
     */
    public static Player loadPlayer(String name) {
        List<Player> players = loadAllPlayers();
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    /**
     * Renvoie true si la progression existe dans le dossier externe.
     *
     * @param name nom de la progression recherché.
     */
    public static boolean playerExists(String name) {
        List<Player> players = loadAllPlayers();
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /** 
     * @return List<Player>
     */
    // Load all players from file
    public static List<Player> loadAllPlayers() {
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

    // clearing database (only for tests)
    public static void clear() {
        try {
            File file = new File(SAVE_FILE);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}