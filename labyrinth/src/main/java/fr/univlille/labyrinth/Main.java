package fr.univlille.labyrinth;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Stage primaryStage;

    public static void goTo(String page) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource(page));
        primaryStage.setScene(new Scene(root));
    }

    public static void goTo(Scene scene) throws IOException {
        primaryStage.setScene(scene);
    }

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;



        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AccueilLabyrinth.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Labyrinth");

        stage.setScene(scene);
        stage.show();

    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
