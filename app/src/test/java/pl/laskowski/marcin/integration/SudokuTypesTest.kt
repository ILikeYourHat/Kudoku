package pl.laskowski.marcin.integration

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import pl.laskowski.marcin.parsing.text.SudokuTextFormatParser
import pl.laskowski.marcin.solving.sat.SatSolver

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