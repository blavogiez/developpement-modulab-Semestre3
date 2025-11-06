package fr.univlille.labyrinth.view;

import fr.univlille.labyrinth.model.maze.entities.EntityType;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

/*
 * Classe de liaison entre les entités et leurs formes et couleurs
 * De ce fait la configuration est totale 
 * (On peut imaginer un fichier CSV de configuration de couleurs)
 */
public class EntityShapeMapper {
    private final Map<EntityType, EntityShape> shapeMap;
    private final Map<EntityType, Color> colorMap;

    public EntityShapeMapper() {
        this.shapeMap = new HashMap<>();
        this.colorMap = new HashMap<>();
        initializeDefaults();
    }

    private void initializeDefaults() {
        shapeMap.put(EntityType.PLAYER, EntityShape.CIRCLE);
        shapeMap.put(EntityType.EXIT, EntityShape.SQUARE);
        
        colorMap.put(EntityType.PLAYER, GameColors.PLAYER.getColor());
        colorMap.put(EntityType.EXIT, GameColors.EXIT.getColor());
    }

    public void setShape(EntityType entityType, EntityShape shape) {
        shapeMap.put(entityType, shape);
    }

    public void setColor(EntityType entityType, Color color) {
        colorMap.put(entityType, color);
    }

    public EntityShape getShape(EntityType entityType) {
        return shapeMap.getOrDefault(entityType, EntityShape.CIRCLE);
    }

    public Color getColor(EntityType entityType) {
        return colorMap.getOrDefault(entityType, Color.BLACK);
    }
}