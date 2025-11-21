package fr.univlille.labyrinth.app;

import java.io.*;

/**
 * Gestionnaire des paramètres de l'application.
 * Cette classe permet de charger, sauvegarder et accéder aux paramètres globaux de l'application.
 *
 * @author Antonin, Angèl, Baptiste, Romain, Victor
 * @version 1.0
 * @since 1.0
 */
public class SettingsManager {
    private static final String SETTINGS_FILE = "res/saves/settings.dat";
    private static Settings settings;

    static {
        File savesDir = new File("res/saves");
        if (!savesDir.exists()) {
            savesDir.mkdirs();
        }
        load();
    }

    /**
     * Charge les paramètres depuis le fichier de sauvegarde.
     * Si le fichier n'existe pas ou une erreur se produit, un objet Settings par défaut est créé.
     */
    public static void load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SETTINGS_FILE))) {
            settings = (Settings) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            settings = new Settings();
        }
    }

    /**
     * Sauvegarde les paramètres actuels dans le fichier de sauvegarde.
     * Affiche une erreur sur la console si la sauvegarde échoue.
     */
    public static void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SETTINGS_FILE))) {
            oos.writeObject(settings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retourne les paramètres chargés.
     *
     * @return l'objet Settings contenant les paramètres actuels
     */
    public static Settings getSettings() {
        return settings;
    }
}
