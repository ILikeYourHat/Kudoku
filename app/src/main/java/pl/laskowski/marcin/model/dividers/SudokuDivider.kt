package pl.laskowski.marcin.model.dividers

import pl.laskowski.marcin.model.Region
import pl.laskowski.marcin.model.Sudoku

/**
 * Created by Marcin Laskowski.
 */
interface SudokuDivider {
    fun divideIntoRegions(sudoku: Sudoku): Set<Region>
}