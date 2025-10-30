package fr.univlille.labyrinth.utils;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;

import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class ResizeUtil {

    public static void resizeControlInPane(Pane parent, Control cont, double width, double height, double MarginT, double MarginR, double MarginB, double MarginL){
        cont.setMinWidth(width);
        cont.setPrefWidth(width);
        cont.setMaxWidth(width);
        cont.setMinHeight(height);
        cont.setPrefHeight(height);
        cont.setMaxHeight(height);

        if(cont instanceof Button button){
            button.setFont(new Font(height * 0.50));
            button.setPadding(new Insets(0, 0, 0, 0));
        } else if(cont instanceof Label label){
            label.setFont(new Font(height * 0.60));
        }
        if(parent instanceof VBox){
            VBox.setMargin(cont, new Insets(MarginT, MarginR, MarginB, MarginL));
        } else if (parent instanceof HBox) {
            HBox.setMargin(cont, new Insets(MarginT, MarginR, MarginB, MarginL));
        }
    }

    public static void resizeControlsInPane(Pane parent, double width, double height, double MarginT, double MarginR, double MarginB, double MarginL){
        ObservableList<Node> childs = parent.getChildren();
        for (Node child : childs) {
            resizeControlInPane( parent, (Control)child,width,height, height*MarginT, MarginR, MarginB, MarginL);
        }
    }

    public static void resizeControlsInPane(Pane parent){
        double width = parent.getWidth();
        double height = parent.getHeight();
        boolean isHBox = parent instanceof HBox;

        ObservableList<Node> childs = parent.getChildren();
        int nbChilds = childs.size();
        if(isHBox){
            resizeControlsInPane( parent,(width/nbChilds)*0.40,height*0.50, 0, 0, 0, (width/nbChilds)*0.05);
        }else{
            resizeControlsInPane( parent,width*0.50,(height/nbChilds)*0.40, 0, 0, (height/nbChilds)*0.15, 0);
        }
    }

    public static void resizeEtapeControlsInPane(Pane parent){
        double width = parent.getWidth();
        double height = parent.getHeight();
        boolean isHBox = parent instanceof HBox;

        ObservableList<Node> childs = parent.getChildren();
        int nbChilds = childs.size();
        double boutonSize = Double.min(width*0.70,(height/nbChilds)*0.70);
        resizeControlsInPane( parent,boutonSize,boutonSize, 0, 0, (height/nbChilds)*0.2, 0);
    }

    public static void resizePaneInPane(Pane parent, Pane pane, double width, double height, double MarginT, double MarginR, double MarginB, double MarginL){
        pane.setMinWidth(width);
        pane.setPrefWidth(width);
        pane.setMaxWidth(width);
        pane.setMinHeight(height);
        pane.setPrefHeight(height);
        pane.setMaxHeight(height);

        if(parent instanceof VBox){
            VBox.setMargin(pane, new Insets(MarginT, MarginR, MarginB, MarginL));
        } else if (parent instanceof HBox) {
            HBox.setMargin(pane, new Insets(MarginT, MarginR, MarginB, MarginL));
        }
    }

    public static void resizePanesInPane(Pane parent, double width, double height, double MarginT, double MarginR, double MarginB, double MarginL){
        ObservableList<Node> childs = parent.getChildren();
        for (Node child : childs) {
            resizePaneInPane( parent, (Pane) child,width,height, height*MarginT, MarginR, MarginB, MarginL);
        }
    }

    public static void resizePanesInPane(Pane parent){
        double width = parent.getWidth();
        double height = parent.getHeight();
        boolean isHBox = parent instanceof HBox;

        ObservableList<Node> childs = parent.getChildren();
        int nbChilds = childs.size();
        if(isHBox){
            resizePanesInPane( parent,(width/nbChilds)*0.90,height, 0, 0, 0, 0);
        }else{
            resizePanesInPane( parent,width,(height/nbChilds)*0.90, 0, 0, 0, 0);
        }
    }

    public static void resizeEtapeInPane(Pane parent){
        double width = parent.getWidth();
        double height = parent.getHeight();

        ObservableList<Node> childs = parent.getChildren();
        int nbChilds = childs.size();
        resizePanesInPane( parent,(width/nbChilds)*0.80,height*0.95, 0, 0, 0, (width/nbChilds)*0.15);
    }

}
