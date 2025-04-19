package io.github.ilikeyourhat.kudoku.integration.generating

import io.github.ilikeyourhat.kudoku.Kudoku
import io.github.ilikeyourhat.kudoku.rating.Difficulty
import io.github.ilikeyourhat.kudoku.type.Butterfly12x12
import io.github.ilikeyourhat.kudoku.type.Classic9x9
import io.github.ilikeyourhat.kudoku.type.Jigsaw4x4
import io.github.ilikeyourhat.kudoku.type.Jigsaw9x9
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import java.util.concurrent.TimeUnit
import kotlin.random.Random

@Timeout(
    value = 30,
    unit = TimeUnit.SECONDS,
    threadMode = Timeout.ThreadMode.SEPARATE_THREAD
)
class SudokuGeneratingTest {

    private val random = Random(58857L)

    @Test
    fun `should generate Classic9x9 sudoku`() {
        val sudoku = Kudoku.create(Classic9x9, random = random)

        assertEquals(Classic9x9, sudoku.type)
        assertTrue(sudoku.isValid())
        assertFalse(sudoku.isCompleted())
        assertNotEquals(Difficulty.UNSOLVABLE, Kudoku.rate(sudoku))
    }

    @Test
    fun `should generate Classic9x9 sudoku with given difficulty`() {
        val sudoku = Kudoku.create(Classic9x9, difficulty = Difficulty.HARD, random = random)

        assertEquals(Classic9x9, sudoku.type)
        assertTrue(sudoku.isValid())
        assertFalse(sudoku.isCompleted())
        assertEquals(Difficulty.HARD, Kudoku.rate(sudoku))
    }

    @Test
    fun `should generate Butterfly12x12 sudoku`() {
        val sudoku = Kudoku.create(Butterfly12x12, random = random)

        assertEquals(Butterfly12x12, sudoku.type)
        assertTrue(sudoku.isValid())
        assertFalse(sudoku.isCompleted())
        assertNotEquals(Difficulty.UNSOLVABLE, Kudoku.rate(sudoku))
    }

    @Test
    fun `should generate Butterfly12x12 sudoku with given difficulty`() {
        val sudoku = Kudoku.create(Butterfly12x12, difficulty = Difficulty.HARD, random = random)

        assertEquals(Butterfly12x12, sudoku.type)
        assertTrue(sudoku.isValid())
        assertFalse(sudoku.isCompleted())
        assertEquals(Difficulty.HARD, Kudoku.rate(sudoku))
    }

    @Test
    fun `should generate Jigsaw9x9 sudoku`() {
        val sudoku = Kudoku.create(Jigsaw9x9, random = random)

        assertEquals(Jigsaw9x9, sudoku.type)
        assertTrue(sudoku.isValid())
        assertFalse(sudoku.isCompleted())
        assertNotEquals(Difficulty.UNSOLVABLE, Kudoku.rate(sudoku))
    }

    @Test
    fun `should generate Jigsaw9x9 sudoku with given difficulty`() {
        val sudoku = Kudoku.create(Jigsaw9x9, difficulty = Difficulty.HARD, random = random)

        assertEquals(Jigsaw9x9, sudoku.type)
        assertTrue(sudoku.isValid())
        assertFalse(sudoku.isCompleted())
        assertEquals(Difficulty.HARD, Kudoku.rate(sudoku))
    }

    @Test
    fun `should generate valid Jigsaw4x4 sudoku giving that empty one can be already invalid`() {
        repeat(100) {
            val sudoku = Kudoku.create(Jigsaw4x4, random = random)
            assertNotEquals(Difficulty.UNSOLVABLE, Kudoku.rate(sudoku))
        }
    }
}
