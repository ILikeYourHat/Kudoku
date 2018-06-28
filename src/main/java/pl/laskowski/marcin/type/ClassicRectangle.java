package pl.laskowski.marcin.type;

import pl.laskowski.marcin.model.Sudoku;
import pl.laskowski.marcin.model.dividers.*;

/**
 * Created by Marcin Laskowski.
 */

public class ClassicRectangle extends SudokuVariant {

    private final int blockWidth;
    private final int blockHeight;

    public ClassicRectangle(int width, int height, int blockWidth, int blockHeight) {
        super(width, height);
        this.blockWidth = blockWidth;
        this.blockHeight = blockHeight;
    }

    @Override
    public int regionSize() {
        return blockWidth * blockHeight;
    }

    @Override
    protected SudokuDivider divider() {
        return new ComplexDivider(
                new RowDivider(),
                new ColumnDivider(),
                new BlockDivider(blockWidth, blockHeight));
    }

    @Override
    public Sudoku template() {
        return Sudoku.blank(width(), height());
    }

    public int getBlockHeight() {
        return blockHeight;
    }

    public int getBlockWidth() {
        return blockWidth;
    }


}
