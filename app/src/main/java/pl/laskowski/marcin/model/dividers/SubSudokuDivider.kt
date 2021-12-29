package pl.laskowski.marcin.model.dividers

import pl.laskowski.marcin.model.Point
import pl.laskowski.marcin.model.Region
import pl.laskowski.marcin.model.Sudoku
import java.util.HashSet

/**
 * Created by Marcin Laskowski.
 */
class SubSudokuDivider(
    private val divider: SudokuDivider,
    private vararg val areas: Area
) : SudokuDivider {

    override fun divideIntoRegions(sudoku: Sudoku): Set<Region> {
        val regions: MutableSet<Region> = HashSet()
        for (area in areas) {
            val fragment = sudoku.subSudoku(area.start.x, area.start.y, area.end.x, area.end.y)
            regions.addAll(divider.divideIntoRegions(fragment))
        }
        return regions
    }

    data class Area(
        val start: Point,
        val end: Point
    )
}