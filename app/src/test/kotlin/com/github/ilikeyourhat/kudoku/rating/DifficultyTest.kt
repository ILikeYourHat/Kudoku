package com.github.ilikeyourhat.kudoku.rating

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DifficultyTest {

    @Test
    fun `should be comparable by effort to solve`() {
        val difficulties = Difficulty.entries.sorted()

        difficulties shouldBe listOf(
            Difficulty.EASY,
            Difficulty.MEDIUM,
            Difficulty.HARD,
            Difficulty.VERY_HARD,
            Difficulty.UNSOLVABLE
        )
    }

}
