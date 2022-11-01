package com.github.ilikeyourhat.sudokusolver.integration

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import com.github.ilikeyourhat.sudokusolver.parsing.text.SudokuTextFormatParser
import com.github.ilikeyourhat.sudokusolver.solving.sat.SatSolver

abstract class SudokuTypesTest {

    abstract val puzzle: String
    abstract val solution: String

    @Test
    fun `should solve it correctly`() {
        val solver = SatSolver()

        val sudokuToSolve = SudokuTextFormatParser().parseOne(puzzle)
        val expectedResult = SudokuTextFormatParser().parseOne(solution)

        val result = solver.solve(sudokuToSolve)
        assertEquals(expectedResult, result)
        assertTrue(result.isSolvedCorrectly())
    }
}