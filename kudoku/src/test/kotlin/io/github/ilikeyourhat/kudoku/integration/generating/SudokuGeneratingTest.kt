package io.github.ilikeyourhat.kudoku.integration.generating

import io.github.ilikeyourhat.kudoku.Kudoku
import io.github.ilikeyourhat.kudoku.rating.Difficulty
import io.github.ilikeyourhat.kudoku.type.Butterfly12x12
import io.github.ilikeyourhat.kudoku.type.Classic9x9
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import java.util.concurrent.TimeUnit
import kotlin.random.Random

@Timeout(
    value = 10,
    unit = TimeUnit.SECONDS,
    threadMode = Timeout.ThreadMode.SEPARATE_THREAD
)
class SudokuGeneratingTest {

    private val random = Random(62361L)

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
}
