package fr.univlille.labyrinth.model.save.database;

import fr.univlille.labyrinth.model.save.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestionnaire de stockage des données des joueurs.
 * Cette classe gère la lecture et l'écriture des données des joueurs sur le disque.
 *
 * @author Antonin, Angèl, Baptiste, Romain, Victor
 * @version 1.0
 * @since 1.0
 */
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
     * Écrit la liste complète des joueurs dans le fichier de sauvegarde.
     *
     * @param players la liste des joueurs à sauvegarder
     */
    public static void writeAll(List<Player> players){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            oos.writeObject(players);
        } catch (Exception e) {
            System.out.println("Erreur WriteAll"+e.getMessage());
        }
    }

    /**
     * Lit et retourne la liste complète des joueurs depuis le fichier de sauvegarde.
     *
     * @return la liste des joueurs sauvegardés, ou une liste vide si le fichier n'existe pas
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

    /**
     * Supprime le fichier de sauvegarde des joueurs.
     * Cette méthode est principalement utilisée pour les tests.
     */
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
