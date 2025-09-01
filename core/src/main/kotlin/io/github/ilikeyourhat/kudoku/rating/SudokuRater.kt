package io.github.ilikeyourhat.kudoku.rating

import io.github.ilikeyourhat.kudoku.model.Sudoku

/**
 * Common interface implemented by every Sudoku rate algorithm.
 *
 * The difficulty is represented by the enum class [Difficulty].
 */
interface SudokuRater {

    /**
     * Rates the given Sudoku puzzle.
     *
     * @param sudoku The Sudoku puzzle to rate.
     * @return The difficulty of the Sudoku puzzle (represented by the [Difficulty] enum).
     */
    suspend fun rate(sudoku: Sudoku): Difficulty
}
