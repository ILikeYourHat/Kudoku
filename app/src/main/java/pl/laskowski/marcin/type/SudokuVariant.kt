package pl.laskowski.marcin.type

import pl.laskowski.marcin.model.Board
import pl.laskowski.marcin.model.Region
import pl.laskowski.marcin.model.dividers.SudokuDivider

interface ISudokuVariant {
    val sizeX: Int
    val sizeY: Int
    val regionSize: Int
    fun divideIntoRegions(board: Board): List<Region>
}

abstract class SudokuVariant(
    override val sizeX: Int,
    override val sizeY: Int
) : ISudokuVariant {

    abstract fun divider(): SudokuDivider

    override fun divideIntoRegions(board: Board): List<Region> {
        return divider().divideIntoRegions(board).toList()
    }
}