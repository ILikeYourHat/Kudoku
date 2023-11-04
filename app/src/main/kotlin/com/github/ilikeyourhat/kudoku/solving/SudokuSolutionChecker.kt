package com.github.ilikeyourhat.kudoku.solving

import com.github.ilikeyourhat.kudoku.model.Sudoku

interface SudokuSolutionChecker {
    fun checkSolutions(sudoku: Sudoku): SolutionCount
}
