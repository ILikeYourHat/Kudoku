package io.github.ilikeyourhat.kudoku.generating

import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.rating.Difficulty
import io.github.ilikeyourhat.kudoku.rating.SudokuRater
import kotlin.random.Random

class SudokuHolePuncher(
    private val rater: SudokuRater,
    private val random: Random = Random.Default
) {

    fun punchHoles(sudoku: Sudoku, difficulty: Difficulty? = null): Sudoku {
        require(difficulty != Difficulty.UNSOLVABLE)

        val minDifficulty = difficulty ?: Difficulty.EASY
        while (true) {
            val result = sudoku.copy()
            tryToPunchHoles(result, minDifficulty)
            if (difficulty == null || result.isDesiredDifficulty(difficulty)) {
                return result
            }
        }
    }

    private fun Sudoku.isDesiredDifficulty(difficulty: Difficulty): Boolean {
        return rater.rate(this) == difficulty
    }

    private fun tryToPunchHoles(sudoku: Sudoku, minDifficulty: Difficulty) {
        sudoku.allFields
            .shuffled(random)
            .forEach { field ->
                val value = field.value
                field.clear()
                if (rater.rate(sudoku) > minDifficulty) {
                    field.set(value)
                }
            }
    }
}
