package pl.laskowski.marcin.model.dividers

import pl.laskowski.marcin.model.Board
import pl.laskowski.marcin.model.Region
import java.util.HashSet

class ComplexDivider(
    private vararg val dividers: SudokuDivider
) : SudokuDivider {

    override fun divideIntoRegions(board: Board): Set<Region> {
        val regions: MutableSet<Region> = HashSet()
        for (divider in dividers) {
            regions.addAll(divider.divideIntoRegions(board))
        }
        return regions
    }
}