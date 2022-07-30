package pl.laskowski.marcin.model.type

import pl.laskowski.marcin.model.Board
import pl.laskowski.marcin.model.Region
import pl.laskowski.marcin.type.ISudokuVariant
import pl.laskowski.marcin.type.divideByBlocks
import pl.laskowski.marcin.type.divideByColumns
import pl.laskowski.marcin.type.divideByRows

class ClassicSquare16x16 : ISudokuVariant {

    override val sizeX = 16
    override val sizeY = 16
    override val regionSize = 16

    override fun divideIntoRegions(board: Board): List<Region> {
        return board.divideByRows() +
                board.divideByColumns() +
                board.divideByBlocks(4, 4)
    }
}