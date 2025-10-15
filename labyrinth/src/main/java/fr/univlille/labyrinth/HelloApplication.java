package fr.univlille.labyrinth;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private static Stage primaryStage;

    public static void goTo(String page) throws IOException {
        Stage stage = primaryStage;
        Parent root = FXMLLoader.load(HelloApplication.class.getResource(page));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AccueilLabyrinth.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void goTo(Scene scene){
        primaryStage.setScene(scene);
    }
}
