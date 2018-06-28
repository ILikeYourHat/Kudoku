package pl.laskowski.marcin.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Marcin Laskowski.
 */

public class FieldTest {

    @Test
    public void createEmptyField() {
        Field field = new Field(0, 0);

        assertTrue(field.isEmpty());
        assertEquals(0, field.value());
    }

    @Test
    public void setNewValueToField() {
        Field field = new Field(0, 0);
        assertTrue(field.isEmpty());
        field.set(7);
        assertEquals(7, field.value());
    }

    @Test
    public void createFieldWithCoords() {
        int x = 3;
        int y = 7;
        Field field = new Field(x, y);

        assertEquals(x, field.getX());
        assertEquals(y, field.getY());
    }

    @Test
    public void shouldBeEqualsIfCoordsAreEquals() {
        Field fieldBeingTested = new Field(1, 2);
        Field sameField = new Field(1, 2);
        Field differentField = new Field(3, 5);

        assertEquals(sameField, fieldBeingTested);
        assertNotEquals(differentField, fieldBeingTested);
    }

}
