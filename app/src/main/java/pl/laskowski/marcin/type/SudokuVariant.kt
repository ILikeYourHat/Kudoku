package pl.laskowski.marcin.type

import pl.laskowski.marcin.model.Region
import pl.laskowski.marcin.model.dividers.SudokuDivider
import pl.laskowski.marcin.model.Sudoku

abstract class SudokuVariant(
    val sizeX: Int,
    val sizeY: Int
) {

    abstract fun regionSize(): Int

    abstract fun divider(): SudokuDivider

    fun divideIntoRegions(sudoku: Sudoku): Set<Region> {
        return divider().divideIntoRegions(sudoku)
    }

    abstract fun template(): Sudoku
}