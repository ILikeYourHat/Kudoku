package io.github.ilikeyourhat.kudoku.generating

import io.github.ilikeyourhat.kudoku.type.Classic4x4
import io.github.ilikeyourhat.kudoku.type.Classic9x9
import io.github.ilikeyourhat.kudoku.type.SamuraiClassic21x21
import io.kotest.inspectors.shouldForAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.equals.shouldBeEqual
import org.junit.jupiter.api.Test
import kotlin.random.Random

class RandomRegionGeneratorTest {

    private val generator = RandomRegionGenerator()

    @Test
    fun `regions should have the same size`() {
        val type = Classic9x9
        val board = type.createEmpty().board

        repeat(100) {
            val regions = generator.generateRandomRegions(type, board)

            regions.shouldHaveSize(9)
                .shouldForAll { region -> region.shouldHaveSize(9) }
        }
    }

    @Test
    fun `regions should be distinct and cover all the board`() {
        val type = Classic9x9
        val board = type.createEmpty().board

        repeat(100) {
            val regions = generator.generateRandomRegions(type, board)

            regions.flatten()
                .shouldHaveSize(board.fields().size)
                .distinct()
                .shouldHaveSize(board.fields().size)
        }
    }

    @Test
    fun `should work for any type`() {
        val type = Classic4x4
        val board = type.createEmpty().board

        repeat(100) {
            val regions = generator.generateRandomRegions(type, board)

            regions.shouldHaveSize(4)
                .shouldForAll { region -> region.shouldHaveSize(4) }
        }
    }

    @Test
    fun `should work for any type 2`() {
        val type = SamuraiClassic21x21
        val board = type.createEmpty().board

        val regions = generator.generateRandomRegions(type, board)

        regions.shouldHaveSize(41)
            .shouldForAll { it.shouldHaveSize(9) }
    }

    @Test
    fun `generation should be deterministic`() {
        val type = Classic9x9
        val board = type.createEmpty().board

        val firstTry = RandomRegionGenerator(random = Random(1234L))
            .generateRandomRegions(type, board)
        val secondTry = RandomRegionGenerator(random = Random(1234L))
            .generateRandomRegions(type, board)

        firstTry.shouldBeEqual(secondTry)
    }
}
