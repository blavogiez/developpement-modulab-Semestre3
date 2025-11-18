package fr.univlille.labyrinth.model.algorithm.pathsearch;

import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.MazeWallChecker;
import fr.univlille.labyrinth.model.maze.Position;

import java.util.*;


/*
 * Classe statique utilitaire relative aux recherches de positions et de distance (une inconnue)
 * pour trouver les positions à une distance X d'une position A, ou trouver la distance entre une position A et B
 */
public class MazeDistance {

    /**
     * @param maze
     * @param start
     * @param targetDistance
     * @return DistanceResult
     */
    // parcourt le labyrinthe en largeur depuis start avec optimisation d'arret anticipe
    // cette version s'arrete des qu'on atteint la targetDistance (exploration par niveaux)
    // si targetDistance depasse la distance maximale possible, elle est automatiquement limitee a cette distance maximale
    // retourne toutes les positions situees a la distance cible (ou maximale si targetDistance est trop grande)
    // méthode commentée car assez complexe
    public static DistanceResult calculateAllDistances(Maze maze, Position start, int targetDistance) {
        Queue<Position> queue = new LinkedList<>();
        Map<Position, Integer> distances = new HashMap<>();
        List<Position> resultPositions = new ArrayList<>();

        // initialisation : la position de depart est a distance 0
        queue.add(start);
        distances.put(start, 0);
        int currentLevel = 0;  // niveau/distance actuel dans le BFS
        int actualDistance = 0;  // distance reelle atteinte (sera retournee)

        // BFS par niveaux : on traite niveau par niveau au lieu de position par position
        // cela permet de savoir exactement a quelle distance on est et de s'arreter au bon moment
        while (!queue.isEmpty()) {
            // levelSize = nombre de positions dans le niveau actuel
            // ca permet de traiter TOUT le niveau avant de passer au suivant
            int levelSize = queue.size();

            // arret anticipe si on a depasse la distance cible
            // on a deja explore tout ce qu'il fallait, inutile de continuer
            if (currentLevel > targetDistance) {
                break;
            }

            // si on est exactement au niveau cible, on collecte toutes les positions de ce niveau
            // on recupere TOUTES les positions du niveau (levelSize) et on s'arrete immediatement
            if (currentLevel == targetDistance) {
                for (int i = 0; i < levelSize; i++) {
                    resultPositions.add(queue.poll());
                }
                actualDistance = currentLevel;
                break;  // cas d'arret : on a trouve ce qu'on cherchait
            }

            // traite toutes les positions du niveau actuel
            // la boucle for avec levelSize garantit qu'on ne melange pas avec le niveau suivant
            for (int i = 0; i < levelSize; i++) {
                Position current = queue.poll();

                // explore les 4 directions (haut, bas, gauche, droite)
                for (Direction dir : Direction.values()) {
                    int nx = current.getX() + dir.getX();
                    int ny = current.getY() + dir.getY();
                    Position next = new Position(nx, ny);

                    // verifie que la position est valide :
                    // - dans les limites du labyrinthe
                    // - pas deja visitee (distances.containsKey)
                    // - pas de mur entre current et next
                    if (nx >= 0 && nx < maze.getWidth() && ny >= 0 && ny < maze.getHeight()
                        && !distances.containsKey(next)
                        && !MazeWallChecker.isWall(maze,current.getY(), current.getX(), ny, nx)) {
                        distances.put(next, currentLevel + 1);
                        queue.add(next);  // cette position sera traitee au prochain niveau
                    }
                }
            }

            actualDistance = currentLevel;  // sauvegarde du dernier niveau complete
            currentLevel++;  // passe au niveau suivant
        }

        // donc par exemple pour trouver toutes les positions à 5 de distance, on teste le niveau 1, puis 2... jusque 5
        // car on n'a aucun moyen de savoir s'il existe effectivement une position à 5 de distance, on doit être sur de connaitre la distance maximale

        // Cas original : si resultPositions est vide, c'est que targetDistance etait trop grand
        // (le labyrinthe est plus petit que prevu)
        // dans ce cas on retourne toutes les positions au niveau maximum atteint
        // exemple : targetDistance = 500 mais le labyrinthe fait max 120 de distance
        // on va retourner toutes les positions a distance 120
        if (resultPositions.isEmpty()) {
            for (Map.Entry<Position, Integer> entry : distances.entrySet()) {
                if (entry.getValue() == actualDistance) {
                    resultPositions.add(entry.getKey());
                }
            }
        }

        //System.out.println(new DistanceResult(resultPositions, actualDistance));
        return new DistanceResult(resultPositions, actualDistance);
    }

    /**
     * @param maze
     * @param start
     * @param end
     * @return Integer
     */
    // methode helper pour calculer la distance BFS (Parcours en largeur !) reelle entre deux positions
    // retourne null si aucun chemin n'existe
    public static Integer calculateDistance(Maze maze, Position start, Position end) {
        Queue<Position> queue = new LinkedList<>();
        Map<Position, Integer> distances = new HashMap<>();

        queue.add(start);
        distances.put(start, 0);

        while (!queue.isEmpty()) {
            Position current = queue.poll();

            if (current.equals(end)) {
                return distances.get(current);
            }

            for (Direction dir : Direction.values()) {
                int nx = current.getX() + dir.getX();
                int ny = current.getY() + dir.getY();
                Position next = new Position(nx, ny);

                if (nx >= 0 && nx < maze.getWidth() && ny >= 0 && ny < maze.getHeight()
                    && !distances.containsKey(next)
                    && !MazeWallChecker.isWall(maze,current.getY(), current.getX(), ny, nx)) {
                    distances.put(next, distances.get(current) + 1);
                    queue.add(next);
                }
            }
        }

        return null;
    }
}
