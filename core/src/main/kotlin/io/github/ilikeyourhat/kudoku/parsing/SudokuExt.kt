package io.github.ilikeyourhat.kudoku.parsing

import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.model.SudokuType

fun Sudoku.toSingleLineString(emptyCellIndicator: EmptyCellIndicator = EmptyCellIndicator.DEFAULT): String {
    return SingleLineSudokuParser().toText(this, emptyCellIndicator)
}

fun Sudoku.toGraphicFormatString(): String {
    return GraphicFormatParser().toText(this)
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
