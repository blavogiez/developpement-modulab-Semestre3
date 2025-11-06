package fr.univlille.labyrinth.view.labyrinth;

import fr.univlille.labyrinth.model.Observer;
import fr.univlille.labyrinth.model.maze.ObservableMaze;
import fr.univlille.labyrinth.view.GameColors;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public abstract class LabyrinthCanvasView implements Observer<ObservableMaze> {

    private Pane container;
    protected Canvas canvas;
    protected ObservableMaze currentMaze;

    protected double tailleCellule;
    protected double offsetX;
    protected double offsetY;
    protected double epaisseurMur;

    public LabyrinthCanvasView(ObservableMaze maze) {
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
    public void update(ObservableMaze maze) {
        this.currentMaze = maze;

        if (canvas.getWidth() == 0 || canvas.getHeight() == 0) {
            return;
        }

        int hauteur = maze.getHeight();
        int largeur = maze.getWidth();

        calculerDimensions(hauteur, largeur);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(GameColors.PATH.getColor());
        gc.fillRect(offsetX, offsetY, largeur * tailleCellule, hauteur * tailleCellule);

        dessinerMurs(gc, hauteur, largeur);
        dessinerElements(gc, maze, hauteur, largeur);
    }

    protected void calculerDimensions(int hauteur, int largeur) {
        double width = canvas.getWidth();
        double height = canvas.getHeight();

        double tailleCelluleX = (width - 20) / largeur;
        double tailleCelluleY = (height - 20) / hauteur;
        tailleCellule = Math.min(tailleCelluleX, tailleCelluleY);

        offsetX = (width - (largeur * tailleCellule)) / 2;
        offsetY = (height - (hauteur * tailleCellule)) / 2;

        epaisseurMur = Math.max(2, tailleCellule / 15);
    }

    protected void dessinerMurs(GraphicsContext gc, int hauteur, int largeur) {
        gc.setStroke(GameColors.WALL.getColor());
        gc.setLineWidth(epaisseurMur);

        for (int y = 0; y < hauteur; y++) {
            if (currentMaze.isWall(y, -1, y, 0)) {
                double x1 = offsetX;
                double y1 = offsetY + y * tailleCellule;
                double y2 = y1 + tailleCellule;
                gc.strokeLine(x1, y1, x1, y2);
            }
            for (int x = 0; x < largeur; x++) {
                if (currentMaze.isWall(y, x, y, x + 1)) {
                    double x1 = offsetX + (x + 1) * tailleCellule;
                    double y1 = offsetY + y * tailleCellule;
                    double y2 = y1 + tailleCellule;
                    gc.strokeLine(x1, y1, x1, y2);
                }
            }
        }

        for (int x = 0; x < largeur; x++) {
            if (currentMaze.isWall(-1, x, 0, x)) {
                double x1 = offsetX + x * tailleCellule;
                double x2 = x1 + tailleCellule;
                double y1 = offsetY;
                gc.strokeLine(x1, y1, x2, y1);
            }
            for (int y = 0; y < hauteur; y++) {
                if (currentMaze.isWall(y, x, y + 1, x)) {
                    double x1 = offsetX + x * tailleCellule;
                    double x2 = x1 + tailleCellule;
                    double y1 = offsetY + (y + 1) * tailleCellule;
                    gc.strokeLine(x1, y1, x2, y1);
                }
            }
        }
    }

    protected void dessinerJoueur(GraphicsContext gc, ObservableMaze maze) {
        dessinerMarqueur(gc, maze.getPlayerPosition().getY(), maze.getPlayerPosition().getX(), GameColors.PLAYER.getColor());
    }

    protected void dessinerSortie(GraphicsContext gc, ObservableMaze maze) {
        dessinerMarqueur(gc, maze.getExitPosition().getY(), maze.getExitPosition().getX(), GameColors.EXIT.getColor());
    }

    protected void dessinerEntree(GraphicsContext gc, ObservableMaze maze) {
        dessinerMarqueur(gc, maze.getEntryPosition().getY(), maze.getEntryPosition().getX(), Color.GREEN);
    }

    protected void dessinerMarqueur(GraphicsContext gc, int y, int x, Color couleur) {
        double tailleMarqueur = tailleCellule * 0.5;
        double marginMarqueur = (tailleCellule - tailleMarqueur) / 2;

        gc.setFill(couleur);
        gc.fillOval(
            offsetX + x * tailleCellule + marginMarqueur,
            offsetY + y * tailleCellule + marginMarqueur,
            tailleMarqueur,
            tailleMarqueur
        );
    }
    protected boolean estDansRayon(int x, int y, ObservableMaze maze, int rayon) {
        int playerX = maze.getPlayerPosition().getX();
        int playerY = maze.getPlayerPosition().getY();
        int dx = Math.abs(x - playerX);
        int dy = Math.abs(y - playerY);
        return Math.max(dx, dy) <= rayon;
    }

    protected abstract void dessinerElements(GraphicsContext gc, ObservableMaze maze, int hauteur, int largeur);

    protected abstract boolean shouldRenderCell(int y, int x, ObservableMaze maze);

    public Pane getView() {
        return container;
    }
}