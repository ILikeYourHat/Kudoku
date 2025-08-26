package io.github.ilikeyourhat.kudoku.rating

/**
 * Enum representing the difficulty level of a Sudoku puzzle.
 *
 * @property EASY The puzzle is easy to solve.
 * @property MEDIUM The puzzle has a medium difficulty level.
 * @property HARD The puzzle is hard to solve.
 * @property VERY_HARD The puzzle is very hard to solve.
 * @property UNSOLVABLE The puzzle cannot be solved (it has zero solutions).
 */
enum class Difficulty {
    EASY, MEDIUM, HARD, VERY_HARD, UNSOLVABLE;

    override fun toString() = name.lowercase()
}
