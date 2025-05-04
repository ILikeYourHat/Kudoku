package io.github.ilikeyourhat.kudoku.solving

/**
 * Enum representing the number of solutions found for a Sudoku puzzle.
 *
 * @property ZERO There is no solution.
 * @property ONE There is exactly one solution.
 * @property MANY There are multiple solutions (two or more).
 */
enum class SolutionCount {
    ZERO, ONE, MANY
}
