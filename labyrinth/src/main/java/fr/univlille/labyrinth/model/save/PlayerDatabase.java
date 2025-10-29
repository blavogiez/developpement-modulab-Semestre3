package fr.univlille.labyrinth.model.save;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * PlayerDatabase gère la persistance des données. Elle permet de load/save une progression par un nom.
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class PlayerDatabase {
    private static final String SAVE_FILE = "res/saves/players.dat";

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
        boolean found = playerExists(player);
        if (!found) {
            players.add(player);
        } else {
            updatePlayer(player, players);
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            oos.writeObject(players);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    /**
     * Met à jour le joueur dans la base de données
     *
     * @param player objet du joueur
     * @param player liste des joueurs
     */

    public static void updatePlayer(Player player, List<Player> players) {
        boolean found = false;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getName().equals(player.getName())) {
                players.set(i, player);
                return ;
            }
        }

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
     * Renvoie true si la progression du joueur au nom existe dans le dossier externe.
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
     * Surcharge ; renvoie true si le joueur existe dans le dossier externe en vérifiant son nom.
     *
     * @param name nom de la progression recherché.
     */
    public static boolean playerExists(Player player) {
        return playerExists(player.getName());
    }

    /**
     * Cette méthode renvoie la liste de tous les joueurs présents dans la base de donnée
     * @return List<Player> la liste des joueurs de la base des données
     */
    public static List<Player> loadAllPlayers() {
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

    /**
     * Cette méthode supprime les données dans la base de donnée (pour tests uniquement !)
     */
    public static void clear() {
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