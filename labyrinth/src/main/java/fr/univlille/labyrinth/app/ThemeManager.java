package fr.univlille.labyrinth.app;

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
}