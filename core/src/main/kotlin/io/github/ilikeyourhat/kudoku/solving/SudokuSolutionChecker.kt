package io.github.ilikeyourhat.kudoku.solving

import io.github.ilikeyourhat.kudoku.model.Sudoku

/**
 * Common interface implemented by every solution checker algorithm.
 *
 * Checking the number of solutions of a Sudoku puzzle is a separate concern from solving it. The number of solutions
 * is not required to be exact, so it is represented by the enum class [SolutionCount].
 */
interface SudokuSolutionChecker {

    /**
     * Checks the number of solutions of a Sudoku puzzle.
     *
     * @param sudoku The Sudoku puzzle to check.
     * @return The number of solutions found (represented by the [SolutionCount] enum).
     */
    suspend fun checkSolutions(sudoku: Sudoku): SolutionCount

    /**
     * Checks if the Sudoku puzzle has at least one solution.
     *
     * @param sudoku The Sudoku puzzle to check.
     * @return True if the Sudoku puzzle has at least one solution, false otherwise.
     */
    suspend fun hasSolutions(sudoku: Sudoku) = checkSolutions(sudoku) != SolutionCount.ZERO
}
