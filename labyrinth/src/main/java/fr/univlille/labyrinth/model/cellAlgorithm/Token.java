package fr.univlille.labyrinth.model.cellAlgorithm;

public enum Token {
    NOTHING(""),
    PLAYER("P"),
    MONSTER("M");
    private Token(String visual){
        this.visual=visual;
    }


    private String visual;

    public String getVisual() {
        return visual;
    }
}
