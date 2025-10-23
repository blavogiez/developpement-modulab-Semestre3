package fr.univlille.labyrinth.utils;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class Redimension {

    public static void redimensionnerVboxControles(VBox parent) {
        redimensionnerVboxControles(parent, 0.4, 0.1,  0, 0, 0.05, 0);
    }

    public static void redimensionnerVboxControles(VBox parent,double pourcentWidth, double MarginT, double MarginR, double MarginB, double MarginL) {
        redimensionnerVboxControles(parent, pourcentWidth, pourcentWidth,  MarginT, MarginR, MarginB, MarginL);
    }

    public static void redimensionnerVboxControles(VBox parent,double pourcentWidth, double pourcentHeigth, double MarginT, double MarginR, double MarginB, double MarginL) { //Toutes les tailles en pourcentage pa rapport au contenant parent
        double width = parent.getWidth();
        double height = parent.getHeight();

        ObservableList<Node> enfants = parent.getChildren();
        for (Node enfant : enfants) {
            if (enfant instanceof Control cont) {
                redimensionnerPaneControl(parent, cont, width*pourcentWidth, height*pourcentHeigth,  height*MarginT, width*MarginR, height*MarginB, width*MarginL);
            }
        }
    }

    public static void redimensionnerPanePanes(Pane parent){
        double width = parent.getWidth();
        double height = parent.getHeight();

        ObservableList<Node> enfants = parent.getChildren();
        int nbEtapes = enfants.size();
        Pane temp = null;
        for (Node enfant : enfants) {
            temp = (Pane)enfant;
            redimensionnerPanePane( parent, temp,width/nbEtapes,height, height*0.05, 0, 0, width*0.05);
        }
        redimensionnerPanePane( parent, temp,width/nbEtapes,height, height*0.05, width*0.05, 0, width*0.05);
    }

    public static void redimensionnerPaneControl(Pane parent, Control cont,double width,double height, double MarginT, double MarginR, double MarginB, double MarginL){
        cont.setPrefWidth(width);
        cont.setPrefHeight(height);
        if(cont instanceof Button button){
            button.setFont(new Font(height * 0.50));
        }
        if(parent instanceof VBox){
            VBox.setMargin(cont, new Insets(MarginT, MarginR, MarginB, MarginL));
        } else if (parent instanceof HBox) {
            HBox.setMargin(cont, new Insets(MarginT, MarginR, MarginB, MarginL));
        }
    }

    public static void redimensionnerPanePane(Pane parent, Pane pane,double width,double height, double MarginT, double MarginR, double MarginB, double MarginL){
        pane.setPrefWidth(width);
        pane.setPrefHeight(height);

        if(parent instanceof VBox){
            VBox.setMargin(pane, new Insets(MarginT, MarginR, MarginB, MarginL));
        } else if (parent instanceof HBox) {
            HBox.setMargin(pane, new Insets(MarginT, MarginR, MarginB, MarginL));
        }
    }
}
