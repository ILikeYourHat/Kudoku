package io.github.ilikeyourhat.kudoku.integration.solving

import io.github.ilikeyourhat.kudoku.Kudoku
import io.github.ilikeyourhat.kudoku.solving.SolutionCount
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SudokuSolutionCheckerTest {

    @Test
    fun `should have no solutions`() {
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

        val solutions = Kudoku.checkSolutions(sudoku)

        assertEquals(SolutionCount.ZERO, solutions)
    }

    @Test
    fun `should have one solution`() {
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

        val solutions = Kudoku.checkSolutions(sudoku)

        assertEquals(SolutionCount.ONE, solutions)
    }

    @Test
    fun `should have multiple solutions`() {
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

        val solutions = Kudoku.checkSolutions(sudoku)

        assertEquals(SolutionCount.MANY, solutions)
    }
}
