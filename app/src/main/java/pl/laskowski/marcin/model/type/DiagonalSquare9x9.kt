package pl.laskowski.marcin.model.type

import pl.laskowski.marcin.model.Board
import pl.laskowski.marcin.model.Region
import pl.laskowski.marcin.type.*

class DiagonalSquare9x9 : ISudokuVariant {

    override val sizeX = 9
    override val sizeY = 9
    override val regionSize = 9

    override fun divideIntoRegions(board: Board): List<Region> {
        return board.divideByRows() +
                board.divideByColumns() +
                board.divideByBlocks(3, 3) +
                board.primaryDiagonal() +
                board.antiDiagonal()
    }
}