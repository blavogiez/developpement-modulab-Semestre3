package fr.univlille.labyrinth.model.algorithm;

import fr.univlille.labyrinth.model.Direction;
import fr.univlille.labyrinth.model.Position;

import java.util.*;


/**
 * Génère un labyrinthe en utilisant un parcours en largeur.
 * Le point de fin est choisi parmi les cellules à distance minPath,
 * garantissant un chemin d'une longueur minimale spécifiée dépendant de la taille du labyrinthe.
 *
 * @author Antonin, Angel, Baptise, Romain, Victor
 * @version 0.0
 * @since 0.0
 */
public class MazeAlgorithmStandardLargeur extends MazeAlgorithmTemplate {

    private static MazeAlgorithmStandardLargeur instance;
    private Position start, end;

    /**
     * Génère un labyrinthe selon un algorithme en largeur.
     *
     * @param width             largeur du labyrinthe
     * @param height            hauteur du labyrinthe
     * @param percentageOfWall  pourcentage de murs (entre 0 et 1)
     * @param pathLength     longueur minimale du chemin entre début et fin
     */
    public boolean[][] createMaze(int width, int height, double percentageOfWall, int pathLength) {
        maze = new boolean[width][height];
        percentageWall = percentageOfWall;

        int startX, startY;
        // Distance maximum possible d'un chemin dans nos labyrinthes!
        int maxPossibleDistance = (width - 3) + (height - 3);
        do {
            startX = 1 + new Random().nextInt(width - 2);
            startY = 1 + new Random().nextInt(height - 2);
            // Puis on vérifie dans le while que la case est placée de sorte à ce qu'il existe une cellule (donc une cellule potentielle de fin) vers laquelle il existe un chemin de longueur "pathLength"
            // Sinon, on refait une génération
        } while (pathLength <= maxPossibleDistance && Math.max(startX - 1, (width - 2) - startX) + Math.max(startY - 1, (height - 2) - startY) < pathLength + (pathLength < maxPossibleDistance ? Math.max(2, pathLength / 10) : 0));

        start = new Position(startX, startY);

        Map<Position,Integer> distances = new HashMap<>();
        distances.put(start,0);

        Map<Position, Position> parentMap = tracePathLargeur(start, distances);

        List<Position> farthest = getFarthestCells(pathLength, distances);

        // Si aucune cellule n'est trouvée à la distance spécifiée, on cherche les cellules
        // les plus éloignées possibles
        if (farthest.isEmpty()) {
            // On recherche les cellules à la distance maximale possible
            int maxDistance = distances.values().stream().mapToInt(Integer::intValue).max().orElse(0);
            if (maxDistance > 0) {
                // On réduit la distance minimale requise
                farthest = getFarthestInMap(maxDistance, distances);
            }
            
            // Si on ne trouve toujours aucune cellule appropriée, on sélectionne la plus éloignée possible
            if (farthest.isEmpty()) {
                throw new MazeSizeException("Une erreur à eu lieu lors de la génération du labyrinthe (pas de cellules à la longueur spécifiée)");
            }
        }
        
        end = farthest.get(new Random().nextInt(farthest.size()));

        markFinalPath(parentMap, start, end);

        removePercentageWall();

        return maze;
    }

    /** 
     * @return Position
     */
    public Position getStart() {
        return start;
    }

    /** 
     * @return Position
     */
    public Position getEnd() {
        return end;
    }

    /**
     * Marque toutes les cellules atteignables et garde les parents pour retracer plus tard.
     */
    private Map<Position, Position> tracePathLargeur(Position start, Map<Position,Integer> distances) {
        Queue<Position> queue = new LinkedList<>();
        Map<Position, Position> parent = new HashMap<>();

        queue.add(start);
        parent.put(start, null);
        markCell(start);

        while (!queue.isEmpty()) {
            Position current = queue.poll();
            List<Direction> directions = new ArrayList<>(Arrays.stream(Direction.values()).toList());
            Collections.shuffle(directions);

            checkAllDirections(distances, directions, current, parent, queue);
        }
        return parent;
    }

    /** 
     * @param distances
     * @param directions
     * @param current
     * @param parent
     * @param queue
     */
    private void checkAllDirections(Map<Position, Integer> distances, List<Direction> directions, Position current, Map<Position, Position> parent, Queue<Position> queue) {
        for (int i = 0; i< directions.size(); i++) {
            Direction dir = directions.get(i);
            int nx = current.getX() + dir.getX();
            int ny = current.getY() + dir.getY();
            Position next = new Position(nx, ny);

            if (isInside(nx, ny) && isWall(next)) {
                markPathBetweenCell(current,next);
                parent.put(next, current);
                distances.put(next, distances.get(current) + 1);
                queue.add(next);
            }
        }
    }

    /**
     * Récupère les cellules éloignées du départ (= minPathLength)
     */
    private List<Position> getFarthestCells(int minPathLength, Map<Position, Integer> distances) {
        List<Position> positions = new ArrayList<>();
        for (Map.Entry<Position,Integer> entrys : distances.entrySet()){
            if (entrys.getValue().equals(minPathLength)){
                positions.add(entrys.getKey());
            }
        }

        return positions;
    }

    /** 
     * @param minPathLength
     * @param distance
     * @return List<Position>
     */
    private static List<Position> getFarthestInMap(int minPathLength, Map<Position, Integer> distance) {
        List<Position> farthest = new ArrayList<>();
        for (Map.Entry<Position, Integer> e : distance.entrySet()) {
            if (e.getValue() >= minPathLength) {
                farthest.add(e.getKey());
            }
        }
        return farthest;
    }



    /**
     * Retrace le chemin du end vers le start et ne garde que ces cellules comme "PATH".
     */
    private void markFinalPath(Map<Position, Position> parentMap, Position start, Position end) {
        clearMaze();
        Position current = end;
        while (current != null) {
            maze[current.getX()][current.getY()] = PATH;
            current = parentMap.get(current);
        }
    }

    /**
     * Met toutes les cases à WALL
     */
    protected void clearMaze() {
        for (int x = 0; x < maze.length; x++) {
            for (int y = 0; y < maze[0].length; y++) {
                maze[x][y] = WALL;
            }
        }
    }

    /** 
     * @param x
     * @param y
     * @return boolean
     */
    protected boolean isInside(int x, int y) {
        return x > 0 && y > 0 && x < maze.length - 1 && y < maze[0].length - 1;
    }

    /** 
     * @return MazeAlgorithmStandardLargeur
     */
    public static MazeAlgorithmStandardLargeur getInstance() {
        if (instance == null) instance = new MazeAlgorithmStandardLargeur();
        return instance;
    }
}
