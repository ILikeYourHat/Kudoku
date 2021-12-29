package pl.laskowski.marcin.type;

import pl.laskowski.marcin.model.Sudoku;

/**
 * Created by Marcin Laskowski.
 */

public class ClassicSquare extends ClassicRectangle {

    public ClassicSquare(int size) {
        super(size, size, blockSize(size), blockSize(size));
    }

    private static int blockSize(int size) {
        int blockSize = (int) Math.sqrt(size);
        if (blockSize * blockSize != size) throw new IllegalArgumentException();
        return blockSize;
    }

    @Override
    public Sudoku template() {
        return Sudoku.blank(width(), height());
    }

}
