package io.github.ilikeyourhat.kudoku.model.dividers

import io.github.ilikeyourhat.kudoku.model.Board
import io.github.ilikeyourhat.kudoku.model.Region

class SingleRegionDivider : BoardDivider {

    override fun divide(board: Board): List<Region> {
        return listOf(
            board.region(
                startX = 0,
                startY = 0,
                endX = board.sizeX() - 1,
                endY = board.sizeY() - 1
            )
        )
    }
}
