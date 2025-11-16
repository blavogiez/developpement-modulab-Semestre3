package fr.univlille.labyrinth.app;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class NavigationContext {
    private final Stage primaryStage;
    private final StackPane rootPane;
    private final BackgroundManager backgroundManager;

    public NavigationContext(Stage primaryStage, StackPane rootPane, BackgroundManager backgroundManager) {
        this.primaryStage = primaryStage;
        this.rootPane = rootPane;
        this.backgroundManager = backgroundManager;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public StackPane getRootPane() {
        return rootPane;
    }

    public BackgroundManager getBackgroundManager() {
        return backgroundManager;
    }
}
