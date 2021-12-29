package pl.laskowski.marcin.model.dividers

import pl.laskowski.marcin.model.Region
import pl.laskowski.marcin.model.Sudoku
import java.util.HashSet

/**
 * Created by Marcin Laskowski.
 */
class ComplexDivider(
    private vararg val dividers: SudokuDivider
) : SudokuDivider {

    override fun divideIntoRegions(sudoku: Sudoku): Set<Region> {
        val regions: MutableSet<Region> = HashSet()
        for (divider in dividers) {
            regions.addAll(divider.divideIntoRegions(sudoku))
        }
        return regions
    }
}