package fr.univlille.labyrinth.app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;

public class SceneLoader {
    private static final String FXML_BASE_PATH = "/fr/univlille/labyrinth/";

    /** 
     * @param page
     * @return Parent
     * @throws IOException
     */
    public Parent load(String page) throws IOException {
        String fullPath = FXML_BASE_PATH + page;
        URL url = getClass().getResource(fullPath);

        if (url == null) {
            throw new IOException("fichier FXML absent : " + fullPath);
        }

        return FXMLLoader.load(url);
    }
}
