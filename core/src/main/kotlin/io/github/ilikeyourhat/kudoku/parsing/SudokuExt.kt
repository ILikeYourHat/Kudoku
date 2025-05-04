package io.github.ilikeyourhat.kudoku.parsing

import io.github.ilikeyourhat.kudoku.model.Sudoku

fun Sudoku.toSingleLineString(emptyCellIndicator: EmptyCellIndicator = EmptyCellIndicator.ZERO): String {
    return SingleLineSudokuParser().toText(this, emptyCellIndicator)
}
