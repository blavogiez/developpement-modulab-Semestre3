package fr.univlille.labyrinth.model;

import javafx.scene.Scene;


import java.util.Stack;

public class SceneManager {
    private javafx.stage.Stage stage;
    private Stack<Scene> scenes;

    public SceneManager(){
        scenes=new Stack<>();
    }

    public SceneManager(javafx.stage.Stage stage){
        scenes=new Stack<>();
        push(stage.getScene());
    }

    public Scene push(Scene scene){
        this.stage.setScene(scene);
        return scenes.push(scene);
    }

    public Scene pop(){
        Scene scene = this.scenes.pop();
        this.stage.setScene(scenes.peek());
        return scene;
    }

    public Scene peek(){
        Scene scene = this.scenes.peek();
        this.stage.setScene(scene);
        return scene;
    }


}
