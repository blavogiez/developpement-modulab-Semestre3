package fr.univlille.labyrinth.view;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

public class Redimension {

    public static void redimensionnerBouton(Pane parent) {
        double width = parent.getWidth();
        double height = parent.getHeight();

        ObservableList<Node> enfants = parent.getChildren();
        for (Node enfant : enfants) {
            if (enfant instanceof Button bouton) {
                Insets margin = new Insets(0, width * 0.40, height * 0.10, width * 0.40);

                if (parent instanceof VBox) {
                    VBox.setMargin(bouton, margin);
                } else if (parent instanceof HBox) {
                    HBox.setMargin(bouton, margin);
                } else if (parent instanceof GridPane) {
                    GridPane.setMargin(bouton, margin);
                } else if (parent instanceof FlowPane) {
                    FlowPane.setMargin(bouton, margin);
                }
                System.out.println(height);
                System.out.println(width);
            }
        }
    }
}
