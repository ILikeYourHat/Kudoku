package io.github.ilikeyourhat.kudoku.parsing

import io.github.ilikeyourhat.kudoku.model.Sudoku

fun Sudoku.toSingleLineString(emptyFieldIndicator: EmptyFieldIndicator = EmptyFieldIndicator.ZERO): String {
    return SingleLineSudokuParser().toText(this, emptyFieldIndicator)
}
