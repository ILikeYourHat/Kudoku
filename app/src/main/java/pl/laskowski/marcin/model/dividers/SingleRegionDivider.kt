package pl.laskowski.marcin.model.dividers

import pl.laskowski.marcin.model.Region
import pl.laskowski.marcin.model.dividers.SudokuDivider
import pl.laskowski.marcin.model.Sudoku

/**
 * Created by Marcin Laskowski.
 */
class SingleRegionDivider : SudokuDivider {
    override fun divideIntoRegions(sudoku: Sudoku): Set<Region> {
        return setOf(Region(sudoku.allFields.filterNotNull()))
    }
}