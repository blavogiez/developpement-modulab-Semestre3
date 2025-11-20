package fr.univlille.labyrinth.model.algorithm;

public enum MazeAlgorithmFactory {
    PERFECT(PerfectAlgorithm.getInstance()),
    FUSION(PerfectAlgorithmFusionKruskalUnion.getInstance()),
    FUSION_LEGACY(PerfectAlgorithmRandomFusion.getInstance()),
    RANDOM(RandomAlgorithm.getInstance());

    private MazeAlgorithmFactory(MazeAlgorithm mazeAlgorithm) {
        this.mazeAlgorithm = mazeAlgorithm;
    }

    private final MazeAlgorithm mazeAlgorithm;

    public MazeAlgorithm getAlgorithm(){
        return mazeAlgorithm;
    }

    public boolean isPerfect() {
        return this.name().toUpperCase().contains("PERFECT") || this.name().toUpperCase().contains("FUSION");
    }

}
