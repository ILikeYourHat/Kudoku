package io.github.ilikeyourhat.kudoku.generating

import io.github.ilikeyourhat.kudoku.model.Cell
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
        return type.createEmpty(random)
            .apply { setRandomValidValues(this) }
    }

    private fun setRandomValidValues(sudoku: Sudoku) {
        sudoku.allCells
            .shuffled(random)
            .forEach { cell -> setRandomValidValue(sudoku, cell) }
    }

    private fun setRandomValidValue(sudoku: Sudoku, cell: Cell) {
        (1..sudoku.type.maxValue)
            .shuffled(random)
            .asSequence()
            .onEach { cell.set(it) }
            .first { sudoku.hasSolution() }
    }

    private fun Sudoku.hasSolution(): Boolean {
        return solutionChecker.checkSolutions(this) != SolutionCount.ZERO
    }
}
