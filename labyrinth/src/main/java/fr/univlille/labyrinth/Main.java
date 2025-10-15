package fr.univlille.labyrinth;

import fr.univlille.labyrinth.controller.LabyrinthControler;
import fr.univlille.labyrinth.model.*;
import fr.univlille.labyrinth.model.algorithm.AlgoLabyOld;
import fr.univlille.labyrinth.view.LabyrinthScene;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    private static AlgoLabyOld algo;




    public static AlgoLabyOld getAlgo() {
        if (algo==null) algo = new AlgoLabyOld();
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





        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        final double minHeigth = screen.getHeight()/1.5;
        final double minWidth = screen.getWidth()/1.5;

        stage.setOnShown(x ->{
            stage.setMinHeight(minHeigth);
            stage.setMinWidth(minWidth);
        });

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AccueilLabyrinth.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello !");


        this.scenes = new SceneManager(stage, scene);

        stage.setX(screen.getMinX());
        stage.setY(screen.getMinY());
        stage.setWidth(screen.getWidth());
        stage.setHeight(screen.getHeight());
        stage.setTitle("Labyrinthe");
        stage.show();

        this.algo=new AlgoLabyOld();
//        this.scenes = new SceneManager(stage, new Scene(new Pane()));
        this.instance=this;
        this.gameMode=new GameMode() {
            @Override
            public void start() {
                gameMode.setCurrentMaze(new Maze(11,11,0.5));
                LabyrinthScene labyScene = new LabyrinthScene(gameMode.getCurrentMaze());
                labyScene.setControler(new LabyrinthControler(this));
                Main.getInstance().getScenes().push(labyScene);
                gameMode.getCurrentMaze().add(labyScene);

            }

            @Override
            public void playerWin() {
                System.out.println("Le joueur à win !");
                scenes.pop();
            }
        };







    }


    public static Main getInstance() {
        return instance;
    }
    //Main is a Singleton. To call out method, you need to write "Main.getInstance().<the method you want>". It's like a static object but more optimised :D

    public SceneManager getScenes() {
        return scenes;
    }

    public void goTo(String name){
        scenes.goTo(name);
    }
}
