package io.github.ilikeyourhat.kudoku.model.dividers

import io.github.ilikeyourhat.kudoku.model.Board
import org.junit.jupiter.api.Test

class SingleRegionDividerTest {

    @Test
    fun `should divide board into single region`() {
        val divider = SingleRegionDivider()
        val board = Board(2, 2)
        val regions = divider.divide(board)

        regions.assertRegionCoordinates(
            setOf(0 to 0, 0 to 1, 1 to 0, 1 to 1)
        )
    }
}
