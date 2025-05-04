package io.github.ilikeyourhat.kudoku.model.dividers

import io.github.ilikeyourhat.kudoku.model.Board
import io.github.ilikeyourhat.kudoku.model.Region

class RegionIdDivider(
    private val regionIds: List<Int>
) : BoardDivider {

    override fun divide(board: Board): List<Region> {
        val cells = board.cells()
        require(cells.size == regionIds.size) {
            "Data size ${regionIds.size} does not match board size ${cells.size}"
        }
        return regionIds.zip(cells)
            .groupBy({ it.first }, { it.second })
            .map { Region(it.value) }
    }
}
