package io.github.ilikeyourhat.kudoku.generating

import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.rating.defaultRater
import io.github.ilikeyourhat.kudoku.solving.defaultSolutionChecker
import kotlin.random.Random

fun Sudoku.Companion.defaultGenerator(random: Random = Random.Default): SudokuGenerator {
    return SudokuGenerator(
        solutionChecker = Sudoku.defaultSolutionChecker(),
        rater = Sudoku.defaultRater(),
        random = random
    )
}
