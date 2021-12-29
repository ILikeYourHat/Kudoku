package pl.laskowski.marcin.model.dividers

import pl.laskowski.marcin.model.Field
import pl.laskowski.marcin.model.Region
import pl.laskowski.marcin.model.Sudoku
import java.util.HashSet

/**
 * Created by Marcin Laskowski.
 */
class ColumnDivider : SudokuDivider {

    override fun divideIntoRegions(sudoku: Sudoku): Set<Region> {
        val set: MutableSet<Region> = HashSet()
        for (x in 0 until sudoku.sizeX()) {
            val fields: MutableSet<Field> = HashSet()
            for (y in 0 until sudoku.sizeY()) {
                fields.add(sudoku.at(x, y)!!)
            }
            set.add(Region(fields))
        }
        return set
    }
}