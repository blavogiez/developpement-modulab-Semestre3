package fr.univlille.labyrinth.model.cellAlgorithm;

import fr.univlille.labyrinth.model.Direction;
import fr.univlille.labyrinth.model.Position;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;
import java.util.Map;

public class Cell {
    Position position;
    Map<Direction,Boolean> walls;
    Token token;
    CellType cellType;

    public Cell(Position position, boolean up, boolean right, boolean left, boolean down, Token token, CellType cellType){
        this.position=position;
        walls = new HashMap<>();
        walls.put(Direction.DOWN,down);
        walls.put(Direction.UP,up);
        walls.put(Direction.LEFT,left);
        walls.put(Direction.RIGHT,right);
        this.token=token;
        this.cellType=cellType;
    }

    /** 
     * @return Rectangle
     */
    public Rectangle generateRectangle(){
        Rectangle rectangle = new Rectangle();
        rectangle.setFill(cellType.getPaint());
        rectangle.setAccessibleText(token.getVisual());
        //À modifier afin de renvoyer un widget javafx pouvant avoir une couleur, un text en son centre, et des lignes fines pour les murs autour.
        //Vous avez l'idée.
        return null;
    }

    /** 
     * @param cellType
     */
    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    /** 
     * @param token
     */
    public void setToken(Token token) {
        this.token = token;
    }

    /** 
     * @return CellType
     */
    public CellType getCellType() {
        return cellType;
    }

    /** 
     * @return Token
     */
    public Token getToken() {
        return token;
    }
}
