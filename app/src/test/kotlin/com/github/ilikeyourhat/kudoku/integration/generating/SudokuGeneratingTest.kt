package com.github.ilikeyourhat.kudoku.integration.generating

import com.github.ilikeyourhat.kudoku.Kudoku
import com.github.ilikeyourhat.kudoku.rating.Difficulty
import com.github.ilikeyourhat.kudoku.type.Butterfly12x12
import com.github.ilikeyourhat.kudoku.type.ClassicSquare9x9
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.random.Random

class SudokuGeneratingTest {

    private val random = Random(62361L)

    @Test
    fun `should generate Classic9x9 sudoku`() {
        val sudoku = Kudoku.create(ClassicSquare9x9, random = random)

        assertEquals(ClassicSquare9x9, sudoku.type)
        assertTrue(sudoku.isValid())
        assertFalse(sudoku.isCompleted())
        assertNotEquals(Difficulty.INVALID, Kudoku.rate(sudoku))
    }

    @Test
    fun `should generate Classic9x9 sudoku with given difficulty`() {
        val sudoku = Kudoku.create(ClassicSquare9x9, difficulty = Difficulty.HARD, random = random)

        assertEquals(ClassicSquare9x9, sudoku.type)
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
        assertNotEquals(Difficulty.INVALID, Kudoku.rate(sudoku))
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