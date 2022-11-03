package com.github.ilikeyourhat.sudokusolver.solving

import com.github.ilikeyourhat.sudokusolver.model.Sudoku

interface SudokuSolutionChecker {
    fun checkSolutions(sudoku: Sudoku): SolutionCount
}