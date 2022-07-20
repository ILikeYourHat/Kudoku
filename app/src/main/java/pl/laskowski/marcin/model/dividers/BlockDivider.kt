package pl.laskowski.marcin.model.dividers

import pl.laskowski.marcin.model.Board
import pl.laskowski.marcin.model.Field
import pl.laskowski.marcin.model.Region
import java.util.ArrayList
import java.util.HashSet

class BlockDivider(
    private val sizeX: Int,
    private val sizeY: Int
) : SudokuDivider {

    override fun divideIntoRegions(board: Board): Set<Region> {
        val regions: MutableSet<Region> = HashSet()
        var x = 0
        while (x < board.sizeX()) {
            var y = 0
            while (y < board.sizeY()) {
                regions.add(createRegionAt(board, x, y))
                y += sizeY
            }
            x += sizeX
        }
        return regions
    }

    private fun createRegionAt(board: Board, startX: Int, startY: Int): Region {
        val fields: MutableList<Field> = ArrayList()
        for (x in 0 until sizeX) {
            for (y in 0 until sizeY) {
                val field = board.at(startX + x, startY + y)
                fields.add(field!!)
            }
        }
        return Region(fields)
    }
}