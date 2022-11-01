package com.github.ilikeyourhat.sudokusolver.integration

import com.github.ilikeyourhat.sudokusolver.Kudoku
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

abstract class SudokuTypesTest {

    abstract val puzzle: String
    abstract val solution: String

    @Test
    fun `should solve it correctly`() {
        val solver = Kudoku.defaultSolver()
        val sudokuToSolve = Kudoku.sudokuFromString(puzzle)
        val expectedResult = Kudoku.sudokuFromString(solution)

        val result = solver.solve(sudokuToSolve)

        assertEquals(expectedResult, result)
        assertTrue(result.isSolvedCorrectly())
    }
}