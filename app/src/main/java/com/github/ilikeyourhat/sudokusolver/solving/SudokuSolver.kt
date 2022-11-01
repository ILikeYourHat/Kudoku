package com.github.ilikeyourhat.sudokusolver.solving

import com.github.ilikeyourhat.sudokusolver.model.Sudoku

interface SudokuSolver {
    fun solve(sudoku: Sudoku): Sudoku
}