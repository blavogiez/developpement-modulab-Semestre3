package fr.univlille.labyrinth.app;

import java.io.Serializable;

public class Settings implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean darkMode = true;
    private boolean animationEnabled = false;

    public boolean isDarkMode() {
        return darkMode;
    }

    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
    }

    public boolean isAnimationEnabled() {
        return animationEnabled;
    }

    public void setAnimationEnabled(boolean animationEnabled) {
        this.animationEnabled = animationEnabled;
    }
}
