package fr.univlille.labyrinth.model.save;

import fr.univlille.labyrinth.model.save.database.PlayerStorage;

import java.util.List;

/**
 * PlayerDatabase gère la persistance des données. Elle permet de load/save une
 * progression par un nom.
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class PlayerDatabase {

    /**
     * Sauvegarde, ou remplace automatiquement une progression dans le dossier
     * associé
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

        PlayerStorage.writeAll(players);
    }

    /**
     * Met à jour le joueur dans la base de données
     *
     * @param player objet du joueur
     * @param player liste des joueurs
     */

    public static void updatePlayer(Player player, List<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getName().equals(player.getName())) {
                players.set(i, player);
                return;
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
     * Renvoie true si la progression du joueur au nom existe dans le dossier
     * externe.
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
     * Surcharge ; renvoie true si le joueur existe dans le dossier externe en
     * vérifiant son nom.
     *
     * @param player nom de la progression recherché.
     */
    public static boolean playerExists(Player player) {
        return playerExists(player.getName());
    }

    /**
 * Retourne la liste de tous les joueurs présents dans la base de données.
 *
 * @return la {@link java.util.List} des {@link Player} présents dans la base de données
 */

    public static List<Player> loadAllPlayers() {
        return PlayerStorage.readAll();
    }

    /**
     * Cette méthode supprime les données dans la base de donnée (pour tests
     * uniquement !)
     */
    public static void clear() {
        PlayerStorage.deleteFile();
    }
}