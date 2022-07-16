package pl.laskowski.marcin.solving

import pl.laskowski.marcin.model.Sudoku
import pl.laskowski.marcin.type.SudokuVariant

interface SudokuSolver {
    val sudokuVariant: SudokuVariant
    fun solve(sudoku: Sudoku): Sudoku
}