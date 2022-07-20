package pl.laskowski.marcin.model.dividers

import pl.laskowski.marcin.model.Board
import pl.laskowski.marcin.model.Field
import pl.laskowski.marcin.model.Region
import pl.laskowski.marcin.model.Sudoku
import java.util.HashSet

class RowDivider : SudokuDivider {

    override fun divideIntoRegions(board: Board): Set<Region> {
        val set: MutableSet<Region> = HashSet()
        for (y in 0 until board.sizeY()) {
            val fields: MutableSet<Field> = HashSet()
            for (x in 0 until board.sizeX()) {
                fields.add(board.at(x, y)!!)
            }
            set.add(Region(fields))
        }
        return set
    }
}