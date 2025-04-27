package io.github.ilikeyourhat.kudoku.model.dividers

import io.github.ilikeyourhat.kudoku.model.Board
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.Test

class BlocksDividerTest {

    @Test
    fun `should divide board into 2x3 blocks`() {
        val divider = BlocksDivider(2, 3)
        val board = Board(4, 6)
        val regions = divider.divide(board)

        regions.assertRegionCoordinates(
            setOf(0 to 0, 0 to 1, 0 to 2, 1 to 0, 1 to 1, 1 to 2),
            setOf(0 to 3, 0 to 4, 0 to 5, 1 to 3, 1 to 4, 1 to 5),
            setOf(2 to 0, 2 to 1, 2 to 2, 3 to 0, 3 to 1, 3 to 2),
            setOf(2 to 3, 2 to 4, 2 to 5, 3 to 3, 3 to 4, 3 to 5)
        )
    }

    @Test
    fun `should divide board into 2x2 blocks`() {
        val divider = BlocksDivider(2)
        val board = Board(4, 4)
        val regions = divider.divide(board)

        regions.assertRegionCoordinates(
            setOf(0 to 0, 0 to 1, 1 to 0, 1 to 1),
            setOf(0 to 2, 0 to 3, 1 to 2, 1 to 3),
            setOf(2 to 0, 2 to 1, 3 to 0, 3 to 1),
            setOf(2 to 2, 2 to 3, 3 to 2, 3 to 3)
        )
    }

    @Test
    fun `should not divide board with invalid size`() {
        val divider = BlocksDivider(2, 2)

        shouldThrow<IllegalArgumentException> { divider.divide(Board(7, 4)) }
            .shouldHaveMessage("Board size 7x4 is not divisible by block sizeX 2")
        shouldThrow<IllegalArgumentException> { divider.divide(Board(4, 7)) }
            .shouldHaveMessage("Board size 4x7 is not divisible by block sizeY 2")
    }
}
