package io.github.ilikeyourhat.kudoku.model.dividers

import io.github.ilikeyourhat.kudoku.model.Board
import io.github.ilikeyourhat.kudoku.model.Cell
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.Test

class RegionIdDividerTest {

    @Test
    fun `should divide board into regions by id`() {
        val divider = RegionIdDivider(listOf(1, 1, 1, 2, 2, 3))
        val board = Board(2, 3) { x, y -> Cell(x, y) }
        val regions = divider.divide(board)

        regions.assertRegionCoordinates(
            setOf(0 to 0, 0 to 1, 1 to 0),
            setOf(1 to 1, 0 to 2),
            setOf(1 to 2)
        )
    }

    @Test
    fun `should throw exception if data size is incorrect`() {
        val divider = RegionIdDivider(listOf(1, 1))
        val board = Board(2, 3) { x, y -> Cell(x, y) }

        shouldThrow<IllegalArgumentException> { divider.divide(board) }
            .shouldHaveMessage("Data size 2 does not match board size 6")
    }
}
