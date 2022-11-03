package com.github.ilikeyourhat.sudokusolver.rating

import com.github.ilikeyourhat.sudokusolver.model.Sudoku

interface SudokuRater {
    fun rate(sudoku: Sudoku): Difficulty
}