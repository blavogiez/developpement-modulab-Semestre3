package fr.univlille.labyrinth;

import javafx.scene.image.Image;
import fr.univlille.labyrinth.app.BackgroundManager;
import fr.univlille.labyrinth.app.NavigationContext;
import fr.univlille.labyrinth.app.SceneNavigator;
import fr.univlille.labyrinth.app.ThemeManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * App est la classe principale de l'application Labyrinth
 * elle délègue la gestion du thème et de la navigation à des classes spécialisées (single responsibility principle)
 */
public class App extends Application {
    /** 
     * @param page
     * @throws IOException
     */
    public static void goTo(String page) throws IOException {
        SceneNavigator.goTo(page);
    }

    /** 
     * Méthode de démarrage de l'application JavaFX
     * @param stage La scène principale de l'application
     * @throws IOException Si le fichier FXML de la scène initiale ne peut pas être chargé
     */
    @Override
    public void start(Stage stage) throws IOException {
        BackgroundManager backgroundManager = new BackgroundManager("/fr/univlille/labyrinth/images/background.mp4", stage);
        StackPane rootPane = new StackPane();

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("HomeMenu.fxml"));
        rootPane.getChildren().addAll(backgroundManager.getMediaView(), fxmlLoader.load());

        NavigationContext context = new NavigationContext(stage, rootPane, backgroundManager);
        SceneNavigator.setContext(context);

        Scene scene = new Scene(rootPane);
        ThemeManager.applyTheme(scene);
        stage.setTitle("Modulab");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.getIcons().add(
            new Image(App.class.getResourceAsStream("/fr/univlille/labyrinth/images/icon.png"))
        );

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
