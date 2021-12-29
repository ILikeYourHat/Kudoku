package pl.laskowski.marcin.type;

import pl.laskowski.marcin.model.Region;
import pl.laskowski.marcin.model.Sudoku;
import pl.laskowski.marcin.model.dividers.SudokuDivider;

import java.util.Set;

/**
 * Created by Marcin Laskowski.
 */

public abstract class SudokuVariant {

    private final int width;
    private final int height;

    public SudokuVariant(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public abstract int regionSize();

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    protected abstract SudokuDivider divider();

    public Set<Region> divideIntoRegions(Sudoku sudoku) {
        return divider().divideIntoRegions(sudoku);
    }

    public abstract Sudoku template();

}
