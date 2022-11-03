package com.github.ilikeyourhat.kudoku.solving

import com.github.ilikeyourhat.kudoku.model.Sudoku

interface SudokuSolver {
    fun solve(sudoku: Sudoku): Sudoku
}