package fr.univlille.labyrinth.model.maze;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void should_create_position_with_coordinates() {
        Position position = new Position(3, 4);

        assertEquals(3, position.getX());
        assertEquals(4, position.getY());
    }

    @Test
    void should_Be_equal_when_same_coordinates() {
        Position pos1 = new Position(3, 4);
        Position pos2 = new Position(3, 4);

        assertEquals(pos1, pos2);
    }

    @Test
    void should_not_be_equal_when_different_coordinates() {
        Position pos1 = new Position(3, 4);
        Position pos2 = new Position(4, 5);

        assertNotEquals(pos1, pos2);
    }

    @Test
    void should_have_same_hash_code_when_equal() {
        Position pos1 = new Position(3, 4);
        Position pos2 = new Position(3, 4);

        assertEquals(pos1.hashCode(), pos2.hashCode());
    }

    @Test
    void should_update_X_coordinate() {
        Position position = new Position(0, 0);

        position.setX(5);

        assertEquals(5, position.getX());
    }

    @Test
    void should_update_Y_coordinate() {
        Position position = new Position(0, 0);

        position.setY(7);

        assertEquals(7, position.getY());
    }

    @Test
    void should_generate_a_random_position_within_range_test(){
        for (int heigth = 10; heigth<15;heigth++){
            for (int width = 10; width<15; width++){
                boolean[][] wasGenerated = new boolean[heigth][width];
                int i = 0;
                do {
                    Position position = Position.getRandomPosition(heigth,width);
                    wasGenerated[position.getY()][position.getX()]=true;
                    i++;
                } while (i<10000 && !checkAllAreTrue(wasGenerated));

                assertTrue(checkAllAreTrue(wasGenerated));
            }
        }
    }

    @Test
    void should_return_another_position_with_updated_parameters(){
        Position pos = new Position(1,1);
        Position pos2 = pos.add(0,0);
        Position pos3 = pos.add(2,0);
        assertEquals(pos,pos2);
        assertNotSame(pos, pos2);
        assertEquals(pos3.getX(), pos.getX()+2);
        assertEquals(pos3.getY(), pos.getY());
    }

    @Test
    void should_return_the_minimum_position_between_two(){
        Position pos = new Position(0,0);
        Position pos2 = new Position(1,0);
        Position pos3 = new Position(0,1);
        Position pos4 = new Position(1,1);

        assertEquals(pos.min(pos2),pos);
        assertEquals(pos.min(pos3),pos);
        assertEquals(pos2.min(pos4),pos2);
        assertEquals(pos3.min(pos4),pos3);

        assertEquals(pos2.min(pos),pos);
        assertEquals(pos3.min(pos),pos);
        assertEquals(pos4.min(pos2),pos2);
        assertEquals(pos4.min(pos3),pos3);
    }

    @Test
    void should_copy_the_position(){
        Position pos = new Position(1,1);
        Position pos2 = pos.copy();
        assertEquals(pos, pos2);
        assertNotSame(pos, pos2);
    }

    /** 
     * @param tab
     * @return boolean
     */
    private boolean checkAllAreTrue(boolean[][] tab){
        for (boolean[] booleans : tab) {
            for (boolean aBoolean : booleans) {
                if (!aBoolean) return false;
            }
        }
        return true;
    }
}
