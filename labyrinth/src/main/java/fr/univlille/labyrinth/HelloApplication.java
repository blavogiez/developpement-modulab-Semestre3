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

    private GameMode gameMode;

    public GameMode getGameMode() {
        return gameMode;
    }
    private static Stage primaryStage;

    public static void goTo(String page) throws IOException {
        Stage stage = primaryStage;
        Parent root = FXMLLoader.load(HelloApplication.class.getResource(page));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        final double minHeigth = screen.getHeight()/1.5;
        final double minWidth = screen.getWidth()/1.5;

        stage.setOnShown(x ->{
            stage.setMinHeight(minHeigth);
            stage.setMinWidth(minWidth);
        });

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AccueilLabyrinth.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        
        stage.setScene(scene);

        stage.setX(screen.getMinX());
        stage.setY(screen.getMinY());
        stage.setWidth(screen.getWidth());
        stage.setHeight(screen.getHeight());
        stage.setTitle("Labyrinthe");
        stage.show();

        primaryStage = stage;

        //À METTRE DANS UN BOUTON
        this.gameMode=new FreeMode();
        gameMode.setCurrentMaze(null);
        goTo(new LabyrinthScene(null));
        null.setControler(new LabyrinthControler(this));


        //
        
        stage.show();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void goTo(Scene scene){
        primaryStage.setScene(scene);
    }

    @Override
    public void start(Stage stage) throws Exception {
        

        LabyrinthScene labyScene = new LabyrinthScene(gameMode.getCurrentMaze());
        labyScene.setControler(new LabyrinthControler(this));
        gameMode.getCurrentMaze().add(labyScene);
        this.gameMode.start();
    }
}
