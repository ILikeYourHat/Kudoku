package io.github.ilikeyourhat.kudoku.generating

import io.github.ilikeyourhat.kudoku.model.Field
import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.solving.SolutionCount
import io.github.ilikeyourhat.kudoku.solving.SudokuSolutionChecker
import io.github.ilikeyourhat.kudoku.solving.sat.SatSolver
import kotlin.random.Random

class FilledSudokuGenerator(
    private val solutionChecker: SudokuSolutionChecker = SatSolver(),
    private val random: Random = Random.Default
) {

    fun generate(type: SudokuType): Sudoku {
        return Sudoku(type).apply {
            setRandomValidValues(this)
        }
    }

    private fun setRandomValidValues(sudoku: Sudoku) {
        sudoku.allFields
            .shuffled(random)
            .forEach { field -> setRandomValidValue(sudoku, field) }
    }

    private fun setRandomValidValue(sudoku: Sudoku, field: Field) {
        (1..sudoku.type.maxValue)
            .shuffled(random)
            .asSequence()
            .onEach { field.set(it) }
            .first { sudoku.hasSolution() }
    }

    private fun Sudoku.hasSolution(): Boolean {
        return solutionChecker.checkSolutions(this) != SolutionCount.ZERO
    }
}
