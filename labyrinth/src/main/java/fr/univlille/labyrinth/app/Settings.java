package fr.univlille.labyrinth.app;

import java.io.Serializable;

/*
 * fichier de paramètres 
 */
public class Settings implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean darkMode = true;
    private boolean animationEnabled = false;

    /** 
     * @return boolean
     */
    public boolean isDarkMode() {
        return darkMode;
    }

    /** 
     * @param darkMode
     */
    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
    }

    /** 
     * @return boolean
     */
    public boolean isAnimationEnabled() {
        return animationEnabled;
    }

    /** 
     * @param animationEnabled
     */
    public void setAnimationEnabled(boolean animationEnabled) {
        this.animationEnabled = animationEnabled;
    }
}
