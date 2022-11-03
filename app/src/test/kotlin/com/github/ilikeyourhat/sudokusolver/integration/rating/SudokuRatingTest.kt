package com.github.ilikeyourhat.sudokusolver.integration.rating

import com.github.ilikeyourhat.sudokusolver.Kudoku
import com.github.ilikeyourhat.sudokusolver.rating.Difficulty
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SudokuRatingTest {

    @Test
    fun `should rate sudoku as EASY`() {
        val sudoku = Kudoku.createFromString("""
            classic_9x9
            _,5,7, 8,6,_, _,_,4
            8,_,4, _,9,5, 2,_,_
            _,_,3, _,_,7, 5,8,1

            _,_,_, 7,4,2, _,1,9
            7,6,1, _,_,_, 8,4,_
            _,2,9, 1,8,_, _,7,_

            1,4,_, 9,_,3, 7,_,_
            5,_,_, 6,_,_, 4,9,3
            9,3,_, _,7,4, _,_,8
        """.trimIndent())

        val rating = Kudoku.rate(sudoku)

        assertEquals(Difficulty.EASY, rating)
    }

    @Test
    fun `should rate sudoku as MEDIUM`() {
        val sudoku = Kudoku.createFromString("""
            classic_9x9
            3,_,_, 2,_,_, _,_,_
            _,_,_, 1,_,7, _,_,_
            7,_,6, _,3,_, 5,_,_

            _,7,_, _,_,9, _,8,_
            9,_,_, _,2,_, _,_,4
            _,1,_, 8,_,_, _,5,_

            _,_,9, _,4,_, 3,_,1
            _,_,_, 7,_,2, _,_,_
            _,_,_, _,_,8, _,_,6
        """.trimIndent())

        val rating = Kudoku.rate(sudoku)

        assertEquals(Difficulty.MEDIUM, rating)
    }

    @Test
    fun `should rate sudoku as HARD`() {
        val sudoku = Kudoku.createFromString("""
            classic_9x9
            _,_,_, _,_,7, 5,_,_
            _,_,4, _,_,9, _,1,_
            _,_,2, _,_,_, _,_,_
            
            _,1,_, _,6,_, 9,_,_
            2,_,_, _,_,_, _,_,_
            _,_,9, _,_,_, _,7,4
            
            _,_,_, 7,2,3, _,9,_
            6,_,_, _,_,_, _,_,_
            _,8,_, _,1,5, _,2,_
        """.trimIndent())

        val rating = Kudoku.rate(sudoku)

        assertEquals(Difficulty.HARD, rating)
    }

    @Test
    fun `should rate sudoku as VERY_HARD`() {
        val sudoku = Kudoku.createFromString("""
            classic_9x9
            8,_,_, _,_,_, _,_,_
            _,_,3, 6,_,_, _,_,_
            _,7,_, _,9,_, 2,_,_

            _,5,_, _,_,7, _,_,_
            _,_,_, _,4,5, 7,_,_
            _,_,_, 1,_,_, _,3,_

            _,_,1, _,_,_, _,6,8
            _,_,8, 5,_,_, _,1,_
            _,9,_, _,_,_, 4,_,_
        """.trimIndent())

        val rating = Kudoku.rate(sudoku)

        assertEquals(Difficulty.VERY_HARD, rating)
    }

    @Test
    fun `should rate sudoku as INVALID when no solutions`() {
        val sudoku = Kudoku.createFromString("""
            classic_9x9
            1,1,1, _,_,_, _,_,_
            _,_,_, _,_,_, _,_,_
            _,_,_, _,_,_, _,_,_

            _,_,_, _,_,_, _,_,_
            _,_,_, _,_,_, _,_,_
            _,_,_, _,_,_, _,_,_

            _,_,_, _,_,_, _,_,_
            _,_,_, _,_,_, _,_,_
            _,_,_, _,_,_, _,_,_
        """.trimIndent())

        val rating = Kudoku.rate(sudoku)

        assertEquals(Difficulty.INVALID, rating)
    }

    @Test
    fun `should rate sudoku as INVALID when multiple solutions`() {
        val sudoku = Kudoku.createFromString("""
            classic_9x9
            1,_,_, _,_,_, _,_,_
            _,_,_, _,_,_, _,_,_
            _,_,_, _,_,_, _,_,_

            _,_,_, _,_,_, _,_,_
            _,_,_, _,_,_, _,_,_
            _,_,_, _,_,_, _,_,_

            _,_,_, _,_,_, _,_,_
            _,_,_, _,_,_, _,_,_
            _,_,_, _,_,_, _,_,_
        """.trimIndent())

        val rating = Kudoku.rate(sudoku)

        assertEquals(Difficulty.INVALID, rating)
    }
}