package fr.univlille.labyrinth.view;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class Redimension {

    public static void redimensionnerBouton(Pane parent,double PourcentageLongueur,double PourcentageHauteur, double PourcentageMarge) {
        double width = parent.getWidth();
        double height = parent.getHeight();

        ObservableList<Node> enfants = parent.getChildren();
        for (Node enfant : enfants) {
            if (enfant instanceof Button bouton) {
                bouton.setPrefWidth(width * PourcentageLongueur);
                bouton.setPrefHeight(height * PourcentageHauteur);
                bouton.setFont(new Font(height * 0.05));

                Insets margin = new Insets(0, 0, height * PourcentageMarge, 0);

                if (parent instanceof VBox) {
                    VBox.setMargin(bouton, margin);
                } else if (parent instanceof HBox) {
                    HBox.setMargin(bouton, margin);
                } else if (parent instanceof GridPane) {
                    GridPane.setMargin(bouton, margin);
                } else if (parent instanceof FlowPane) {
                    FlowPane.setMargin(bouton, margin);
                }
            }
        }
    }

    public static void redimensionnerBouton(Pane parent) {
        double width = parent.getWidth();
        double height = parent.getHeight();

        ObservableList<Node> enfants = parent.getChildren();
        for (Node enfant : enfants) {
            if (enfant instanceof Button bouton) {
                bouton.setPrefWidth(width * 0.4);
                bouton.setPrefHeight(height * 0.15);
                bouton.setFont(new Font(height * 0.05));

                Insets margin = new Insets(0, 0, height * 0.10, 0);

                if (parent instanceof VBox) {
                    VBox.setMargin(bouton, margin);
                } else if (parent instanceof HBox) {
                    HBox.setMargin(bouton, margin);
                } else if (parent instanceof GridPane) {
                    GridPane.setMargin(bouton, margin);
                } else if (parent instanceof FlowPane) {
                    FlowPane.setMargin(bouton, margin);
                }
            }
        }
    }
}
