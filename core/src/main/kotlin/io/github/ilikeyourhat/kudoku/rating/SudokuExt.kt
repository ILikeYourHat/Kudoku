package io.github.ilikeyourhat.kudoku.rating

import io.github.ilikeyourhat.kudoku.model.Sudoku

fun Sudoku.Companion.defaultRater(): SudokuRater {
    return DeductionBasedRater()
}
