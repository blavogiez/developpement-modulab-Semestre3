package fr.univlille.labyrinth.controller;


import fr.univlille.labyrinth.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;

import java.io.IOException;

import static fr.univlille.labyrinth.Main.getTheme;

public class ParametreController {

    @FXML
    private ToggleButton toggleButton;

    @FXML
    private Button quit;


    @FXML
    public void initialize(){


    }

    @FXML
    private void toggleButton() throws IOException {
        Main.setDarkMode(!Main.getDarkMode());
        Main.getPrimaryStage().getScene().getStylesheets().set(0,Main.class.getResource(getTheme()).toExternalForm());
    }

    @FXML
    private void goToAccueil() throws IOException {
        Main.goTo("PortailLabyrinth.fxml");
    }




}
