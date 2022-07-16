package pl.laskowski.marcin.solving

import pl.laskowski.marcin.model.Sudoku

interface SudokuSolver {
    fun solve(sudoku: Sudoku): Sudoku
}