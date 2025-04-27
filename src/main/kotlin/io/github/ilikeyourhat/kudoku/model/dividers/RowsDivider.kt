package io.github.ilikeyourhat.kudoku.model.dividers

import io.github.ilikeyourhat.kudoku.model.Board
import io.github.ilikeyourhat.kudoku.model.Region

class RowsDivider : BoardDivider {

    override fun divide(board: Board): List<Region> {
        return (0 until board.sizeY())
            .map { indexY ->
                board.region(
                    startX = 0,
                    startY = indexY,
                    endX = board.sizeX() - 1,
                    endY = indexY
                )
            }
    }
}
