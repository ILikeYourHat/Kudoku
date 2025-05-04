package io.github.ilikeyourhat.kudoku.parsing

import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.model.SudokuType

fun Sudoku.toSingleLineString(emptyCellIndicator: EmptyCellIndicator = EmptyCellIndicator.ZERO): String {
    return SingleLineSudokuParser().toText(this, emptyCellIndicator)
}

fun Sudoku.Companion.fromSingleLineString(string: String): Sudoku {
    return SingleLineSudokuParser().fromText(string)
}

fun Sudoku.Companion.fromSingleLineString(type: SudokuType, string: String): Sudoku {
    return SingleLineSudokuParser().fromText(type, string)
}

fun Sudoku.Companion.createFromString(string: String): Sudoku {
    return SudokuTextFormatParser().parseOne(string)
}
