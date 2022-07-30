package pl.laskowski.marcin.model.type

import pl.laskowski.marcin.model.Board
import pl.laskowski.marcin.model.Region
import pl.laskowski.marcin.type.ISudokuVariant
import pl.laskowski.marcin.type.allFields

class ClassicSquare1x1 : ISudokuVariant {

    override val sizeX = 1
    override val sizeY = 1
    override val regionSize = 1

    override fun divideIntoRegions(board: Board): List<Region> {
        return board.allFields()
    }
}