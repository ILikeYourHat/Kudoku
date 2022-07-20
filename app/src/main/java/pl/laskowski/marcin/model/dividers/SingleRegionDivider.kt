package pl.laskowski.marcin.model.dividers

import pl.laskowski.marcin.model.Board
import pl.laskowski.marcin.model.Region

class SingleRegionDivider : SudokuDivider {
    override fun divideIntoRegions(board: Board): Set<Region> {
        return setOf(Region(board.fields().filterNotNull()))
    }
}