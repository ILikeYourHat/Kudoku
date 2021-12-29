package pl.laskowski.marcin.model.dividers

import pl.laskowski.marcin.model.Field
import pl.laskowski.marcin.model.Region
import pl.laskowski.marcin.model.Sudoku
import java.util.ArrayList
import kotlin.math.min

/**
 * Created by Marcin Laskowski.
 */
class MainDiagonalDivider : SudokuDivider {

    override fun divideIntoRegions(sudoku: Sudoku): Set<Region> {
        val limit = min(sudoku.sizeX(), sudoku.sizeY())
        val fields: MutableList<Field> = ArrayList()
        for (i in 0 until limit) {
            fields.add(sudoku.at(i, i)!!)
        }
        val region = Region(fields)
        return setOf(region)
    }
}