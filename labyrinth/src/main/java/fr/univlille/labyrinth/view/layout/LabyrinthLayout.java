package fr.univlille.labyrinth.view.layout;

/**
 * Représente la disposition du labyrinthe à afficher graphiquement.
 * Cette classe contient les paramètres de mise en page tels que la taille des cellules,
 * les décalages et l'épaisseur des murs.
 *
 * @author Antonin, Angèl, Baptiste, Romain, Victor
 * @version 1.0
 * @since 1.0
 */
public class LabyrinthLayout {
    private final double cellSize;
    private final double offsetX;
    private final double offsetY;
    private final double wallThickness;

    /**
     * Constructeur de la disposition du labyrinthe.
     *
     * @param cellSize la taille des cellules
     * @param offsetX le décalage en X
     * @param offsetY le décalage en Y
     * @param wallThickness l'épaisseur des murs
     */
    public LabyrinthLayout(double cellSize, double offsetX, double offsetY, double wallThickness) {
        this.cellSize = cellSize;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.wallThickness = wallThickness;
    }

    /**
     * Retourne la taille des cellules dans le labyrinthe.
     *
     * @return la taille des cellules
     */
    public double getCellSize() {
        return cellSize;
    }

    /**
     * Retourne le décalage en X pour l'affichage du labyrinthe.
     *
     * @return le décalage en X
     */
    public double getOffsetX() {
        return offsetX;
    }

    /**
     * Retourne le décalage en Y pour l'affichage du labyrinthe.
     *
     * @return le décalage en Y
     */
    public double getOffsetY() {
        return offsetY;
    }

    /**
     * Retourne l'épaisseur des murs dans le labyrinthe.
     *
     * @return l'épaisseur des murs
     */
    public double getWallThickness() {
        return wallThickness;
    }
}
