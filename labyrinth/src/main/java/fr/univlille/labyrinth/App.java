package fr.univlille.labyrinth;

import fr.univlille.labyrinth.app.SceneNavigator;
import fr.univlille.labyrinth.app.ThemeManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * App est la classe principale de l'application Labyrinth
 * elle délègue la gestion du thème et de la navigation à des classes spécialisées (single responsibility principle)
 */
public class App extends Application {
    private static Stage primaryStage;

    /**
     * navigue vers une nouvelle page en chargeant le fichier FXML spécifié
     * 
     * @param page Le chemin vers le fichier FXML (a la racine es ressources)
     * @throws IOException Si le fichier FXML n'est pas trouvé ou ne peut pas être chargéd
     */
    public static void goTo(String page) throws IOException {
        SceneNavigator.setPrimaryStage(primaryStage);
        SceneNavigator.goTo(page);
    }

    /** 
     * Méthode de démarrage de l'application JavaFX
     * @param stage La scène principale de l'application
     * @throws IOException Si le fichier FXML de la scène initiale ne peut pas être chargé
     */
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        SceneNavigator.setPrimaryStage(stage);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("HomeMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(App.class.getResource(ThemeManager.getThemeCss()).toExternalForm());
        stage.setTitle("Labyrinth");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    /** 
     * Point d'entrée principal de l'application
     * @param args Arguments de la ligne de commande
     */
    public static void main(String[] args) {
        launch(args);
    }
}
