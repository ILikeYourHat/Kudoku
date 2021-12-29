package pl.laskowski.marcin.creating;

import pl.laskowski.marcin.model.Point;
import pl.laskowski.marcin.model.Sudoku;

/**
 * Created by Marcin Laskowski.
 */

public class SudokuTransformations {

    public Sudoku rotateLeft(Sudoku origin) {
        if (origin == null) return null;
        return origin.copyRotatedWithIndexMapping(p -> {
            int x = p.y();
            int y = indexFromEnd(origin.sizeX(), p.x());
            return new Point(x, y);
        });
    }

    public Sudoku rotateRight(Sudoku origin) {
        if (origin == null) return null;
        return origin.copyRotatedWithIndexMapping(p -> {
            int x = indexFromEnd(origin.sizeY(), p.y());
            int y = p.x();
            return new Point(x, y);
        });
    }

    public Sudoku rotate180(Sudoku origin) {
        if (origin == null) return null;
        return origin.copyWithIndexMapping(p -> {
            int x = indexFromEnd(origin.sizeX(), p.x());
            int y = indexFromEnd(origin.sizeY(), p.y());
            return new Point(x, y);
        });
    }

    public Sudoku mirrorByYAxis(Sudoku origin) {
        if (origin == null) return null;
        return origin.copyWithIndexMapping(p -> {
            int x = indexFromEnd(origin.sizeX(), p.x());
            int y = p.y();
            return new Point(x, y);
        });
    }

    public Sudoku mirrorByXAxis(Sudoku origin) {
        if (origin == null) return null;
        return origin.copyWithIndexMapping(p -> {
            int x = p.x();
            int y = indexFromEnd(origin.sizeY(), p.y());
            return new Point(x, y);
        });
    }

    public Sudoku mirrorByFirstDiagonal(Sudoku origin) {
        if (origin == null) return null;
        return origin.copyRotatedWithIndexMapping(p -> {
            int x = p.y();
            int y = p.x();
            return new Point(x, y);
        });
    }

    public Sudoku mirrorBySecondDiagonal(Sudoku origin) {
        if (origin == null) return null;
        return origin.copyRotatedWithIndexMapping(p -> {
            int x = indexFromEnd(origin.sizeY(), p.y());
            int y = indexFromEnd(origin.sizeX(), p.x());
            return new Point(x, y);
        });
    }

    public Sudoku swapRow(Sudoku origin, int y1, int y2) {
        if (origin == null) return null;
        return origin.copyWithIndexMapping(p -> {
            int x = p.x();
            int y = swapIndex(p.y(), y1, y2);
            return new Point(x, y);
        });
    }

    public Sudoku swapRows(Sudoku origin, int y1, int y2, int size) {
        if (origin == null) return null;
        return origin.copyWithIndexMapping(p -> {
            int x = p.x();
            int y = swapIndex(p.y(), y1, y2, size);
            return new Point(x, y);
        });
    }

    public Sudoku swapColumn(Sudoku origin, int x1, int x2) {
        if (origin == null) return null;
        return origin.copyWithIndexMapping(p -> {
            int x = swapIndex(p.x(), x1, x2);
            int y = p.y();
            return new Point(x, y);
        });
    }

    public Sudoku swapColumns(Sudoku origin, int x1, int x2, int size) {
        if (origin == null) return null;
        return origin.copyWithIndexMapping(p -> {
            int x = swapIndex(p.x(), x1, x2, size);
            int y = p.y();
            return new Point(x, y);
        });
    }

    private int indexFromEnd(int size, int index) {
        return size - 1 - index;
    }

    private int swapIndex(int current, int index1, int index2) {
        if (current == index1) {
            return index2;
        } else if (current == index2){
            return index1;
        } else {
            return current;
        }
    }

    private int swapIndex(int current, int index1, int index2, int size) {
        if (current >= index1 && current < index1 + size) {
            int padding = current - index1;
            return index2 + padding;
        } else if (current >= index2 && current < index2 + size) {
            int padding = current - index2;
            return index1 + padding;
        } else {
            return current;
        }
    }

}
