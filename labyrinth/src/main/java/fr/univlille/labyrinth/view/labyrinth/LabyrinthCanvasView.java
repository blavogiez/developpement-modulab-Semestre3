package fr.univlille.labyrinth.view.labyrinth;

import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.view.GameColors;
import fr.univlille.labyrinth.model.Observer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

// A adapter pour model
// Should render cell à bosser ?

/*
 * Classe abstraite mutualisant les comportements communs aux vues de labyrinthe
 * Permet de créer une nouvelle vue avec un code minimal
 * Permet d'injecter une dépendance abstraite et non concrète
 * 
 * Permet le O et le D de S O L I D
 */
public abstract class LabyrinthCanvasView implements Observer<Maze> {

    private Pane container;
    protected Canvas canvas;
    protected Maze currentMaze;

    protected double tailleCellule;
    protected double offsetX;
    protected double offsetY;
    protected double epaisseurMur;

    public LabyrinthCanvasView(Maze maze) {
        this.currentMaze = maze;

        container = new Pane();
        canvas = new Canvas(700, 700);
        container.getChildren().add(canvas);

        container.setMinSize(0, 0);
        container.setMaxSize(canvas.getWidth(), canvas.getHeight());
        container.setPrefSize(canvas.getWidth(), canvas.getHeight());

        canvas.widthProperty().bind(container.widthProperty());
        canvas.heightProperty().bind(container.heightProperty());

        container.widthProperty().addListener((obs, oldVal, newVal) -> update(currentMaze));
        container.heightProperty().addListener((obs, oldVal, newVal) -> update(currentMaze));

        update(maze);
    }

    @Override
    public void update(Maze maze) {
        this.currentMaze = maze;

        if (canvas.getWidth() == 0 || canvas.getHeight() == 0) {
            return;
        }

        int lignes = maze.getWidth();
        int colonnes = maze.getHeight();

        calculerDimensions(lignes, colonnes);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(GameColors.PATH.getColor());
        gc.fillRect(offsetX, offsetY, colonnes * tailleCellule, lignes * tailleCellule);

        dessinerMurs(gc, lignes, colonnes);
        dessinerElements(gc, maze, lignes, colonnes);
    }

    // final numbers à mettre
    protected void calculerDimensions(int lignes, int colonnes) {
        double width = canvas.getWidth();
        double height = canvas.getHeight();

        double tailleCelluleX = (width - 20) / colonnes;
        double tailleCelluleY = (height - 20) / lignes;
        tailleCellule = Math.min(tailleCelluleX, tailleCelluleY);

        offsetX = (width - (colonnes * tailleCellule)) / 2;
        offsetY = (height - (lignes * tailleCellule)) / 2;

        // donc un minimum
        epaisseurMur = Math.max(2, tailleCellule / 15);
    }

    protected void dessinerMurs(GraphicsContext gc, int lignes, int colonnes) {
        gc.setStroke(GameColors.WALL.getColor());
        gc.setLineWidth(epaisseurMur);

        gc.strokeRect(offsetX, offsetY, colonnes * tailleCellule, lignes * tailleCellule);

        boolean[][] mursHorizontaux = currentMaze.getMurHorizontaux();
        boolean[][] mursVerticaux = currentMaze.getMurVerticaux();

        for (int i = 0; i < mursHorizontaux.length; i++) {
            for (int j = 0; j < mursHorizontaux[0].length; j++) {
                if (!shouldRenderCell(i, j, currentMaze)) {
                    continue;
                }

                double x = offsetX + j * tailleCellule;
                double y = offsetY + i * tailleCellule;

                if (mursHorizontaux[i][j]) {
                    gc.strokeLine(x, y + tailleCellule, x + tailleCellule, y + tailleCellule);
                }

            }
        }

        for (int i = 0; i < mursVerticaux.length; i++) {
            for (int j = 0; j < mursVerticaux[0].length; j++) {
                if (!shouldRenderCell(i, j, currentMaze)) {
                    continue;
                }

                double x = offsetX + j * tailleCellule;
                double y = offsetY + i * tailleCellule;

                if (mursVerticaux[i][j]) {
                    gc.strokeLine(x + tailleCellule, y, x + tailleCellule, y + tailleCellule);
                }
            }
        }
    }

    /*
     * wrappers successifs de "dessinerMarqueur"
     */
    protected void dessinerJoueur(GraphicsContext gc, Maze maze) {
        dessinerMarqueur(gc, maze.getPlayerPosition().getX(), maze.getPlayerPosition().getY(), GameColors.PLAYER.getColor());
    }

    protected void dessinerSortie(GraphicsContext gc, Maze maze) {
        dessinerMarqueur(gc, maze.getExitPosition().getX(), maze.getExitPosition().getY(), GameColors.EXIT.getColor());
    }

    // en fait osef d'elle
    protected void dessinerEntree(GraphicsContext gc, Maze maze) {
        dessinerMarqueur(gc, maze.getEntryPosition().getX(), maze.getEntryPosition().getY(), Color.GREEN);
    }

    /*
     * Dessine une forme ronde d'une couleur donnée sur une case (utile pour joueur / sortie)
     */
    protected void dessinerMarqueur(GraphicsContext gc, int ligne, int colonne, Color couleur) {
        double tailleMarqueur = tailleCellule * 0.5;
        double marginMarqueur = (tailleCellule - tailleMarqueur) / 2;

        gc.setFill(couleur);
        gc.fillOval(
            offsetX + colonne * tailleCellule + marginMarqueur,
            offsetY + ligne * tailleCellule + marginMarqueur,
            tailleMarqueur,
            tailleMarqueur
        );
    }

    protected boolean estDansRayon(int ligne, int colonne, Maze maze, int rayon) {
        int playerX = maze.getPlayerPosition().getX();
        int playerY = maze.getPlayerPosition().getY();
        int dx = Math.abs(ligne - playerX);
        int dy = Math.abs(colonne - playerY);
        return Math.max(dx, dy) <= rayon;
    }

    // quels élements le labyrinthe implémenté decide de dessiner ? 
    protected abstract void dessinerElements(GraphicsContext gc, Maze maze, int lignes, int colonnes);

    // faut il dessiner la cellule à cet endroit ? peut etre utile
    // Attention à ça pour la Ségrégation d'interfaces 
    protected abstract boolean shouldRenderCell(int ligne, int colonne, Maze maze);

    public Pane getView() {
        return container;
    }
}
