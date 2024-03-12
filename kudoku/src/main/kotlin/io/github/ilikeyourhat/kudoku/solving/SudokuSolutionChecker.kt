package io.github.ilikeyourhat.kudoku.solving

import io.github.ilikeyourhat.kudoku.model.Sudoku

interface SudokuSolutionChecker {
    fun checkSolutions(sudoku: Sudoku): SolutionCount
}
