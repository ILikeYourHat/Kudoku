package pl.laskowski.marcin.type

import pl.laskowski.marcin.model.Region
import pl.laskowski.marcin.model.dividers.SudokuDivider
import pl.laskowski.marcin.model.Sudoku

/**
 * Created by Marcin Laskowski.
 */
abstract class SudokuVariant(
    private val width: Int,
    private val height: Int
) {

    abstract fun regionSize(): Int

    fun width(): Int {
        return width
    }

    fun sizeX(): Int {
        return width
    }

    fun height(): Int {
        return height
    }

    fun sizeY(): Int {
        return height
    }

    abstract fun divider(): SudokuDivider

    fun divideIntoRegions(sudoku: Sudoku): Set<Region> {
        return divider().divideIntoRegions(sudoku)
    }

    abstract fun template(): Sudoku
}