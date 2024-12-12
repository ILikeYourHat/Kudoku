package io.github.ilikeyourhat.kudoku.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BoardTest {

    @Test
    fun createGridWithNoFieldsAndCheckSize() {
        val sudoku = createGridWithSize(0, 0)
        assertEquals(0, sudoku.sizeX())
        assertEquals(0, sudoku.sizeY())
    }

    @Test
    fun createGridWithGivenSize() {
        val sudoku = createGridWithSize(4, 8)
        assertEquals(4, sudoku.sizeX())
        assertEquals(8, sudoku.sizeY())
    }

    @Test
    fun throwExceptionWhenTryingToCreateGridWithNegativeWidth() {
        assertThrows<IllegalArgumentException> {
            createGridWithSize(-1, 2)
        }
    }

    @Test
    fun throwExceptionWhenTryingToCreateGridWithNegativeHeight() {
        assertThrows<IllegalArgumentException> {
            createGridWithSize(2, -1)
        }
    }

    @Test
    fun throwExceptionWhenTryingToCreateGridWithIncorrectData() {
        assertThrows<IllegalArgumentException> {
            Board(4, 4, listOf(2, 2, 3, 4, 7))
        }
    }

    @Test
    fun checkIfGridFieldsHaveNoValuesAfterCreation() {
        val sudoku = createGridWithSize(2, 2)
        for (x in 0..1) {
            for (y in 0..1) {
                assertEquals(0, sudoku.getOrNull(x, y)!!.value)
            }
        }
    }

    @Test
    fun checkIfFieldsAreInitializedCorrectWhenValuesAreGiven() {
        val values = listOf<Int?>(1, 2, 3, 4)
        val board = Board(2, 2, values)
        assertFalse(board.getOrNull(0, 0)!!.isEmpty)
        assertEquals(1, board.getOrNull(0, 0)!!.value)
        assertFalse(board.getOrNull(1, 0)!!.isEmpty)
        assertEquals(2, board.getOrNull(1, 0)!!.value)
        assertFalse(board.getOrNull(0, 1)!!.isEmpty)
        assertEquals(3, board.getOrNull(0, 1)!!.value)
        assertFalse(board.getOrNull(1, 1)!!.isEmpty)
        assertEquals(4, board.getOrNull(1, 1)!!.value)
    }

    @Test
    fun changeValueAtGivenPosition() {
        val values = listOf<Int?>(
            0, 0, 0,
            0, 0, 1,
            0, 0, 0
        )
        val board = Board(3, 3, values)
        assertEquals(1, board.getOrNull(2, 1)!!.value)
        board.getOrNull(2, 1)!!.set(2)
        assertEquals(2, board.getOrNull(2, 1)!!.value)
    }

    @Test
    fun shouldBeEqualsIfSizeIsEquals() {
        val testSudoku = createGridWithSize(2, 3)
        val sameSudoku = createGridWithSize(2, 3)
        val differentSudoku = createGridWithSize(3, 2)
        assertEquals(testSudoku, sameSudoku)
        assertNotEquals(testSudoku, differentSudoku)
    }

    private fun createGridWithSize(sizeX: Int, sizeY: Int): Board {
        return Board(sizeX, sizeY) { x, y -> Field(x, y) }
    }
}
