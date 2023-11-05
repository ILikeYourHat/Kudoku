package com.github.ilikeyourhat.kudoku.solving.sat

import com.github.ilikeyourhat.kudoku.model.Sudoku
import com.github.ilikeyourhat.kudoku.solving.SolutionCount
import com.github.ilikeyourhat.kudoku.type.Classic4x4
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldNotBeSameInstanceAs
import org.junit.jupiter.api.Test

class SatSolverTest {

    private val solver = SatSolver()

    @Test
    fun `should solve simple sudoku`() {
        val sudoku = Sudoku(
            Classic4x4,
            listOf(
                0, 2, 3, 0,
                1, 0, 0, 4,
                3, 0, 0, 2,
                0, 4, 1, 0
            )
        )

        val result = solver.solve(sudoku)

        result shouldBe Sudoku(
            Classic4x4,
            listOf(
                4, 2, 3, 1,
                1, 3, 2, 4,
                3, 1, 4, 2,
                2, 4, 1, 3
            )
        )
        result shouldNotBeSameInstanceAs sudoku
    }

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
        result shouldBe sudoku
        result shouldNotBeSameInstanceAs sudoku
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
        result shouldNotBeSameInstanceAs sudoku
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
