package io.github.ilikeyourhat.kudoku.integration.generating

import io.github.ilikeyourhat.kudoku.generating.defaultGenerator
import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.rating.Difficulty
import io.github.ilikeyourhat.kudoku.rating.defaultRater
import io.github.ilikeyourhat.kudoku.type.Butterfly12x12
import io.github.ilikeyourhat.kudoku.type.Classic9x9
import io.github.ilikeyourhat.kudoku.type.Jigsaw4x4
import io.github.ilikeyourhat.kudoku.type.Jigsaw9x9
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.test.runTest

class SudokuGeneratingTest {

    private val random = Random(58857L)
    private val generator = Sudoku.defaultGenerator(random)
    private val rater = Sudoku.defaultRater()

    @Test
    fun `should generate Classic9x9 sudoku`() = runTest {
        val sudoku = generator.generate(Classic9x9)

        assertEquals(Classic9x9, sudoku.type)
        assertTrue(sudoku.isValid())
        assertFalse(sudoku.isCompleted())
        assertNotEquals(Difficulty.UNSOLVABLE, rater.rate(sudoku))
    }

    @Test
    fun `should generate Classic9x9 sudoku with given difficulty`() = runTest(timeout = 30.seconds) {
        val sudoku = generator.generate(Classic9x9, difficulty = Difficulty.HARD)

        assertEquals(Classic9x9, sudoku.type)
        assertTrue(sudoku.isValid())
        assertFalse(sudoku.isCompleted())
        assertEquals(Difficulty.HARD, rater.rate(sudoku))
    }

    @Test
    fun `should generate Butterfly12x12 sudoku`() = runTest(timeout = 30.seconds) {
        val sudoku = generator.generate(Butterfly12x12)

        assertEquals(Butterfly12x12, sudoku.type)
        assertTrue(sudoku.isValid())
        assertFalse(sudoku.isCompleted())
        assertNotEquals(Difficulty.UNSOLVABLE, rater.rate(sudoku))
    }

    @Test
    fun `should generate Butterfly12x12 sudoku with given difficulty`() = runTest(timeout = 30.seconds) {
        val sudoku = generator.generate(Butterfly12x12, difficulty = Difficulty.HARD)

        assertEquals(Butterfly12x12, sudoku.type)
        assertTrue(sudoku.isValid())
        assertFalse(sudoku.isCompleted())
        assertEquals(Difficulty.HARD, rater.rate(sudoku))
    }

    @Test
    fun `should generate Jigsaw9x9 sudoku`() = runTest {
        val sudoku = generator.generate(Jigsaw9x9)

        assertEquals(Jigsaw9x9, sudoku.type)
        assertTrue(sudoku.isValid())
        assertFalse(sudoku.isCompleted())
        assertNotEquals(Difficulty.UNSOLVABLE, rater.rate(sudoku))
    }

    @Test
    fun `should generate Jigsaw9x9 sudoku with given difficulty`() = runTest {
        val sudoku = generator.generate(Jigsaw9x9, difficulty = Difficulty.HARD)

        assertEquals(Jigsaw9x9, sudoku.type)
        assertTrue(sudoku.isValid())
        assertFalse(sudoku.isCompleted())
        assertEquals(Difficulty.HARD, rater.rate(sudoku))
    }

    @Test
    fun `should generate valid Jigsaw4x4 sudoku giving that empty one can be already invalid`() = runTest {
        repeat(100) {
            val sudoku = generator.generate(Jigsaw4x4)
            assertNotEquals(Difficulty.UNSOLVABLE, rater.rate(sudoku))
        }
    }
}
