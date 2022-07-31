package pl.laskowski.marcin.integration

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import pl.laskowski.marcin.parsing.text.SudokuTextFormatParser
import pl.laskowski.marcin.solving.sat.SatSolver

class SudokuTypesTest {

    @Test
    fun `DoubleSlash15x15 type`() {
        val input = """
            double_slash_15x15
            #,#,#,#,#,#,_,4,5,_,_,_,_,_,_
            #,#,#,#,#,#,_,_,_,_,_,_,_,1,6
            #,#,#,#,#,#,_,_,6,_,_,_,9,_,_
            #,#,#,#,#,#,8,7,_,_,_,_,_,_,_
            #,#,#,#,#,#,_,2,_,_,1,_,_,3,7
            #,#,#,#,#,#,_,_,_,9,_,6,_,_,4
            _,_,_,_,_,2,_,_,_,_,_,1,_,_,9
            4,_,_,_,_,7,_,_,_,_,5,_,_,7,_
            _,_,8,_,_,3,_,_,_,_,3,_,_,_,_
            7,2,5,_,6,_,8,_,_,#,#,#,#,#,#
            _,_,_,_,_,_,_,_,_,#,#,#,#,#,#
            3,_,_,_,4,_,_,_,6,#,#,#,#,#,#
            6,_,2,_,_,_,_,_,_,#,#,#,#,#,#
            8,3,_,1,_,_,_,_,7,#,#,#,#,#,#
            _,_,_,_,_,_,_,8,_,#,#,#,#,#,#
        """.trimIndent()

        val output = """
            double_slash_15x15
            #,#,#,#,#,#,1,4,5,6,9,3,7,8,2
            #,#,#,#,#,#,9,3,7,5,8,2,4,1,6
            #,#,#,#,#,#,2,8,6,1,4,7,9,5,3
            #,#,#,#,#,#,8,7,4,3,2,5,6,9,1
            #,#,#,#,#,#,6,2,9,8,1,4,5,3,7
            #,#,#,#,#,#,5,1,3,9,7,6,8,2,4
            9,6,3,4,1,2,7,5,8,2,6,1,3,4,9
            4,5,1,9,8,7,3,6,2,4,5,9,1,7,8
            2,7,8,6,5,3,4,9,1,7,3,8,2,6,5
            7,2,5,3,6,9,8,1,4,#,#,#,#,#,#
            1,4,6,7,2,8,9,3,5,#,#,#,#,#,#
            3,8,9,5,4,1,2,7,6,#,#,#,#,#,#
            6,9,2,8,7,5,1,4,3,#,#,#,#,#,#
            8,3,4,1,9,6,5,2,7,#,#,#,#,#,#
            5,1,7,2,3,4,6,8,9,#,#,#,#,#,#
        """.trimIndent()

        performTest(input, output)
    }

    private fun performTest(input: String, output: String) {
        val solver = SatSolver()

        val sudokuToSolve = SudokuTextFormatParser().parseOne(input)
        val expectedResult = SudokuTextFormatParser().parseOne(output)

        val result = solver.solve(sudokuToSolve)
        assertEquals(expectedResult, result)
        assertTrue(result.isSolvedCorrectly())
    }
}