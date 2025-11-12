package fr.univlille.labyrinth.utils;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;

import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.List;

public class ResizeUtil {

    /** 
     * @param parent
     * @param cont
     * @param width
     * @param height
     * @param MarginT
     * @param MarginR
     * @param MarginB
     * @param MarginL
     */
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
            if(button.getText().length() * height * 0.60 < width){
                button.setFont(new Font(height*0.60));
            }else{
                button.setFont(new Font(width/button.getText().length()));
            }
        } else if(cont instanceof Label label){
            if(label.getText().length() * height * 0.60 < width){
                label.setFont(new Font(height * 0.60));
            }else{
                label.setFont(new Font(width/label.getText().length()));
            }
        } else if(cont instanceof TextField textField){
            textField.setFont(new Font(height * 0.60));
        }else if(cont instanceof ComboBox combo) {

            int maxItemLength = getMaxLengthItemComboBox(combo);
            if ( maxItemLength * height * 0.60 < width) {
                combo.setStyle("-fx-font-size: " + (height * 0.60) + "px;");
            } else {
                combo.setStyle("-fx-font-size: " + (width / maxItemLength) + "px;");
            }
        }
        if(parent instanceof VBox){
            VBox.setMargin(cont, new Insets(MarginT, MarginR, MarginB, MarginL));
        } else if (parent instanceof HBox) {
            HBox.setMargin(cont, new Insets(MarginT, MarginR, MarginB, MarginL));
        }
    }

    /** 
     * @param parent
     * @param width
     * @param height
     * @param MarginT
     * @param MarginR
     * @param MarginB
     * @param MarginL
     */
    public static void resizeControlsInPane(Pane parent, double width, double height, double MarginT, double MarginR, double MarginB, double MarginL){
        ObservableList<Node> childs = parent.getChildren();
        for (Node child : childs) {
            resizeControlInPane( parent, (Control)child,width,height, height*MarginT, MarginR, MarginB, MarginL);
        }
    }

    /** 
     * @param parent
     */
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

    /** 
     * @param parent
     * @param pane
     * @param width
     * @param height
     * @param MarginT
     * @param MarginR
     * @param MarginB
     * @param MarginL
     */
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

    /** 
     * @param parent
     * @param width
     * @param height
     * @param MarginT
     * @param MarginR
     * @param MarginB
     * @param MarginL
     */
    public static void resizePanesInPane(Pane parent, double width, double height, double MarginT, double MarginR, double MarginB, double MarginL){
        ObservableList<Node> childs = parent.getChildren();
        for (Node child : childs) {
            resizePaneInPane( parent, (Pane) child,width,height, height*MarginT, MarginR, MarginB, MarginL);
        }
    }

    /** 
     * @param parent
     */
    public static void resizePanesInPane(Pane parent){
        double width = parent.getWidth();
        double height = parent.getHeight();
        boolean isHBox = parent instanceof HBox;

        ObservableList<Node> childs = parent.getChildren();
        int nbChilds = childs.size();
        if(isHBox){
            resizePanesInPane( parent,(width/nbChilds)*0.90,height*0.90, 0, 0, 0, 0);
        }else{
            resizePanesInPane( parent,width*0.90,(height/nbChilds)*0.80, 0, 0, 0, 0);
        }
    }

    /** 
     * @param comboBox
     * @return int
     */
    private static int getMaxLengthItemComboBox(ComboBox comboBox){
        int maxLength = 0;
        for (Object item : comboBox.getItems()) {
            if (item != null) {
                int length = item.toString().length();
                if (length > maxLength) {
                    maxLength = length;
                }
            }
        }
        return maxLength;
    }
}
