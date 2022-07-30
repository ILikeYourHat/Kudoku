package pl.laskowski.marcin.model.type

import pl.laskowski.marcin.model.Board
import pl.laskowski.marcin.model.Region
import pl.laskowski.marcin.type.ISudokuVariant
import pl.laskowski.marcin.type.divideByBlocks
import pl.laskowski.marcin.type.divideByColumns
import pl.laskowski.marcin.type.divideByRows

class ClassicSquare4x4 : ISudokuVariant {

    override val sizeX = 4
    override val sizeY = 4
    override val regionSize = 4

    override fun divideIntoRegions(board: Board): List<Region> {
        return board.divideByRows() +
                board.divideByColumns() +
                board.divideByBlocks(2, 2)
    }
}