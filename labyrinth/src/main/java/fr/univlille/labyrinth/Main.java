package fr.univlille.labyrinth;

import fr.univlille.labyrinth.controller.LabyrinthControler;
import fr.univlille.labyrinth.model.*;
import fr.univlille.labyrinth.model.algorithm.AlgoLaby;
import fr.univlille.labyrinth.view.LabyrinthScene;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    private static AlgoLaby algo;



    public static AlgoLaby getAlgo() {
        if (algo==null) algo = new AlgoLaby();
        return algo;
    }
    public static Main instance;

    public GameMode startGame() {
        //The Main menu's controller need to interact with this. It supposed to make a FreeMode, or a Progression mode, and push a Scene (ex : scenes.push(new FreeMenuScene()) )
        return null;
    }

    private GameMode gameMode;

    public GameMode getGameMode() {
        return gameMode;
    }

    private SceneManager scenes;

    public void displayProgress(Player player) {

    }

    public void savePlayer(Player player) {

    }

    @Override
    public void start(Stage stage) throws Exception {
        this.algo=new AlgoLaby();
        this.scenes = new SceneManager(stage, new Scene(new Pane()));
        this.instance=this;
        this.gameMode=new GameMode() {
            @Override
            public void start() {
                currentMaze=new Maze(11,9,0.3);
                LabyrinthScene labyScene = new LabyrinthScene(currentMaze);
                labyScene.setControler(new LabyrinthControler(this));
                Main.getInstance().getScenes().push(labyScene);
                currentMaze.add(labyScene);

            }

            @Override
            public void playerWin() {
                System.out.println("Le joueur à win !");
                scenes.pop();
            }
        };
        this.gameMode.start();
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        stage.setX(screenBounds.getMinX());
        stage.setY(screenBounds.getMinY());
        stage.setWidth(screenBounds.getWidth());
        stage.setHeight(screenBounds.getHeight());
        stage.setTitle("Labyrinthe");
        stage.show();


    }

    public static Main getInstance() {
        return instance;
    }
    //Main is a Singleton. To call out method, you need to write "Main.getInstance().<the method you want>". It's like a static object but more optimised :D

    public SceneManager getScenes() {
        return scenes;
    }
}
