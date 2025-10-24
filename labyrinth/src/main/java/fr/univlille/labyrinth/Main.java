package fr.univlille.labyrinth;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    private static boolean darkMode = true;

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
        page = "/fr/univlille/labyrinth/"+page;
        URL url = Main.class.getResource(page);
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root, width, height);
        //appliquer le theme avant de la mettre (évite flash)
        scene.getStylesheets().add(Main.class.getResource(getTheme()).toExternalForm());
        primaryStage.setScene(scene);
        
    }

    /** 
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HomeMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Main.class.getResource("dark.css").toExternalForm());
        stage.setTitle("Labyrinth");
        stage.setMaximized(true);
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
