package fr.univlille.labyrinth.controller.freemode;

import fr.univlille.labyrinth.model.maze.entities.EntityType;
import fr.univlille.labyrinth.model.maze.entities.movebehaviors.MoveBehavior;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.awt.*;

public class FreeModeEntityConfigurationController{
    @FXML
    javafx.scene.control.TextField textField;
    @FXML
    Button buttonAdd;
    @FXML
    ComboBox<EntityType> comboBox1;
    @FXML
    ComboBox<MoveBehavior> comboBox2;
}
