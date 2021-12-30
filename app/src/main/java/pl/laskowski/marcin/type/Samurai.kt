package pl.laskowski.marcin.type

import pl.laskowski.marcin.model.Point
import pl.laskowski.marcin.model.dividers.SudokuDivider
import pl.laskowski.marcin.model.dividers.SubSudokuDivider
import pl.laskowski.marcin.model.Sudoku

/**
 * Created by Marcin Laskowski.
 */
class Samurai(
    width: Int,
    height: Int,
    private val child: SudokuVariant
) : SudokuVariant(width, height) {

    init {
        require(width() % 2 == child.width() % 2)
        require(height() % 2 == child.height() % 2)
        require(width() >= 2 * child.width())
        require(height() >= 2 * child.height())
        require(width() < 3 * child.width())
        require(height() < 3 * child.height())
    }

    override fun regionSize(): Int {
        return child.regionSize()
    }

    override fun divider(): SudokuDivider {
        return SubSudokuDivider(
            child.divider(),
            areaStartingIn(upperLeftCorner()),
            areaStartingIn(upperRightCorner()),
            areaStartingIn(center()),
            areaStartingIn(lowerLeftCorner()),
            areaStartingIn(lowerRightCorner())
        )
    }

    private fun upperLeftCorner(): Point {
        return Point(0, 0)
    }

    private fun upperRightCorner(): Point {
        return Point(width() - child.width(), 0)
    }

    private fun center(): Point {
        val x = (width() - child.width()) / 2
        val y = (height() - child.height()) / 2
        return Point(x, y)
    }

    private fun lowerLeftCorner(): Point {
        return Point(0, height() - child.height())
    }

    private fun lowerRightCorner(): Point {
        return Point(width() - child.width(), height() - child.height())
    }

    private fun areaStartingIn(p: Point): SubSudokuDivider.Area {
        return SubSudokuDivider.Area(p, Point(p.x() + child.width(), p.y() + child.height()))
    }

    override fun template(): Sudoku {
        val sudoku = Sudoku(width(), height())
        sudoku.append(child.template(), upperLeftCorner())
        sudoku.append(child.template(), upperRightCorner())
        sudoku.append(child.template(), center())
        sudoku.append(child.template(), lowerLeftCorner())
        sudoku.append(child.template(), lowerRightCorner())
        return sudoku
    }
}