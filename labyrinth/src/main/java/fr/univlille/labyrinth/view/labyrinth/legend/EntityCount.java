package fr.univlille.labyrinth.view.labyrinth.legend;

import fr.univlille.labyrinth.view.Shape;
import javafx.scene.paint.Color;

/*
Classe utilitaire pour LegendPanel représentant le compte d'une entité avec ses caractéristiques nécessaires à l'affichage
*/
class EntityCount {
    String name;
    Shape shape;
    Color color;
    int count;

    EntityCount(String name, Shape shape, Color color) {
        this.name = name;
        this.shape = shape;
        this.color = color;
        this.count = 0;
    }

    void increment() {
        count++;
    }
}
