package fr.univlille.labyrinth.view.labyrinth.filter;

public class NormalWallFilter implements WallFilter {

    @Override
    public boolean shouldDrawVerticalWall(int y, int x, int height, int width) {
        return true;
    }

    @Override
    public boolean shouldDrawHorizontalWall(int y, int x, int height, int width) {
        return true;
    }
}
