package io.github.ilikeyourhat.kudoku.model.dividers

import io.github.ilikeyourhat.kudoku.model.Board
import org.junit.jupiter.api.Test

class RowsDividerTest {

    @Test
    fun `should divide board into rows`() {
        val divider = RowsDivider()
        val board = Board(2, 2)
        val regions = divider.divide(board)

        regions.assertRegionCoordinates(
            setOf(0 to 0, 1 to 0),
            setOf(0 to 1, 1 to 1)
        )
    }
}
