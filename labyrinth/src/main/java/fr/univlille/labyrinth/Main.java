package fr.univlille.labyrinth;

import fr.univlille.labyrinth.controller.LabyrinthControler;
import fr.univlille.labyrinth.model.*;
import fr.univlille.labyrinth.model.algorithm.AlgoLabyOld;
import fr.univlille.labyrinth.view.LabyrinthScene;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
    private GameMode gameMode;

    public GameMode getGameMode() {
        return gameMode;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        final double minHeigth = screen.getHeight()/1.5;
        final double minWidth = screen.getWidth()/1.5;

        stage.setOnShown(x ->{
            stage.setMinHeight(minHeigth);
            stage.setMinWidth(minWidth);
        });

        stage.setX(screen.getMinX());
        stage.setY(screen.getMinY());
        stage.setWidth(screen.getWidth());
        stage.setHeight(screen.getHeight());
        stage.setTitle("Labyrinthe");
        stage.show();

        this.gameMode=new GameMode() {
            @Override
            public void start() {
                gameMode.setCurrentMaze(new Maze(11,21,0.5));
                LabyrinthScene labyScene = new LabyrinthScene(gameMode.getCurrentMaze());
                labyScene.setControler(new LabyrinthControler(this));
                gameMode.getCurrentMaze().add(labyScene);
            }

            @Override
            public void playerWin() {
                System.out.println("Le joueur à win !");
                // toto
            }
        };
        this.gameMode.start();
    }
}
