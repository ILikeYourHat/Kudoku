package pl.laskowski.marcin.model.dividers

import pl.laskowski.marcin.model.Board
import pl.laskowski.marcin.model.Field
import pl.laskowski.marcin.model.Region
import java.util.ArrayList
import kotlin.math.min

class AntidiagonalDivider : SudokuDivider {

    override fun divideIntoRegions(board: Board): Set<Region> {
        val limit = min(board.sizeX(), board.sizeY())
        val fields: MutableList<Field> = ArrayList()
        for (i in 0 until limit) {
            fields.add(board.at(limit - i - 1, i)!!)
        }
        val region = Region(fields)
        return setOf(region)
    }
}