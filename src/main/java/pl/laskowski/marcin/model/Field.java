package pl.laskowski.marcin.model;

/**
 * Created by Marcin Laskowski.
 */

public class Field {

    private static final int EMPTY_FIELD = 0;

    private final Point position;
    private int value = EMPTY_FIELD;

    public Field(int x, int y) {
        this.position = new Point(x, y);
    }

    public int value() {
        return value;
    }

    public void set(int value) {
        this.value = value;
    }

    public boolean isEmpty() {
        return value == EMPTY_FIELD;
    }

    public int getX() {
        return position.x();
    }

    public int getY() {
        return position.y();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Field field = (Field) o;

        if (value != field.value) return false;
        return position.equals(field.position);
    }

    @Override
    public int hashCode() {
        int result = position.hashCode();
        result = 31 * result + value;
        return result;
    }

    public void clear() {
        value = EMPTY_FIELD;
    }

    public Field copy() {
        Field field = new Field(position.x(), position.y());
        field.value = this.value;
        return field;
    }

    public boolean haveSamePosition(Field field) {
        return this.position.equals(field.position);
    }

    public Point position() {
        return position;
    }
}
