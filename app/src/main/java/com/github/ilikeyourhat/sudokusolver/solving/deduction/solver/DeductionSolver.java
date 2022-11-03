package com.github.ilikeyourhat.sudokusolver.solving.deduction.solver;

import com.github.ilikeyourhat.sudokusolver.model.Sudoku;
import com.github.ilikeyourhat.sudokusolver.model.hint.SudokuHintGrid;
import com.github.ilikeyourhat.sudokusolver.solving.SudokuSolver;
import com.github.ilikeyourhat.sudokusolver.solving.deduction.algorithm.DeductionAlgorithm;
import org.jetbrains.annotations.NotNull;
import com.github.ilikeyourhat.sudokusolver.model.SudokuType;

import java.util.List;

public abstract class DeductionSolver implements SudokuSolver {

    protected abstract List<DeductionAlgorithm.Factory> provideAlgorithms(SudokuType type);

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
        } while (gridHasChanged && !sudoku.isCompleted());
        return sudoku;
    }

}
