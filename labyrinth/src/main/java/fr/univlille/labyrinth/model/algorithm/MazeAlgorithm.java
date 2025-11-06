package fr.univlille.labyrinth.model.algorithm;

public enum MazeAlgorithm {
    PERFECT(PerfectAlgorithm.getInstance()),
    OTHER(null);

    private MazeAlgorithm(MazeAlgorithmTemplate MazeAlgorithm) {
        this.MazeAlgorithm = MazeAlgorithm;
    }

    private final MazeAlgorithmTemplate MazeAlgorithm;

    public MazeAlgorithmTemplate getAlgorithm(){
        return MazeAlgorithm;
    }

}
