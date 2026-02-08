package io.github.ilikeyourhat.kudoku.solving

import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.type.Classic4x4
import io.github.ilikeyourhat.kudoku.type.Classic9x9
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.types.shouldNotBeSameInstanceAs
import org.junit.jupiter.api.Test
import kotlinx.coroutines.test.runTest

@Suppress("AbstractClassCanBeConcreteClass") // JUnit5 treats this class as a test suite when it is not abstract
abstract class SolverContractTestTemplate<Solver : SudokuSolver>(
    protected val solver: Solver
) {

    @Test
    fun `should find solution for simple sudoku`() = runTest {
        val input = sudoku.copy()
        val result = solver.solve(input)

        result.shouldNotBeSameInstanceAs(input)
        sudoku.shouldBeEqual(input)
        result.isSolved().shouldBeTrue()
    }

    @Test
    fun `should just return the progress on sudoku when not finding solution`() = runTest {
        val input = brokenSudoku.copy()
        val result = solver.solve(input)

        result.shouldNotBeSameInstanceAs(brokenSudoku)
        brokenSudoku.shouldBeEqual(input)
        result.isSolved().shouldBeFalse()
    }

    @Test
    fun `should handle already solved sudoku`() = runTest {
        val input = solvedSudoku.copy()
        val result = solver.solve(input)

        result.shouldNotBeSameInstanceAs(solvedSudoku)
        solvedSudoku.shouldBeEqual(input)
    }

    @Test
    fun `should find solution for challenge sudoku`() = runTest {
        val result = solver.solve(challengeSudoku)
        result.isSolved().shouldBeTrue()
    }

    private companion object {
        val sudoku = Sudoku(
            Classic4x4,
            listOf(
                0, 2, 3, 0,
                1, 0, 0, 4,
                3, 0, 0, 2,
                0, 4, 1, 0
            )
        )
        val brokenSudoku = Sudoku(
            Classic4x4,
            listOf(
                2, 2, 3, 0,
                1, 0, 0, 4,
                3, 0, 0, 2,
                0, 4, 1, 0
            )
        )
        val solvedSudoku = Sudoku(
            Classic4x4,
            listOf(
                4, 2, 3, 1,
                1, 3, 2, 4,
                3, 1, 4, 2,
                2, 4, 1, 3
            )
        )
        val challengeSudoku = Sudoku(
            Classic9x9,
            listOf(
                0, 0, 3, 0, 2, 0, 6, 0, 0,
                9, 0, 0, 3, 0, 5, 0, 0, 1,
                0, 0, 1, 8, 0, 6, 4, 0, 0,
                0, 0, 8, 1, 0, 2, 9, 0, 0,
                7, 0, 0, 0, 0, 0, 0, 0, 8,
                0, 0, 6, 7, 0, 8, 2, 0, 0,
                0, 0, 2, 6, 0, 9, 5, 0, 0,
                8, 0, 0, 2, 0, 3, 0, 0, 9,
                0, 0, 5, 0, 1, 0, 3, 0, 0
            )
        )
    }
}
