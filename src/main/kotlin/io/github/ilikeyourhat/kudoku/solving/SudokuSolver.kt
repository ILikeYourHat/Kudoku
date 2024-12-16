package io.github.ilikeyourhat.kudoku.solving

import io.github.ilikeyourhat.kudoku.model.Sudoku

interface SudokuSolver {
    fun solve(sudoku: Sudoku): Sudoku
}
