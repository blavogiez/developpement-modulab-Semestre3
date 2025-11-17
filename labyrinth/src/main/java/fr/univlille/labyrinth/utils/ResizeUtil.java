package fr.univlille.labyrinth.utils;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class ResizeUtil {

    private static final double FONT_PERCENTAGE_WIDTH = 0.55;
    private static final double DEFAULT_CONTROLS_PERCENTAGE_LEFT_MARGIN = 0.5;
    private static final double DEFAULT_CONTROLS_PERCENTAGE_BOTTOM_MARGIN = 0.15;

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
            button.setFont(new Font(height * FONT_PERCENTAGE_WIDTH));
            button.setPadding(new Insets(0, 0, 0, 0));
            if(button.getText().length() * height * FONT_PERCENTAGE_WIDTH < width){
                button.setFont(new Font(height*FONT_PERCENTAGE_WIDTH));
            }else{
                button.setFont(new Font(width/button.getText().length()));
            }
        } else if(cont instanceof Label label){
            if(label.getText().length() * height * FONT_PERCENTAGE_WIDTH < width){
                label.setFont(new Font(height * FONT_PERCENTAGE_WIDTH));
            }else{
                label.setFont(new Font(width/label.getText().length()));
            }
        } else if(cont instanceof TextField textField){
            textField.setFont(new Font(height * FONT_PERCENTAGE_WIDTH));
        }else if(cont instanceof ComboBox combo) {

            int maxItemLength = getMaxLengthItemComboBox(combo);
            if ( maxItemLength * height * FONT_PERCENTAGE_WIDTH < width) {
                combo.setStyle("-fx-font-size: " + (height * FONT_PERCENTAGE_WIDTH) + "px;");
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
     * @param cont
     * @param percentageWidth
     * @param percentageHeight
     */
    public static void resizeControlToParentSize(Pane parent, Control cont, double percentageWidth, double percentageHeight){
        double width = parent.getWidth();
        double height = parent.getHeight();
        ObservableList<Node> children = parent.getChildren();
        int nbChildren = children.size();
        resizeControlInPane(parent, cont,(width/nbChildren)*percentageWidth,height*percentageHeight, 0, 0, 0, 0);
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
        ObservableList<Node> children = parent.getChildren();
        for (Node child : children) {
            resizeControlInPane( parent, (Control)child,width,height, height*MarginT, MarginR, MarginB, MarginL);
        }
    }

    /** 
     * @param parent
     * @param percentageWidth
     * @param percentageHeight
     * @param MarginT
     * @param MarginR
     * @param MarginB
     * @param MarginL
     */
    public static void resizeControlsToParentSize(Pane parent,  double percentageWidth, double percentageHeight, double MarginT, double MarginR, double MarginB, double MarginL ){
        double width = parent.getWidth();
        double height = parent.getHeight();
        boolean isHBox = parent instanceof HBox;

        ObservableList<Node> children = parent.getChildren();
        int nbChildren = children.size();
        if(isHBox){
            resizeControlsInPane( parent,(width/nbChildren)*percentageWidth,height*percentageHeight, 0, 0, 0, (width/nbChildren)*DEFAULT_CONTROLS_PERCENTAGE_LEFT_MARGIN);
        }else{
            resizeControlsInPane( parent,width*percentageWidth,(height/nbChildren)*percentageHeight, 0, 0, (height/nbChildren)*DEFAULT_CONTROLS_PERCENTAGE_BOTTOM_MARGIN, 0);
        }
    }

    /**
     * @param parent
     * @param percentageWidth
     * @param percentageHeight
     */
    public static void resizeControlsToParentSize(Pane parent, double percentageWidth, double percentageHeight ){
        boolean isHBox = parent instanceof HBox;
        if(isHBox){
            resizeControlsInPane( parent,percentageWidth,percentageHeight, 0, 0, 0, DEFAULT_CONTROLS_PERCENTAGE_LEFT_MARGIN);
        }else{
            resizeControlsInPane( parent,percentageWidth,percentageHeight, 0, 0, DEFAULT_CONTROLS_PERCENTAGE_BOTTOM_MARGIN, 0);
        }
    }

    /** 
     * @param parent
     */
    public static void resizeControlsToParentSize(Pane parent){
        boolean isHBox = parent instanceof HBox;

        if(isHBox){
            resizeControlsToParentSize( parent,0.5,0.4, 0, 0, 0, DEFAULT_CONTROLS_PERCENTAGE_LEFT_MARGIN);
        }else{
            resizeControlsToParentSize( parent,0.4,0.5, 0, 0, DEFAULT_CONTROLS_PERCENTAGE_BOTTOM_MARGIN, 0);
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
        ObservableList<Node> children = parent.getChildren();
        for (Node child : children) {
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

        ObservableList<Node> children = parent.getChildren();
        int nbChildren = children.size();
        if(isHBox){
            resizePanesInPane( parent,(width/nbChildren)*0.90,height*0.90, 0, 0, 0, 0);
        }else{
            resizePanesInPane( parent,width*0.90,(height/nbChildren)*0.80, 0, 0, 0, 0);
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
