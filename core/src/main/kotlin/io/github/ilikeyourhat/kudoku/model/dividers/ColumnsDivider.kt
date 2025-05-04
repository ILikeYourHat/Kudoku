package io.github.ilikeyourhat.kudoku.model.dividers

import io.github.ilikeyourhat.kudoku.model.Board
import io.github.ilikeyourhat.kudoku.model.Region

class ColumnsDivider : BoardDivider {

    override fun divide(board: Board): List<Region> {
        return (0 until board.sizeX())
            .map { indexX ->
                board.region(
                    startX = indexX,
                    startY = 0,
                    endX = indexX,
                    endY = board.sizeY() - 1
                )
            }
    }
}
