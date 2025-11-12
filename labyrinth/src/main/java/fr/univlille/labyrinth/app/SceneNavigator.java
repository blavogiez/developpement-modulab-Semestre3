package fr.univlille.labyrinth.app;

import java.io.IOException;
import java.net.URL;

import fr.univlille.labyrinth.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * SceneNavigator gère la navigation entre différentes scènes de l'application
 */
public class SceneNavigator {
    private static Stage primaryStage;
    
    private SceneNavigator() {
    }
    
    /** 
     * @param stage
     */
    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }
    
    /** 
     * @return Stage
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }
    
    /**
     * navigue vers une nouvelle page en chargeant le fichier FXML spécifié
     * 
     * @param page Le chemin vers le fichier FXML (a la racine es ressources)
     * @throws IOException Si le fichier FXML n'est pas trouvé ou ne peut pas être chargéd
     */
    public static void goTo(String page) throws IOException {
        if (primaryStage == null) {
            throw new IllegalStateException("Primary stage absent");
        }

        double width = primaryStage.getScene().getWidth();
        double height = primaryStage.getScene().getHeight();
        page = "/fr/univlille/labyrinth/" + page;
        URL url = SceneNavigator.class.getResource(page);

        if (url == null) {
            throw new IOException("fichier FXML absent : " + page);
        }

        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root, width, height);

        ThemeManager.applyTheme(scene);
        primaryStage.setScene(scene);
    }
}