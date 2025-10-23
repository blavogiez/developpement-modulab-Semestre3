package fr.univlille.labyrinth.view;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class Redimension {

    public static void redimensionnerVboxControles(VBox parent) {
        double width = parent.getWidth();
        double height = parent.getHeight();

        ObservableList<Node> enfants = parent.getChildren();
        for (Node enfant : enfants) {
            if (enfant instanceof Button bouton) {
                bouton.setPrefWidth(width * 0.4);
                bouton.setPrefHeight(height * 0.15);
                bouton.setFont(new Font(height * 0.05));
                VBox.setMargin(bouton, new Insets(0, 0, height * 0.10, 0));

            } else if (enfant instanceof Label label) {
                label.setFont(new Font(height * 0.05));
                VBox.setMargin(label, new Insets(0, 0, height * 0.05, 0));

            } else if (enfant instanceof TextField textField) {
                textField.setPrefWidth(width * 0.5);
                textField.setPrefHeight(height * 0.1);
                textField.setFont(new Font(height * 0.045));
                VBox.setMargin(textField, new Insets(0, 0, height * 0.05, 0));

            } else if (enfant instanceof Slider slider) {
                slider.setPrefWidth(width * 0.5); // Slider plus large
                slider.setPrefHeight(height * 0.1);
                VBox.setMargin(slider, new Insets(0, 0, height * 0.05, 0));
            }
        }
    }

    public static void redimensionnerHboxVobxs(Pane parent){
        double width = parent.getWidth();
        double height = parent.getHeight();

        ObservableList<Node> enfants = parent.getChildren();
        int nbEtapes = enfants.size();
        VBox temp = null;
        for (Node enfant : enfants) {
            temp = (VBox)enfant;

            HBox.setMargin(temp,new Insets(height*0.05, 0, 0, width*0.05));


            temp.setMaxSize(Double.MAX_VALUE,height);
            temp.setPrefSize(width/nbEtapes,height);
            HBox.setHgrow(temp, Priority.ALWAYS);
        }

        HBox.setMargin(temp,new Insets(height*0.05, width*0.05, 0, width*0.05));
    }

    public static void redimension(VBox parent){

    }
}
