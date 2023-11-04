package com.github.ilikeyourhat.kudoku.rating

import com.github.ilikeyourhat.kudoku.model.Sudoku

interface SudokuRater {
    fun rate(sudoku: Sudoku): Difficulty
}
