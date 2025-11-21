package fr.univlille.labyrinth.app;

import java.io.Serializable;

/**
 * Représente les paramètres de l'application.
 * Cette classe contient les préférences utilisateur telles que le thème et les paramètres d'animation.
 *
 * @author Antonin, Angèl, Baptiste, Romain, Victor
 * @version 1.0
 * @since 1.0
 */
public class Settings implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean darkMode = true;
    private boolean animationEnabled = false;

    /**
     * Retourne l'état du mode sombre.
     *
     * @return true si le mode sombre est activé, false sinon
     */
    public boolean isDarkMode() {
        return darkMode;
    }

    /**
     * Définit l'état du mode sombre.
     *
     * @param darkMode true pour activer le mode sombre, false pour le désactiver
     */
    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
    }

    /**
     * Retourne l'état des animations.
     *
     * @return true si les animations sont activées, false sinon
     */
    public boolean isAnimationEnabled() {
        return animationEnabled;
    }

    /**
     * Définit l'état des animations.
     *
     * @param animationEnabled true pour activer les animations, false pour les désactiver
     */
    public void setAnimationEnabled(boolean animationEnabled) {
        this.animationEnabled = animationEnabled;
    }
}
