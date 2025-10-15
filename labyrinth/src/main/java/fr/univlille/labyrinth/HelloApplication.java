package fr.univlille.labyrinth;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;

        Scene scene = new Scene(FXMLLoader.load(HelloApplication.class.getResource("AccueilLabyrinth.fxml")));
        stage.setTitle("Hello !");
        stage.setScene(scene);
        stage.show();

    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }
}
