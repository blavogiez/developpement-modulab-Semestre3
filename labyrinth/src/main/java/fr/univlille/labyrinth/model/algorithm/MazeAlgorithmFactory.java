package fr.univlille.labyrinth.model.algorithm;

public enum MazeAlgorithmFactory {
    PERFECT(PerfectAlgorithm.getInstance()),
    FUSION(PerfectAlgorithmRandomFusion.getInstance()),
    OTHER(null);

    private MazeAlgorithmFactory(MazeAlgorithm MazeAlgorithm) {
        this.MazeAlgorithm = MazeAlgorithm;
    }

    private final MazeAlgorithm MazeAlgorithm;

    public MazeAlgorithm getAlgorithm(){
        return MazeAlgorithm;
    }

    public boolean isPerfect() {
        return this.name().toUpperCase().contains("PERFECT");
    }

}
