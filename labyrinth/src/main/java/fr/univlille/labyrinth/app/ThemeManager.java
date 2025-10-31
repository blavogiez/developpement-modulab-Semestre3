package fr.univlille.labyrinth.app;

import fr.univlille.labyrinth.App;
import javafx.scene.Scene;

/**
 * ThemeManager gère les fonctionnalités de thème de l'application séparément de la classe principale
 */
public class ThemeManager {
    private static boolean darkMode = true;

    private ThemeManager() {
    }

    public static void setDarkMode(boolean darkMode) {
        ThemeManager.darkMode = darkMode;
    }

    public static boolean isDarkMode() {
        return darkMode;
    }

    public static String getThemeCss() {
        return darkMode ? "dark.css" : "light.css";
    }

    public static void applyTheme(Scene scene) {
        scene.getStylesheets().add(App.class.getResource(getThemeCss()).toExternalForm());
    }

    public static void updateTheme(Scene scene) {
        scene.getStylesheets().set(0, App.class.getResource(getThemeCss()).toExternalForm());
    }
}