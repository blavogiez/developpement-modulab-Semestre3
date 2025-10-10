package fr.univlille.labyrinth;

import fr.univlille.labyrinth.model.*;
import fr.univlille.labyrinth.view.LabyrinthScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Stack;

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
                currentMaze=new Maze(11,11,10);
                Main.getInstance().getScenes().push(new LabyrinthScene(currentMaze));


            }
        };
        this.gameMode.start();
        stage.setTitle("Test");
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
