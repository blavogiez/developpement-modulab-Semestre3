package fr.univlille.labyrinth.app;

import java.io.*;

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

    public static void load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SETTINGS_FILE))) {
            settings = (Settings) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            settings = new Settings();
        }
    }

    public static void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SETTINGS_FILE))) {
            oos.writeObject(settings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** 
     * @return Settings
     */
    public static Settings get() {
        return settings;
    }
}
