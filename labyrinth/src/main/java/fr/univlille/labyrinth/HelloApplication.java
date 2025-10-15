package fr.univlille.labyrinth;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

import fr.univlille.labyrinth.controller.LabyrinthControler;
import fr.univlille.labyrinth.model.* ;
import fr.univlille.labyrinth.view.LabyrinthScene;

public class HelloApplication extends Application {

    private static Stage primaryStage;

    public static void goTo(String page) throws IOException {
        //Stage stage = primaryStage;
        Parent root = FXMLLoader.load(HelloApplication.class.getResource(page));
        System.out.println(root);
        primaryStage.setScene(new Scene(root));
        System.out.println(primaryStage.getScene());

        //primaryStage.show();
    }

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage=stage;

        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        final double minHeigth = screen.getHeight()/1.5;
        final double minWidth = screen.getWidth()/1.5;

        primaryStage.setOnShown(x ->{
            primaryStage.setMinHeight(minHeigth);
            primaryStage.setMinWidth(minWidth);
        });



        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ModeLibre.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        
        stage.setScene(scene);

        primaryStage.setX(screen.getMinX());
        primaryStage.setY(screen.getMinY());
        primaryStage.setWidth(screen.getWidth());
        primaryStage.setHeight(screen.getHeight());
        primaryStage.setTitle("Labyrinthe");

        primaryStage.show();

        // goTo("AccueilLabyrinth.fxml");
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void goTo(Scene scene){
        primaryStage.setScene(scene);
    }
}
