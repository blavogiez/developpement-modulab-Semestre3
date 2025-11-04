package fr.univlille.labyrinth.view.labyrinth;

import fr.univlille.labyrinth.model.maze.Maze;
import fr.univlille.labyrinth.view.GameColors;
import fr.univlille.labyrinth.model.Observer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

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

        int lignes = maze.getHeight();
        int colonnes = maze.getWidth();

        calculerDimensions(lignes, colonnes);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(GameColors.PATH.getColor());
        gc.fillRect(offsetX, offsetY, colonnes * tailleCellule, lignes * tailleCellule);

        dessinerMurs(gc, lignes, colonnes);
        dessinerElements(gc, maze, lignes, colonnes);
    }

    protected void calculerDimensions(int lignes, int colonnes) {
        double width = canvas.getWidth();
        double height = canvas.getHeight();

        double tailleCelluleX = (width - 20) / colonnes;
        double tailleCelluleY = (height - 20) / lignes;
        tailleCellule = Math.min(tailleCelluleX, tailleCelluleY);

        offsetX = (width - (colonnes * tailleCellule)) / 2;
        offsetY = (height - (lignes * tailleCellule)) / 2;

        epaisseurMur = Math.max(2, tailleCellule / 15);
    }

    protected void dessinerMurs(GraphicsContext gc, int lignes, int colonnes) {
        gc.setStroke(GameColors.WALL.getColor());
        gc.setLineWidth(epaisseurMur);
        
        boolean[][] mursHorizontaux = currentMaze.getMurHorizontaux();
        boolean[][] mursVerticaux = currentMaze.getMurVerticaux();

        drawVerticalsWall(gc, lignes, colonnes, mursVerticaux);

        drawHorizontalsWall(gc, lignes, colonnes, mursHorizontaux);
    }

    private void drawVerticalsWall(GraphicsContext gc, int lignes, int colonnes, boolean[][] mursHorizontaux) {
        for (int colonne = 0; colonne < colonnes; colonne++) {
            double x1 = offsetX + colonne * tailleCellule;
            double x2 = x1 + tailleCellule;

            gc.strokeLine(x1, offsetY, x2, offsetY);

            for (int ligne = 0; ligne < lignes - 1; ligne++) {
                if (mursHorizontaux[ligne][colonne]) {
                    double y = offsetY + (ligne + 1) * tailleCellule;
                    gc.strokeLine(x1, y, x2, y);
                }
            }

            gc.strokeLine(x1, offsetY + lignes * tailleCellule, x2, offsetY + lignes * tailleCellule);
        }
    }

    private void drawHorizontalsWall(GraphicsContext gc, int lignes, int colonnes, boolean[][] mursVerticaux) {
        for (int ligne = 0; ligne < lignes; ligne++) {
            double y1 = offsetY + ligne * tailleCellule;
            double y2 = y1 + tailleCellule;

            gc.strokeLine(offsetX, y1, offsetX, y2);

            for (int colonne = 0; colonne < colonnes - 1; colonne++) {
                if (mursVerticaux[colonne][ligne]) {
                    double x = offsetX + (colonne + 1) * tailleCellule;
                    gc.strokeLine(x, y1, x, y2);
                }
            }

            gc.strokeLine(offsetX + colonnes * tailleCellule, y1, offsetX + colonnes * tailleCellule, y2);
        }
    }

    protected void dessinerJoueur(GraphicsContext gc, Maze maze) {
        dessinerMarqueur(gc, maze.getPlayerPosition().getX(), maze.getPlayerPosition().getY(), GameColors.PLAYER.getColor());
    }

    protected void dessinerSortie(GraphicsContext gc, Maze maze) {
        dessinerMarqueur(gc, maze.getExitPosition().getX(), maze.getExitPosition().getY(), GameColors.EXIT.getColor());
    }

    protected void dessinerEntree(GraphicsContext gc, Maze maze) {
        dessinerMarqueur(gc, maze.getEntryPosition().getX(), maze.getEntryPosition().getY(), Color.GREEN);
    }

    protected void dessinerMarqueur(GraphicsContext gc, int ligne, int colonne, Color couleur) {
        double tailleMarqueur = tailleCellule * 0.5;
        double marginMarqueur = (tailleCellule - tailleMarqueur) / 2;

        gc.setFill(couleur);
        gc.fillOval(
            offsetX + ligne * tailleCellule + marginMarqueur,
            offsetY + colonne * tailleCellule + marginMarqueur,
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

    protected abstract void dessinerElements(GraphicsContext gc, Maze maze, int lignes, int colonnes);

    protected abstract boolean shouldRenderCell(int ligne, int colonne, Maze maze);

    public Pane getView() {
        return container;
    }
}