package fr.univlille.labyrinth.model.algorithm;

import fr.univlille.labyrinth.model.maze.Direction;
import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.model.maze.Position;

/*
 * Version optimisée de l'algorithme fusion
 * Basé sur l'union find (Kruskal algorithm)
 */
public class PerfectAlgorithmFusionKruskalUnion extends MazeAlgorithm {

    int[] parent;      // Union-Find parent
    int[] size;        // tailles des ensembles
    int components;    // nombre de composantes restantes

    /** 
     * @param maze
     */
    @Override
    public void generateMaze(Maze maze) {
        super.generateMaze(maze);

        int total = height * width;
        parent = new int[total];
        size = new int[total];
        components = total;

        for (int i = 0; i < total; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        do {
            Direction direction = Direction.getRandomDirection();
            Position position = Position.getRandomPosition(height, width);
            fusionPosition(position, direction);
        } while (!isAllTheSameNumber());
    }

    /** 
     * @param y
     * @param x
     * @return int
     */
    int index(int y, int x) {
        return y * width + x;
    }

    /** 
     * @param x
     * @return int
     */
    int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    /** 
     * @param a
     * @param b
     */
    void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) return;

        if (size[pa] < size[pb]) {
            parent[pa] = pb;
            size[pb] += size[pa];
        } else {
            parent[pb] = pa;
            size[pa] += size[pb];
        }

        components--;
    }

    /** 
     * @param position
     * @param direction
     */
    void fusionPosition(Position position, Direction direction){
        Position next = position.add(direction.getX(), direction.getY());
        if (positionCorrecte(next, height, width)) {

            int id1 = index(position.getY(), position.getX());
            int id2 = index(next.getY(), next.getX());

            if (find(id1) != find(id2)) {
                removeWall(position, next, direction);

                union(id1, id2);
            }
        }
    }

    /** 
     * @return boolean
     */
    boolean isAllTheSameNumber(){
        return components == 1;
    }

    private static PerfectAlgorithmFusionKruskalUnion instance;

    /** 
     * @return PerfectAlgorithmFusionKruskalUnion
     */
    public static PerfectAlgorithmFusionKruskalUnion getInstance(){
        if (instance == null){
            instance = new PerfectAlgorithmFusionKruskalUnion();
        }
        return instance;
    }

    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "Fusion";
    }
}
