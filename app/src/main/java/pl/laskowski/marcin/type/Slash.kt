package pl.laskowski.marcin.type

import pl.laskowski.marcin.model.Point
import pl.laskowski.marcin.model.dividers.SudokuDivider
import pl.laskowski.marcin.model.dividers.SubSudokuDivider

class Slash(
    width: Int,
    height: Int,
    private val child: ISudokuVariant
) : SudokuVariant(width, height) {

    private val widthOffset = width - child.sizeX
    private val heightOffset = height - child.sizeY

    init {
        require(widthOffset > 0 && widthOffset < child.sizeX)
        require(heightOffset > 0 && heightOffset < child.sizeY)
    }

    override val regionSize = child.regionSize

    override fun divider(): SudokuDivider {
//        return SubSudokuDivider(
//            child.divider(),
//            areaStartingIn(upperLeftCorner()),
//            areaStartingIn(lowerRightCorner())
//        )
        TODO()
    }

    private fun areaStartingIn(p: Point): SubSudokuDivider.Area {
        return SubSudokuDivider.Area(p, Point(p.x + child.sizeX, p.y + child.sizeY))
    }

    private fun upperLeftCorner(): Point {
        return Point(0, 0)
    }

    private fun lowerRightCorner(): Point {
        return Point(widthOffset, heightOffset)
    }
}