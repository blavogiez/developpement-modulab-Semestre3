package fr.univlille.labyrinth.model;

import fr.univlille.labyrinth.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.util.Objects;
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

    public Scene push(Scene scene){
        this.stage.setScene(scene);
        return scenes.push(scene);
    }

    public Stage getStage() {
        return stage;
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

    public void goTo(String name){
        try {
            Stage stage = (Stage) HelloApplication.getPrimaryStage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(name)));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e){
            System.out.println(e.getMessage() +" - "+ e.getCause());
        }
    }


}
