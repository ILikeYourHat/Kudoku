package com.github.ilikeyourhat.kudoku.generating

import com.github.ilikeyourhat.kudoku.model.Sudoku
import com.github.ilikeyourhat.kudoku.rating.Difficulty
import com.github.ilikeyourhat.kudoku.rating.SudokuRater
import kotlin.random.Random

class SudokuHolePuncher(
    private val rater: SudokuRater,
    private val random: Random = Random.Default
) {

    fun punchHoles(sudoku: Sudoku, difficulty: Difficulty? = null): Sudoku {
        require(difficulty != Difficulty.INVALID)

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
                if (rater.rate(sudoku).isHarderThan(minDifficulty)) {
                    field.set(value)
                }
            }
    }
}