package com.github.ilikeyourhat.kudoku.generating

import com.github.ilikeyourhat.kudoku.model.Sudoku
import com.github.ilikeyourhat.kudoku.model.SudokuType
import com.github.ilikeyourhat.kudoku.rating.DeductionBasedRater
import com.github.ilikeyourhat.kudoku.rating.Difficulty
import com.github.ilikeyourhat.kudoku.rating.SudokuRater
import com.github.ilikeyourhat.kudoku.solving.SudokuSolutionChecker
import com.github.ilikeyourhat.kudoku.solving.sat.SatSolver
import com.github.ilikeyourhat.kudoku.type.CLASSIC_TYPES
import kotlin.random.Random

open class SudokuGenerator(
    solutionChecker: SudokuSolutionChecker = SatSolver(),
    rater: SudokuRater = DeductionBasedRater(),
    random: Random = Random.Default
) {
    private val filledSudokuGenerator = FilledSudokuGenerator(solutionChecker, random)
    private val classicFilledSudokuGenerator = ClassicFilledSudokuGenerator(random)
    private val holePuncher = SudokuHolePuncher(rater, random)

    fun generate(type: SudokuType, difficulty: Difficulty?): Sudoku {
        require(difficulty != Difficulty.UNSOLVABLE)
        val sudoku = generateFilledSudoku(type)
        return holePuncher.punchHoles(sudoku, difficulty)
    }

    private fun generateFilledSudoku(type: SudokuType): Sudoku {
        return if (CLASSIC_TYPES.contains(type)) {
            classicFilledSudokuGenerator.generate(type)
        } else {
            return filledSudokuGenerator.generate(type)
        }
    }
}
