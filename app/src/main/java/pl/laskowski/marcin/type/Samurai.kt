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
        require(sizeX % 2 == child.sizeX % 2)
        require(sizeY % 2 == child.sizeY % 2)
        require(sizeX >= 2 * child.sizeX)
        require(sizeY >= 2 * child.sizeY)
        require(sizeX < 3 * child.sizeX)
        require(sizeY < 3 * child.sizeY)
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
        return Point(sizeX - child.sizeX, 0)
    }

    private fun center(): Point {
        val x = (sizeX - child.sizeX) / 2
        val y = (sizeY - child.sizeY) / 2
        return Point(x, y)
    }

    private fun lowerLeftCorner(): Point {
        return Point(0, sizeY - child.sizeY)
    }

    private fun lowerRightCorner(): Point {
        return Point(sizeX - child.sizeX, sizeY - child.sizeY)
    }

    private fun areaStartingIn(p: Point): SubSudokuDivider.Area {
        return SubSudokuDivider.Area(p, Point(p.x + child.sizeX, p.y + child.sizeY))
    }

    override fun template(): Sudoku {
//        val sudoku = Sudoku(width(), height())
//        sudoku.append(child.template(), upperLeftCorner())
//        sudoku.append(child.template(), upperRightCorner())
//        sudoku.append(child.template(), center())
//        sudoku.append(child.template(), lowerLeftCorner())
//        sudoku.append(child.template(), lowerRightCorner())
//        return sudoku
        TODO()
    }
}