package pl.laskowski.marcin.model.dividers

import pl.laskowski.marcin.model.Board
import pl.laskowski.marcin.model.Region

interface SudokuDivider {
    fun divideIntoRegions(board: Board): Set<Region>
}