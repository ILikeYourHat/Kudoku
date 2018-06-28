package pl.laskowski.marcin.solving;

import pl.laskowski.marcin.model.Sudoku;
import pl.laskowski.marcin.type.SudokuVariant;

/**
 * Created by Marcin Laskowski.
 */

public interface SudokuSolver {

    SudokuVariant getSudokuVariant();

    Sudoku solve(Sudoku sudoku);

}
