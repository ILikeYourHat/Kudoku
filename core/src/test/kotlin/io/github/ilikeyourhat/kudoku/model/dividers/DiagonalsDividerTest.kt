package io.github.ilikeyourhat.kudoku.model.dividers

import io.github.ilikeyourhat.kudoku.model.Board
import io.github.ilikeyourhat.kudoku.model.Cell
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.Test

class DiagonalsDividerTest {

    @Test
    fun `should divide board into both diagonals`() {
        val divider = DiagonalsDivider(DiagonalsDivider.DivisionType.BOTH)
        val board = Board(3, 3) { x, y -> Cell(x, y) }
        val regions = divider.divide(board)

        regions.assertRegionCoordinates(
            setOf(0 to 0, 1 to 1, 2 to 2),
            setOf(0 to 2, 1 to 1, 2 to 0)
        )
    }

    @Test
    fun `should divide board into primary diagonal only`() {
        val divider = DiagonalsDivider(DiagonalsDivider.DivisionType.PRIMARY)
        val board = Board(3, 3) { x, y -> Cell(x, y) }
        val regions = divider.divide(board)

        regions.assertRegionCoordinates(
            setOf(0 to 0, 1 to 1, 2 to 2)
        )
    }

    @Test
    fun `should divide board into antidiagonal only`() {
        val divider = DiagonalsDivider(DiagonalsDivider.DivisionType.ANTI)
        val board = Board(3, 3) { x, y -> Cell(x, y) }
        val regions = divider.divide(board)

        regions.assertRegionCoordinates(
            setOf(0 to 2, 1 to 1, 2 to 0)
        )
    }

    @Test
    fun `should throw exception when board is not square`() {
        val divider = DiagonalsDivider(DiagonalsDivider.DivisionType.BOTH)

        shouldThrow<IllegalArgumentException> {
            val board = Board(3, 4) { x, y -> Cell(x, y) }
            divider.divide(board)
        }.shouldHaveMessage("Board size 3x4 is not square")
    }
}
