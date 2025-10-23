package fr.univlille.labyrinth;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static boolean darkMode;

    public static void setDarkMode(boolean darkMode) {
        Main.darkMode = darkMode;
    }

    public static boolean getDarkMode(){
        return darkMode;
    }

    public static String getTheme(){
        if (darkMode) return "dark.css";
        return "light.css";
    }

    private static Stage primaryStage;

    /** 
     * @param page
     * @throws IOException
     */
    public static void goTo(String page) throws IOException {
        double width = primaryStage.getScene().getWidth();
        double height = primaryStage.getScene().getHeight();
        Parent root = FXMLLoader.load(Main.class.getResource(page));
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.getScene().getStylesheets().add(Main.class.getResource(getTheme()).toExternalForm());

    }

    /** 
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PortailLabyrinth.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Main.class.getResource("light.css").toExternalForm());
        stage.setTitle("Labyrinth");
        stage.setScene(scene);
        stage.show();
    }

    /** 
     * @return Stage
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    /** 
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
