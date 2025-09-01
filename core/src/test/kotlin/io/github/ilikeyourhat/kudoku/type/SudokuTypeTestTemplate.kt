package io.github.ilikeyourhat.kudoku.type

import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.parsing.createFromString
import io.github.ilikeyourhat.kudoku.solving.defaultSolver
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.equals.shouldBeEqual
import org.junit.jupiter.api.Test
import kotlinx.coroutines.test.runTest

abstract class SudokuTypeTestTemplate {

    abstract val puzzle: String
    abstract val solution: String

    @Test
    fun `should solve it correctly`() = runTest {
        val solver = Sudoku.defaultSolver()
        val sudokuToSolve = Sudoku.createFromString(puzzle)
        val expectedResult = Sudoku.createFromString(solution)

        val result = solver.solve(sudokuToSolve)

        result.shouldBeEqual(expectedResult)
        result.isSolved().shouldBeTrue()
    }
}
