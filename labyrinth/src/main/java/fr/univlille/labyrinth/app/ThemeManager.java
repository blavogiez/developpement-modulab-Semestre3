package fr.univlille.labyrinth.app;

import fr.univlille.labyrinth.App;
import javafx.scene.Scene;

/**
 * ThemeManager gère les fonctionnalités de thème de l'application séparément de la classe principale
 */
public class ThemeManager {

    private ThemeManager() {
    }

    /**
     * @param darkMode
     */
    public static void setDarkMode(boolean darkMode) {
        SettingsManager.get().setDarkMode(darkMode);
    }

    /**
     * @return boolean
     */
    public static boolean isDarkMode() {
        return SettingsManager.get().isDarkMode();
    }

    /**
     * @return String
     */
    public static String getThemeCss() {
        return isDarkMode() ? "dark.css" : "light.css";
    }

    /** 
     * @param scene
     */
    public static void applyTheme(Scene scene) {
        scene.getStylesheets().add(App.class.getResource(getThemeCss()).toExternalForm());
    }

    /** 
     * @param scene
     */
    public static void updateTheme(Scene scene) {
        scene.getStylesheets().set(0, App.class.getResource(getThemeCss()).toExternalForm());
    }
}