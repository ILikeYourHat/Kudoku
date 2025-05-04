package io.github.ilikeyourhat.kudoku.model.dividers

import io.github.ilikeyourhat.kudoku.type.Classic4x4
import io.github.ilikeyourhat.kudoku.type.Classic9x9
import io.github.ilikeyourhat.kudoku.type.SamuraiClassic21x21
import io.kotest.inspectors.shouldForAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.equals.shouldBeEqual
import org.junit.jupiter.api.Test
import kotlin.random.Random

class RandomRegionsDividerTest {

    @Test
    fun `regions should have the same size`() {
        val divider = RandomRegionsDivider(Classic9x9)
        val board = Classic9x9.createEmpty().board

        repeat(100) {
            val regions = divider.divide(board)

            regions.shouldHaveSize(9)
                .shouldForAll { region -> region.shouldHaveSize(9) }
        }
    }

    @Test
    fun `regions should be distinct and cover all the board`() {
        val divider = RandomRegionsDivider(Classic9x9)
        val board = Classic9x9.createEmpty().board

        repeat(100) {
            val regions = divider.divide(board)

            regions.flatten()
                .shouldHaveSize(board.cells().size)
                .distinct()
                .shouldHaveSize(board.cells().size)
        }
    }

    @Test
    fun `should work for any type`() {
        val divider = RandomRegionsDivider(Classic4x4)
        val board = Classic4x4.createEmpty().board

        repeat(100) {
            val regions = divider.divide(board)

            regions.shouldHaveSize(4)
                .shouldForAll { region -> region.shouldHaveSize(4) }
        }
    }

    @Test
    fun `should work for any type 2`() {
        val divider = RandomRegionsDivider(SamuraiClassic21x21)
        val board = SamuraiClassic21x21.createEmpty().board

        val regions = divider.divide(board)

        regions.shouldHaveSize(41)
            .shouldForAll { it.shouldHaveSize(9) }
    }

    @Test
    fun `generation should be deterministic`() {
        val type = Classic9x9
        val board = type.createEmpty().board

        val firstTry = RandomRegionsDivider(type, random = Random(1234L))
            .divide(board)
        val secondTry = RandomRegionsDivider(type, random = Random(1234L))
            .divide(board)

        firstTry.shouldBeEqual(secondTry)
    }
}
