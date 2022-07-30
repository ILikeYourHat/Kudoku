package pl.laskowski.marcin.solving.deduction.solver;

import org.jetbrains.annotations.NotNull;
import pl.laskowski.marcin.solving.SudokuSolver;
import pl.laskowski.marcin.model.Sudoku;
import pl.laskowski.marcin.model.hint.SudokuHintGrid;
import pl.laskowski.marcin.solving.deduction.algorithm.DeductionAlgorithm;
import pl.laskowski.marcin.type.ISudokuVariant;

import java.util.List;

public abstract class DeductionSolver implements SudokuSolver {

    protected abstract List<DeductionAlgorithm.Factory> provideAlgorithms(ISudokuVariant type);

    @NotNull
    @Override
    public Sudoku solve(@NotNull Sudoku sudoku) {
        sudoku = sudoku.copy();
        SudokuHintGrid sudokuHintGrid = new SudokuHintGrid(sudoku);
        return solve(sudoku, sudokuHintGrid);
    }

    private Sudoku solve(Sudoku sudoku, SudokuHintGrid sudokuHintGrid) {
        List<DeductionAlgorithm.Factory> algorithmFactories = provideAlgorithms(sudoku.getType());

        boolean gridHasChanged;
        do {
            gridHasChanged = false;
            for (DeductionAlgorithm.Factory factory : algorithmFactories) {
                if (factory.instance(sudoku.getRegions(), sudokuHintGrid).solve()){
                    gridHasChanged = true;
                    break;
                }
            }
        } while (gridHasChanged && !sudoku.isSolved());
        return sudoku;
    }

}
