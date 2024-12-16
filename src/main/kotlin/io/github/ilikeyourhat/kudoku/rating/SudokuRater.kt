package io.github.ilikeyourhat.kudoku.rating

import io.github.ilikeyourhat.kudoku.model.Sudoku

interface SudokuRater {
    fun rate(sudoku: Sudoku): Difficulty
}
