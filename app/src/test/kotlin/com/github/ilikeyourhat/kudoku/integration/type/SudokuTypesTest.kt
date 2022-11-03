package com.github.ilikeyourhat.kudoku.integration.type

import com.github.ilikeyourhat.kudoku.Kudoku
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

abstract class SudokuTypesTest {

    abstract val puzzle: String
    abstract val solution: String

    @Test
    fun `should solve it correctly`() {
        val solver = Kudoku.defaultSolver()
        val sudokuToSolve = Kudoku.createFromString(puzzle)
        val expectedResult = Kudoku.createFromString(solution)

        val result = solver.solve(sudokuToSolve)

        assertEquals(expectedResult, result)
        assertTrue(result.isSolved())
    }
}