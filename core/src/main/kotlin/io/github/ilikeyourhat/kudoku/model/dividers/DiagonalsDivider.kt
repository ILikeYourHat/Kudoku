package io.github.ilikeyourhat.kudoku.model.dividers

import io.github.ilikeyourhat.kudoku.model.Board
import io.github.ilikeyourhat.kudoku.model.Cell
import io.github.ilikeyourhat.kudoku.model.Region

class DiagonalsDivider(
    private val divisionType: DivisionType,
) : BoardDivider {

    override fun divide(board: Board): List<Region> {
        require(board.sizeX() == board.sizeY()) {
            "Board size ${board.sizeX()}x${board.sizeY()} is not square"
        }
        return when (divisionType) {
            DivisionType.PRIMARY -> listOf(primaryDiagonal(board))
            DivisionType.ANTI -> listOf(antiDiagonal(board))
            DivisionType.BOTH -> listOf(primaryDiagonal(board), antiDiagonal(board))
        }
    }

    private fun primaryDiagonal(board: Board): Region {
        val cells = mutableListOf<Cell>()
        for (x in 0 until board.sizeX()) {
            cells += board.get(x, x)
        }
        return Region(cells)
    }

    private fun antiDiagonal(board: Board): Region {
        val cells = mutableListOf<Cell>()
        for (x in 0 until board.sizeX()) {
            cells += board.get(x, board.sizeY() - x - 1)
        }
        return Region(cells)
    }

    enum class DivisionType {
        PRIMARY,
        ANTI,
        BOTH
    }
}
