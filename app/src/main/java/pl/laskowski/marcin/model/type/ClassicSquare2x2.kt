package pl.laskowski.marcin.model.type

import pl.laskowski.marcin.model.Board
import pl.laskowski.marcin.model.Region
import pl.laskowski.marcin.type.ISudokuVariant
import pl.laskowski.marcin.type.divideByColumns
import pl.laskowski.marcin.type.divideByRows

class ClassicSquare2x2 : ISudokuVariant {

    override val sizeX = 2
    override val sizeY = 2
    override val regionSize = 2

    override fun divideIntoRegions(board: Board): List<Region> {
        return board.divideByRows() +
                board.divideByColumns()
    }
}