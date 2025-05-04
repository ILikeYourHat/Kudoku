package io.github.ilikeyourhat.kudoku.model.dividers

import io.github.ilikeyourhat.kudoku.model.Board
import io.github.ilikeyourhat.kudoku.model.Region
import io.github.ilikeyourhat.kudoku.model.SudokuType

class OtherTypeDivider(
    private val type: SudokuType,
    private val startingPoint: Pair<Int, Int>,
) : BoardDivider {

    override fun divide(board: Board): List<Region> {
        val (x, y) = startingPoint
        val subBoard = board.fragment(x, y, x + type.sizeX, y + type.sizeY)
        return type.divide(subBoard)
    }
}
