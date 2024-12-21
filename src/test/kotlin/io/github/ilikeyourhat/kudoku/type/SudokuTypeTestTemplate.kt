package io.github.ilikeyourhat.kudoku.type

import io.github.ilikeyourhat.kudoku.Kudoku
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.equals.shouldBeEqual
import org.junit.jupiter.api.Test

abstract class SudokuTypeTestTemplate {

    abstract val puzzle: String
    abstract val solution: String

    @Test
    fun `should solve it correctly`() {
        val solver = Kudoku.defaultSolver()
        val sudokuToSolve = Kudoku.createFromString(puzzle)
        val expectedResult = Kudoku.createFromString(solution)

        val result = solver.solve(sudokuToSolve)

        result.shouldBeEqual(expectedResult)
        result.isSolved().shouldBeTrue()
    }
}
