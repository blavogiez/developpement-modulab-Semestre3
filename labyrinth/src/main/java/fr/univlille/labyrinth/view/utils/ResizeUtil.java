package fr.univlille.labyrinth.view.utils;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

/**
 * Utilitaire pour le redimensionnement des composants graphiques.
 * Cette classe fournit des méthodes pour redimensionner dynamiquement les contrôles et les panneaux
 * en fonction de la taille de leur conteneur parent.
 *
 * @author Antonin, Angèl, Baptiste, Romain, Victor
 * @version 1.0
 * @since 1.0
 */
public class ResizeUtil {

    private static final double FONT_PERCENTAGE_WIDTH = 0.55;
    private static final double DEFAULT_CONTROLS_PERCENTAGE_LEFT_MARGIN = 0.5;
    private static final double DEFAULT_CONTROLS_PERCENTAGE_BOTTOM_MARGIN = 0.15;

    /**
     * Redimensionne un contrôle dans un panneau avec des dimensions et des marges spécifiées.
     * Cette méthode redimensionne également la police des contrôles en fonction de leur taille.
     *
     * @param parent le panneau parent contenant le contrôle
     * @param cont le contrôle à redimensionner
     * @param width la largeur souhaitée pour le contrôle
     * @param height la hauteur souhaitée pour le contrôle
     * @param MarginT la marge supérieure
     * @param MarginR la marge droite
     * @param MarginB la marge inférieure
     * @param MarginL la marge gauche
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
     * Redimensionne un contrôle en pourcentage de la taille de son panneau parent.
     *
     * @param parent le panneau parent contenant le contrôle
     * @param cont le contrôle à redimensionner
     * @param percentageWidth le pourcentage de la largeur du parent à utiliser
     * @param percentageHeight le pourcentage de la hauteur du parent à utiliser
     */
    public static void resizeControlToParentSize(Pane parent, Control cont, double percentageWidth, double percentageHeight){
        double width = parent.getWidth();
        double height = parent.getHeight();
        ObservableList<Node> children = parent.getChildren();
        int nbChildren = children.size();
        resizeControlInPane(parent, cont,(width/nbChildren)*percentageWidth,height*percentageHeight, 0, 0, 0, 0);
    }

    /**
     * Redimensionne tous les contrôles dans un panneau avec des dimensions et des marges spécifiées.
     *
     * @param parent le panneau parent contenant les contrôles
     * @param width la largeur souhaitée pour les contrôles
     * @param height la hauteur souhaitée pour les contrôles
     * @param MarginT la marge supérieure
     * @param MarginR la marge droite
     * @param MarginB la marge inférieure
     * @param MarginL la marge gauche
     */
    public static void resizeControlsInPane(Pane parent, double width, double height, double MarginT, double MarginR, double MarginB, double MarginL){
        ObservableList<Node> children = parent.getChildren();
        for (Node child : children) {
            resizeControlInPane( parent, (Control)child,width,height, height*MarginT, MarginR, MarginB, MarginL);
        }
    }

    /**
     * Redimensionne tous les contrôles dans un panneau en pourcentage de la taille du parent.
     *
     * @param parent le panneau parent contenant les contrôles
     * @param percentageWidth le pourcentage de la largeur du parent à utiliser
     * @param percentageHeight le pourcentage de la hauteur du parent à utiliser
     * @param MarginT la marge supérieure
     * @param MarginR la marge droite
     * @param MarginB la marge inférieure
     * @param MarginL la marge gauche
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
     * Redimensionne tous les contrôles dans un panneau en pourcentage de la taille du parent.
     *
     * @param parent le panneau parent contenant les contrôles
     * @param percentageWidth le pourcentage de la largeur du parent à utiliser
     * @param percentageHeight le pourcentage de la hauteur du parent à utiliser
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
     * Redimensionne tous les contrôles dans un panneau en utilisant des paramètres par défaut.
     *
     * @param parent le panneau parent contenant les contrôles
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
     * Redimensionne un panneau dans un panneau parent avec des dimensions et des marges spécifiées.
     *
     * @param parent le panneau parent contenant le panneau à redimensionner
     * @param pane le panneau à redimensionner
     * @param width la largeur souhaitée pour le panneau
     * @param height la hauteur souhaitée pour le panneau
     * @param MarginT la marge supérieure
     * @param MarginR la marge droite
     * @param MarginB la marge inférieure
     * @param MarginL la marge gauche
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
     * Redimensionne tous les panneaux dans un panneau parent avec des dimensions et des marges spécifiées.
     *
     * @param parent le panneau parent contenant les panneaux
     * @param width la largeur souhaitée pour les panneaux
     * @param height la hauteur souhaitée pour les panneaux
     * @param MarginT la marge supérieure
     * @param MarginR la marge droite
     * @param MarginB la marge inférieure
     * @param MarginL la marge gauche
     */
    public static void resizePanesInPane(Pane parent, double width, double height, double MarginT, double MarginR, double MarginB, double MarginL){
        ObservableList<Node> children = parent.getChildren();
        for (Node child : children) {
            resizePaneInPane( parent, (Pane) child,width,height, height*MarginT, MarginR, MarginB, MarginL);
        }
    }

    /**
     * Redimensionne tous les panneaux dans un panneau parent en utilisant des paramètres par défaut.
     *
     * @param parent le panneau parent contenant les panneaux
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
     * Calcule la longueur maximale des éléments d'une ComboBox.
     *
     * @param comboBox la ComboBox à analyser
     * @return la longueur du plus long élément dans la ComboBox
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
