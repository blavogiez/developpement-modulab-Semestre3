package fr.univlille.labyrinth.controller.progressionmode.labyrinthviewtype;

import fr.univlille.labyrinth.model.save.ViewType;

public class ViewTypeFactory {
    public static String getCorrespondingPage(ViewType viewType) {
        switch (viewType.name()) {
            case "NORMAL" : return "progressionmode/labyrinthviewtype/NormalViewProgressionModeLabyrinth.fxml";

            case "LOCAL" : return "progressionmode/labyrinthviewtype/LocalViewProgressionModeLabyrinth.fxml";

            case "EXPLORATION" : return "progressionmode/labyrinthviewtype/ExplorationViewProgressionModeLabyrinth.fxml";
        }
        return "progressionmode/LevelSelection.fxml";
    }
}
