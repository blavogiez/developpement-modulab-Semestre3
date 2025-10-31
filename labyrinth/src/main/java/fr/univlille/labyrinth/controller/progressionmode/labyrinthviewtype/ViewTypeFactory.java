package fr.univlille.labyrinth.controller.progressionmode.labyrinthviewtype;

import fr.univlille.labyrinth.model.save.ViewType;

// Cette factory sert à établir le lien entre un ViewType et la page correspondante
// Il ne serait pas correct d'établir ce lien dans l'énumération ViewType elle-même, car le modèle deviendrait couplé aux controlleurs (Contrainte d'indépendance)
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
