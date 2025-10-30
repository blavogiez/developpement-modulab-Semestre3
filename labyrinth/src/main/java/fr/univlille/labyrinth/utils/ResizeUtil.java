package fr.univlille.labyrinth.utils;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class ResizeUtil {

    public static void resizeControlInPane(Pane parent, Control cont, double width, double height, double MarginT, double MarginR, double MarginB, double MarginL){
        cont.setMinWidth(width);
        cont.setPrefWidth(width);
        if(cont instanceof Button button){
            button.setFont(new Font(height * 0.50));
        } else if(cont instanceof Label label){
            label.setFont(new Font(height * 0.60));
        }
        if(parent instanceof VBox){
            VBox.setMargin(cont, new Insets(MarginT, MarginR, MarginB, MarginL));
        } else if (parent instanceof HBox) {
            HBox.setMargin(cont, new Insets(MarginT, MarginR, MarginB, MarginL));
        }
    }

    public static void resizeControlsInPane(Pane parent){
        double width = parent.getWidth();
        double height = parent.getHeight();
        boolean isHBox = parent instanceof HBox;
                
        ObservableList<Node> childs = parent.getChildren();
        int nbchilds = childs.size();
        Control temp = null;
        for (Node child : childs) {
            temp = (Control)child;
            if(isHBox){
                resizeControlInPane( parent, temp,(width/nbchilds)*0.40,height*0.50, 0, 0, 0, (width/nbchilds)*0.05);
            }else{
                resizeControlInPane( parent, temp,width*0.50,(height/nbchilds)*0.40, 0, 0, (height/nbchilds)*0.15, 0);
            }

        }
    }

    public static void resizePaneInPane(Pane parent, Pane pane, double width, double height, double MarginT, double MarginR, double MarginB, double MarginL){
        pane.setPrefWidth(width);
        pane.setPrefHeight(height);

        if(parent instanceof VBox){
            VBox.setMargin(pane, new Insets(MarginT, MarginR, MarginB, MarginL));
        } else if (parent instanceof HBox) {
            HBox.setMargin(pane, new Insets(MarginT, MarginR, MarginB, MarginL));
        }
    }

    public static void resizePanesInPane(Pane parent){
        double width = parent.getWidth();
        double height = parent.getHeight();

        ObservableList<Node> childs = parent.getChildren();
        int nbEtapes = childs.size();
        Pane temp = null;
        for (Node child : childs) {
            temp = (Pane)child;
            resizePaneInPane( parent, (Pane)child,width/nbEtapes,height, height*0.05, 0, 0, width*0.05);
        }
        resizePaneInPane( parent, temp,width/nbEtapes,height, height*0.05, width*0.05, 0, width*0.05);
    }
}
