package io.github.ilikeyourhat.kudoku.rating

import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.solving.SolutionCount
import io.github.ilikeyourhat.kudoku.solving.SudokuSolutionChecker
import io.github.ilikeyourhat.kudoku.solving.SudokuSolver
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

class DeductionBasedRaterTest {

    private val inputSudoku: Sudoku = mockk()
    private val solvedSudoku: Sudoku = mockk {
        every { isSolved() } returns true
    }
    private val unsolvedSudoku: Sudoku = mockk {
        every { isSolved() } returns false
    }

    private val easySolver: SudokuSolver = mockk {
        every { solve(any()) } returns unsolvedSudoku
    }
    private val mediumSolver: SudokuSolver = mockk {
        every { solve(any()) } returns unsolvedSudoku
    }
    private val hardSolver: SudokuSolver = mockk {
        every { solve(any()) } returns unsolvedSudoku
    }
    private val solutionChecker: SudokuSolutionChecker = mockk()

    private val rater = DeductionBasedRater(
        easySolver = easySolver,
        mediumSolver = mediumSolver,
        hardSolver = hardSolver,
        solutionChecker = solutionChecker
    )

    @Test
    fun `should classify sudoku as easy`() {
        every { easySolver.solve(inputSudoku) } returns solvedSudoku

        val difficulty = rater.rate(inputSudoku)

        difficulty shouldBe Difficulty.EASY
    }

    @Test
    fun `should classify sudoku as medium`() {
        every { mediumSolver.solve(inputSudoku) } returns solvedSudoku

        val difficulty = rater.rate(inputSudoku)

        difficulty shouldBe Difficulty.MEDIUM
    }

    @Test
    fun `should classify sudoku as hard`() {
        every { hardSolver.solve(inputSudoku) } returns solvedSudoku

        val difficulty = rater.rate(inputSudoku)

        difficulty shouldBe Difficulty.HARD
    }

    @Test
    fun `should classify sudoku as very hard`() {
        every { solutionChecker.checkSolutions(inputSudoku) } returns SolutionCount.ONE

        val difficulty = rater.rate(inputSudoku)

        difficulty shouldBe Difficulty.VERY_HARD
    }

    @Test
    fun `should classify sudoku with no solutions as unsolvable`() {
        every { solutionChecker.checkSolutions(inputSudoku) } returns SolutionCount.ZERO

        val difficulty = rater.rate(inputSudoku)

        difficulty shouldBe Difficulty.UNSOLVABLE
    }

    @Test
    fun `should classify sudoku with multiple solutions as unsolvable`() {
        every { solutionChecker.checkSolutions(inputSudoku) } returns SolutionCount.MANY

        val difficulty = rater.rate(inputSudoku)

        difficulty shouldBe Difficulty.UNSOLVABLE
    }
}
