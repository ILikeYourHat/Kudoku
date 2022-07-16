package pl.laskowski.marcin.type

import pl.laskowski.marcin.model.Point
import pl.laskowski.marcin.model.dividers.SudokuDivider
import pl.laskowski.marcin.model.dividers.SubSudokuDivider
import pl.laskowski.marcin.model.Sudoku

/**
 * Created by Marcin Laskowski.
 */
class Backslash(
    width: Int,
    height: Int,
    private val child: SudokuVariant
) : SudokuVariant(width, height) {

    private val widthOffset = width - child.width()
    private val heightOffset =  height - child.height()

    init {
        require(widthOffset > 0 && widthOffset < child.width())
        require(heightOffset > 0 && heightOffset < child.height())
    }

    override fun regionSize(): Int {
        return child.regionSize()
    }

    override fun divider(): SudokuDivider {
        return SubSudokuDivider(
            child.divider(),
            areaStartingIn(upperRightCorner()),
            areaStartingIn(lowerLeftCorner())
        )
    }

    override fun template(): Sudoku {
//        val sudoku = Sudoku(width(), height())
//        sudoku.append(child.template(), upperRightCorner())
//        sudoku.append(child.template(), lowerLeftCorner())
//        return sudoku
        TODO()
    }

    private fun areaStartingIn(p: Point): SubSudokuDivider.Area {
        return SubSudokuDivider.Area(p, Point(p.x + child.width(), p.y + child.height()))
    }

    private fun upperRightCorner(): Point {
        return Point(widthOffset, 0)
    }

    private fun lowerLeftCorner(): Point {
        return Point(0, heightOffset)
    }
}