package fr.univlille.labyrinth.model.maze.entities.factory;

import fr.univlille.labyrinth.model.maze.entities.EntityType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Initialise une EntityConfiguration à partir d'une chaine de caractères (typiquement contenue dans le csv des défis par défaut)
 * Si rien n'est entré, on considère qu'il y a juste un joueur et une sortie aux comportements basiques (étapes 1 à 6)
 */
public class EntityConfigurationParser {

    private static final String DEFAULT_CONFIGURATION = "t=PLAYER;q=2;m=PLAYER|t=EXIT;q=1;m=DEFAULT";

    public static List<EntityConfiguration> parse(String configuration) {
        if (configuration == null || configuration.trim().isEmpty() || configuration.equalsIgnoreCase("DEFAULT")) {
            configuration = DEFAULT_CONFIGURATION;
        }

        List<EntityConfiguration> configurations = new ArrayList<>();
        String[] blocDEntite = configuration.split("\\|");

        for (String block : blocDEntite) {
            configurations.add(parseEntityBlock(block));
        }

        return configurations;
    }

    private static EntityConfiguration parseEntityBlock(String block) {
        Map<String, String> params = new HashMap<>();
        String[] parts = block.split(";");

        for (String part : parts) {
            String[] keyValue = part.split("=");
            if (keyValue.length == 2) {
                params.put(keyValue[0].trim().toLowerCase(), keyValue[1].trim());
            }
        }

        EntityType type = EntityType.valueOf(params.get("t").toUpperCase());
        int quantity = Integer.parseInt(params.getOrDefault("q", "1"));
        String moveBehavior = params.getOrDefault("m", "DEFAULT");

        return new EntityConfiguration(type, quantity, moveBehavior);
    }
}
