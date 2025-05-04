package io.github.ilikeyourhat.kudoku.model.dividers

import io.github.ilikeyourhat.kudoku.model.Board
import org.junit.jupiter.api.Test

class ColumnsDividerTest {

    @Test
    fun `should divide board into columns`() {
        val divider = ColumnsDivider()
        val board = Board(2, 2)
        val regions = divider.divide(board)

        regions.assertRegionCoordinates(
            setOf(0 to 0, 0 to 1),
            setOf(1 to 0, 1 to 1)
        )
    }
}
