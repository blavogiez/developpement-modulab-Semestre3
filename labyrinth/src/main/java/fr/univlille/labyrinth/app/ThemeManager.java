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
     * @return boolean
     */
    public static boolean isDarkMode() {
        return SettingsManager.getSettings().isDarkMode();
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
        String oldTheme = getThemeCss();
        scene.getStylesheets().removeIf(s -> s.contains(oldTheme));
        scene.getStylesheets().add(App.class.getResource(getThemeCss()).toExternalForm());
    }

    public static void toggleTheme(){
        if(!isDarkMode()){
            SettingsManager.getSettings().setDarkMode(true);
        }else{
            SettingsManager.getSettings().setDarkMode(false);
        }
    }
}