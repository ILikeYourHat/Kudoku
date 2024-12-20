package io.github.ilikeyourhat.kudoku.solving.sat

import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.solving.SolutionCount
import io.github.ilikeyourhat.kudoku.solving.SolverContractTestTemplate
import io.github.ilikeyourhat.kudoku.type.Classic4x4
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class SatSolverTest : SolverContractTestTemplate<SatSolver>(
    solver = SatSolver()
) {

    @Test
    fun `should not solve sudoku when there is no solution`() {
        val sudoku = Sudoku(
            Classic4x4,
            listOf(
                0, 2, 3, 4,
                1, 0, 0, 4,
                3, 0, 0, 2,
                0, 4, 1, 0
            )
        )

        val result = solver.solve(sudoku)

        result.shouldBeEqual(sudoku)
    }

    @Test
    fun `should solve sudoku when there are many solutions`() {
        val sudoku = Sudoku(
            Classic4x4,
            listOf(
                0, 2, 3, 0,
                0, 0, 0, 0,
                3, 0, 0, 0,
                0, 0, 0, 0
            )
        )

        val result = solver.solve(sudoku)
        result.isSolved().shouldBeTrue()
    }

    @Test
    fun `should detect single solution`() {
        val sudoku = Sudoku(
            Classic4x4,
            listOf(
                0, 2, 3, 0,
                1, 0, 0, 4,
                3, 0, 0, 2,
                0, 4, 1, 0
            )
        )

        solver.checkSolutions(sudoku) shouldBe SolutionCount.ONE
    }

    @Test
    fun `should detect no solutions`() {
        val sudoku = Sudoku(
            Classic4x4,
            listOf(
                0, 2, 3, 4,
                1, 0, 0, 4,
                3, 0, 0, 2,
                0, 4, 1, 0
            )
        )

        solver.checkSolutions(sudoku) shouldBe SolutionCount.ZERO
    }

    @Test
    fun `should detect many solutions`() {
        val sudoku = Sudoku(
            Classic4x4,
            listOf(
                0, 2, 3, 0,
                0, 0, 0, 0,
                3, 0, 0, 2,
                0, 0, 0, 0
            )
        )

        solver.checkSolutions(sudoku) shouldBe SolutionCount.MANY
    }
}
