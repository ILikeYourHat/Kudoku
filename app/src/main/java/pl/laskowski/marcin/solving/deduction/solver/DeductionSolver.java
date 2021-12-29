package pl.laskowski.marcin.solving.deduction.solver;

import pl.laskowski.marcin.model.Region;
import pl.laskowski.marcin.solving.SudokuSolver;
import pl.laskowski.marcin.model.Sudoku;
import pl.laskowski.marcin.model.SudokuHintGrid;
import pl.laskowski.marcin.solving.deduction.algorithm.DeductionAlgorithm;
import pl.laskowski.marcin.type.SudokuVariant;

import java.util.List;
import java.util.Set;

/**
 * Created by Marcin Laskowski.
 */

public abstract class DeductionSolver implements SudokuSolver {

    private final List<DeductionAlgorithm.Factory> algorithmFactories;
    protected final SudokuVariant sudokuVariant;

    protected DeductionSolver(SudokuVariant sudokuVariant) {
        this.sudokuVariant = sudokuVariant;
        this.algorithmFactories = provideAlgorithms();
    }

    protected abstract List<DeductionAlgorithm.Factory> provideAlgorithms();

    @Override
    public SudokuVariant getSudokuVariant() {
        return sudokuVariant;
    }

    @Override
    public Sudoku solve(Sudoku sudoku) {
        sudoku = sudoku.copy();
        SudokuHintGrid sudokuHintGrid = new SudokuHintGrid(sudoku, sudokuVariant);
        Set<Region> regions = sudokuVariant.divideIntoRegions(sudoku);
        return solve(sudoku, regions, sudokuHintGrid);
    }

    private Sudoku solve(Sudoku sudoku, Set<Region> regions, SudokuHintGrid sudokuHintGrid) {
        boolean gridHasChanged;
        do {
            gridHasChanged = false;
            for (DeductionAlgorithm.Factory factory : algorithmFactories) {
                if (factory.instance(regions, sudokuHintGrid).solve()){
                    gridHasChanged = true;
                    break;
                }
            }
        } while (gridHasChanged && !sudoku.isSolved());
        return sudoku;
    }

}
