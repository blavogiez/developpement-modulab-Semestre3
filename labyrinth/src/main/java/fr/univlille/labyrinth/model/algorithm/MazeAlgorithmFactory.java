package fr.univlille.labyrinth.model.algorithm;

public enum MazeAlgorithmFactory {
    PERFECT(MazeAlgorithmPerfect.getInstance()),
    PERFECTRANDOM(MazeAlgorithmPerfectRandom.getInstance()),
    STANDARD(MazeAlgorithmStandard.getInstance()),
    STANDARDRANDOM(MazeAlgorithmStandardRandom.getInstance()),
    STANDARDLARGEUR(MazeAlgorithmStandardLargeur.getInstance());

    private MazeAlgorithmFactory(MazeAlgorithmTemplate maze){
        this.mazeAlgorithm=maze;
    }

    private MazeAlgorithmTemplate mazeAlgorithm;

    public MazeAlgorithmTemplate getAlgorithm(){
        return mazeAlgorithm;
    }

    public boolean isPerfect(){
        return this == PERFECT || this == PERFECTRANDOM;
    }
}
