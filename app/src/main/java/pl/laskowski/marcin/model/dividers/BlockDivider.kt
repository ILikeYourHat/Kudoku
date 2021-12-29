package pl.laskowski.marcin.model.dividers

import pl.laskowski.marcin.model.Field
import pl.laskowski.marcin.model.Region
import pl.laskowski.marcin.model.Sudoku
import java.util.ArrayList
import java.util.HashSet

/**
 * Created by Marcin Laskowski.
 */
class BlockDivider(
    private val sizeX: Int,
    private val sizeY: Int
) : SudokuDivider {

    override fun divideIntoRegions(sudoku: Sudoku): Set<Region> {
        val regions: MutableSet<Region> = HashSet()
        var x = 0
        while (x < sudoku.sizeX()) {
            var y = 0
            while (y < sudoku.sizeY()) {
                regions.add(createRegionAt(sudoku, x, y))
                y += sizeY
            }
            x += sizeX
        }
        return regions
    }

    private fun createRegionAt(sudoku: Sudoku, startX: Int, startY: Int): Region {
        val fields: MutableList<Field> = ArrayList()
        for (x in 0 until sizeX) {
            for (y in 0 until sizeY) {
                val field = sudoku.at(startX + x, startY + y)
                fields.add(field)
            }
        }
        return Region(fields)
    }
}