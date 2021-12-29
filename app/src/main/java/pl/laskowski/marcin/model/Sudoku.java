package pl.laskowski.marcin.model;

import pl.laskowski.marcin.model.matrix.ArrayMatrix;
import pl.laskowski.marcin.model.matrix.Matrix;

import java.util.*;

/**
 * Created by Marcin Laskowski.
 */

public class Sudoku implements Iterable<Field> {

    private final Matrix<Field> fields;

    public static Sudoku blank(int sizeX, int sizeY) {
        Sudoku sudoku = new Sudoku(sizeX, sizeY);
        sudoku.fields.forEach((x, y) -> {
            Field f = new Field(x, y);
            sudoku.fields.put(f, x, y);
        });
        return sudoku;
    }

    public Sudoku(int sizeX, int sizeY) {
        if (sizeX < 0 || sizeY < 0) throw new IllegalArgumentException();
        fields = new ArrayMatrix<>(sizeX, sizeY);
    }

    public Sudoku(int sizeX, int sizeY, Integer[] values) {
        this(sizeX, sizeY);
        if (sizeX * sizeY != values.length)
            throw new IllegalArgumentException("Not enough data");
        fields.forEach((x, y) -> {
            Integer value = values[y * sizeX + x];
            if (value != null) {
                Field field = new Field(x,y);
                field.set(value);
                fields.put(field, x, y);
            }
        });
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('|');
        for (List<Field> row : rows()) {
            for (Field field : row) {
                if (field == null) {
                    sb.append('#');
                } else if (field.isEmpty()) {
                    sb.append('_');
                } else {
                    sb.append(field.value());
                }
                sb.append(',');
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append('|');
        }
        return sb.toString();
    }

    public List<List<Field>> rows() {
        List<List<Field>> rows = new ArrayList<>();
        fields.forEachY(y -> rows.add(row(y)));
        return rows;
    }


    public List<List<Field>> columns() {
        List<List<Field>> columns = new ArrayList<>();
        fields.forEachX(x -> columns.add(column(x)));
        return columns;
    }

    public List<Field> row(int y) {
        List<Field> row = new ArrayList<>();
        fields.forEachX(x ->
                row.add(at(x, y))
        );
        return row;
    }


    public List<Field> column(int x) {
        List<Field> column = new ArrayList<>();
        fields.forEachY(y ->
                column.add(at(x, y))
        );
        return column;
    }

    public Sudoku copy() {
        Sudoku copy = new Sudoku(sizeX(), sizeY());
        fields.forEach((x, y) -> {
            Field field = fields.get(x, y);
            if (field != null) {
                copy.fields.put(field.copy(), x, y);
            } else {
                copy.fields.put(null, x, y);
            }
        });
        return copy;
    }

    public Sudoku copyWithIndexMapping(Point.Mapper mapper) {
        Sudoku copy = new Sudoku(sizeX(), sizeY());
        return mapValues(copy, mapper);
    }

    public Sudoku copyRotatedWithIndexMapping(Point.Mapper mapper) {
        Sudoku copy = new Sudoku(sizeY(), sizeX());
        return mapValues(copy, mapper);
    }

    private Sudoku mapValues(Sudoku target, Point.Mapper mapper) {
        fields.forEach((x, y) -> {
            Point point = mapper.map(new Point(x, y));
            Field field = at(x, y);
            if (field != null) {
                Field mappedField = new Field(point.x(), point.y());
                mappedField.set(field.value());
                target.fields.put(mappedField, point.x(), point.y());
            }
        });
        return target;
    }

    public Sudoku subSudoku(int startX, int startY, int endX, int endY) {
        Sudoku sudokuFragment = new Sudoku(endX - startX, endY - startY);
        fields.forEach((x, y) -> {
            if (startX <= x && endX > x && startY <= y && endY > y) {
                Field field = at(x, y);
                sudokuFragment.fields.put(field, x - startX, y - startY);
            }
        });
        return sudokuFragment;
    }

    public int sizeX() {
        return fields.sizeX();
    }

    public int sizeY() {
        return fields.sizeY();
    }

    public Field at(int x, int y) {
        return fields.get(x, y);
    }

    public Field at(Point p) {
        return fields.get(p.x(), p.y());
    }

    @Override
    public ListIterator<Field> iterator() {
        return getAllFields().listIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sudoku sudoku = (Sudoku) o;

        return fields.equals(sudoku.fields);
    }

    @Override
    public int hashCode() {
        return fields.hashCode();
    }

    public boolean isSolved() {
        for (Field field : this) {
            if (field.isEmpty()) return false;
        }
        return true;
    }

    public List<Field> getAllFields() {
        List<Field> allFields = new ArrayList<>();
        fields.forEach((x, y) -> {
            Field field = fields.get(x, y);
            if (field != null) {
                allFields.add(field);
            }
        });
        return allFields;
    }

    public Field getFirstEmptyField() {
        for (Field field : this) {
            if (field.isEmpty()){
                return field;
            }
        }
        return null;
    }

    public void append(Sudoku other, Point anchor) {
        other.fields.forEach((x, y) -> {
            int destinationX = anchor.x() + x;
            int destinationY = anchor.y() + y;
            Field source = other.fields.get(x, y);
            if (source == null) {
                this.fields.put(null, destinationX, destinationY);
            } else {
                Field destination = this.fields.get(destinationX, destinationY);
                if (destination == null){
                    destination = new Field(destinationX, destinationY);
                }
                destination.set(source.value());
                this.fields.put(destination, destinationX,destinationY);
            }
        });
    }



}
