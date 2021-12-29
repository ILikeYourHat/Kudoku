package pl.laskowski.marcin.model

import org.junit.Assert.*
import org.junit.Test

/**
 * Created by Marcin Laskowski.
 */
class SudokuTest {

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

    @Test(expected = IllegalArgumentException::class)
    fun throwExceptionWhenTryingToCreateGridWithNegativeWidth() {
        createGridWithSize(-1, 2)
    }

    @Test(expected = IllegalArgumentException::class)
    fun throwExceptionWhenTryingToCreateGridWithNegativeHeight() {
        createGridWithSize(2, -1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun throwExceptionWhenTryingToCreateGridWithIncorrectData() {
        Sudoku(
            2, 2, arrayOf(
                2, 2,
                3, 4,
                7
            )
        )
    }

    @Test
    fun checkIfGridFieldsHaveNoValuesAfterCreation() {
        val sudoku = createGridWithSize(2, 2)
        for (x in 0..1) {
            for (y in 0..1) {
                assertNull(sudoku.at(x, y))
            }
        }
    }

    @Test
    fun checkIfFieldsAreInitializedCorrectWhenValuesAreGiven() {
        val values = arrayOf<Int?>(
            1, 2,
            3, 4
        )
        val sudoku = Sudoku(2, 2, values)
        assertFalse(sudoku.at(0, 0)!!.isEmpty)
        assertEquals(1, sudoku.at(0, 0)!!.value)
        assertFalse(sudoku.at(1, 0)!!.isEmpty)
        assertEquals(2, sudoku.at(1, 0)!!.value)
        assertFalse(sudoku.at(0, 1)!!.isEmpty)
        assertEquals(3, sudoku.at(0, 1)!!.value)
        assertFalse(sudoku.at(1, 1)!!.isEmpty)
        assertEquals(4, sudoku.at(1, 1)!!.value)
    }

    @Test
    fun changeValueAtGivenPosition() {
        val values = arrayOf<Int?>(
            0, 0, 0,
            0, 0, 1,
            0, 0, 0
        )
        val sudoku = Sudoku(3, 3, values)
        assertEquals(1, sudoku.at(2, 1)!!.value)
        sudoku.at(2, 1)!!.set(2)
        assertEquals(2, sudoku.at(2, 1)!!.value)
    }

    @Test
    fun iterateOverGrid() {
        val values = arrayOf<Int?>(
            0, 1, 2, 3,
            4, 5, 6, 7,
            8, 9, 10, 11
        )
        val sudoku = Sudoku(4, 3, values)
        var currentValue = 0
        for ((_, value) in sudoku) {
            assertEquals(currentValue, value)
            currentValue++
        }
    }

    @Test
    fun shouldBeEqualsIfSizeIsEquals() {
        val testSudoku = createGridWithSize(2, 3)
        val sameSudoku = createGridWithSize(2, 3)
        val differentSudoku = createGridWithSize(3, 2)
        assertEquals(testSudoku, sameSudoku)
        assertNotEquals(testSudoku, differentSudoku)
    }

    private fun createGridWithSize(x: Int, y: Int): Sudoku {
        return Sudoku(x, y)
    }
}