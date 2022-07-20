package pl.laskowski.marcin.type

import pl.laskowski.marcin.model.dividers.SudokuDivider

abstract class SudokuVariant(
    val sizeX: Int,
    val sizeY: Int
) {
    abstract fun regionSize(): Int
    abstract fun divider(): SudokuDivider
}