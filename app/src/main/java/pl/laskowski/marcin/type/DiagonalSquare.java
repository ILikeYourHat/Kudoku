package pl.laskowski.marcin.type;

import pl.laskowski.marcin.model.Sudoku;
import pl.laskowski.marcin.model.dividers.*;

/**
 * Created by Marcin Laskowski.
 */

public class DiagonalSquare extends SudokuVariant {

    private final int blockSize;

    public DiagonalSquare(int size) {
        super(size, size);
        this.blockSize = blockSize(size);
    }

    @Override
    public int regionSize() {
        return height();
    }

    @Override
    protected SudokuDivider divider() {
        return new ComplexDivider(
                new RowDivider(),
                new ColumnDivider(),
                new BlockDivider(blockSize, blockSize),
                new MainDiagonalDivider(),
                new AntidiagonalDivider());
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
