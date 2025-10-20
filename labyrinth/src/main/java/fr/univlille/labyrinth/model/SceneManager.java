package fr.univlille.labyrinth.model;

import javafx.scene.Scene;
import javafx.stage.Stage;


import java.util.Stack;

public class SceneManager {
    private javafx.stage.Stage stage;
    private Stack<Scene> scenes;

    public SceneManager(){
        scenes=new Stack<>();
    }

    public SceneManager(javafx.stage.Stage stage, Scene scene){
        this.stage=stage;
        scenes=new Stack<>();
        push(scene);
    }

    /** 
     * @param scene
     * @return Scene
     */
    public Scene push(Scene scene){
        this.stage.setScene(scene);
        return scenes.push(scene);
    }

    /** 
     * @return Stage
     */
    public Stage getStage() {
        return stage;
    }

    /** 
     * @return Scene
     */
    public Scene pop(){
        Scene scene = this.scenes.pop();
        this.stage.setScene(scenes.peek());
        return scene;
    }

    /** 
     * @return Scene
     */
    public Scene peek(){
        Scene scene = this.scenes.peek();
        this.stage.setScene(scene);
        return scene;
    }


}
