package io.github.ilikeyourhat.kudoku.model.dividers

import io.github.ilikeyourhat.kudoku.model.Board
import io.github.ilikeyourhat.kudoku.type.Square2x2
import org.junit.jupiter.api.Test

class OtherTypeDividerTest {

    @Test
    fun `should divide board based on other type`() {
        val divider = OtherTypeDivider(Square2x2, startingPoint = 0 to 0)
        val board = Board(4, 4)
        val regions = divider.divide(board)

        regions.assertRegionCoordinates(
            setOf(0 to 0, 0 to 1),
            setOf(1 to 0, 1 to 1),
            setOf(0 to 0, 1 to 0),
            setOf(0 to 1, 1 to 1),
        )
    }

    @Test
    fun `should divide board based on other type, with position shift`() {
        val divider = OtherTypeDivider(Square2x2, startingPoint = 1 to 2)
        val board = Board(4, 4)
        val regions = divider.divide(board)

        regions.assertRegionCoordinates(
            setOf(1 to 2, 1 to 3),
            setOf(2 to 2, 2 to 3),
            setOf(1 to 2, 2 to 2),
            setOf(1 to 3, 2 to 3),
        )
    }
}
