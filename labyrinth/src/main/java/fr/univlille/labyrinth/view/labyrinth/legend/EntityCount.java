package fr.univlille.labyrinth.view.labyrinth.legend;

import fr.univlille.labyrinth.view.Shape;
import javafx.scene.paint.Color;

/**
 * Classe utilitaire pour LegendPanel représentant le compte d'une entité avec ses caractéristiques nécessaires à l'affichage.
 * Stocke le nom, la forme, la couleur et le nombre d'entités pour l'affichage dans la légende.
 *
 * @author Antonin, Angèl, Baptiste, Romain, Victor
 * @version 1.0
 * @since 1.0
 */
class EntityCount {
    /** Le nom de l'entité */
    String name;
    /** La forme de l'entité */
    Shape shape;
    /** La couleur de l'entité */
    Color color;
    /** Le nombre d'entités */
    int count;

    /**
     * Constructeur de la classe EntityCount avec un compteur initialisé à 0.
     *
     * @param name Le nom de l'entité
     * @param shape La forme de l'entité
     * @param color La couleur de l'entité
     */
    EntityCount(String name, Shape shape, Color color) {
        this.name = name;
        this.shape = shape;
        this.color = color;
        this.count = 0;
    }

    /**
     * Incrémente le compteur d'entités.
     */
    void increment() {
        count++;
    }
}
