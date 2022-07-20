package pl.laskowski.marcin.model.dividers

import pl.laskowski.marcin.model.Board
import pl.laskowski.marcin.model.Field
import pl.laskowski.marcin.model.Region
import java.util.HashSet

class ColumnDivider : SudokuDivider {

    override fun divideIntoRegions(board: Board): Set<Region> {
        val set: MutableSet<Region> = HashSet()
        for (x in 0 until board.sizeX()) {
            val fields: MutableSet<Field> = HashSet()
            for (y in 0 until board.sizeY()) {
                fields.add(board.at(x, y)!!)
            }
            set.add(Region(fields))
        }
        return set
    }
}