package io.github.ilikeyourhat.kudoku.solving.deduction.solver

import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.model.hint.SudokuHintGrid
import io.github.ilikeyourhat.kudoku.solving.SudokuSolver
import io.github.ilikeyourhat.kudoku.solving.deduction.algorithm.DeductionAlgorithm

abstract class DeductionSolver : SudokuSolver {

    protected abstract fun provideAlgorithms(type: SudokuType): List<DeductionAlgorithm.Factory>

    override fun solve(sudoku: Sudoku): Sudoku {
        val copy = sudoku.copy()
        val sudokuHintGrid = SudokuHintGrid.create(copy)
        return solve(copy, sudokuHintGrid)
    }

    private fun solve(sudoku: Sudoku, sudokuHintGrid: SudokuHintGrid): Sudoku {
        val algorithmFactories = provideAlgorithms(sudoku.type)
        var gridHasChanged: Boolean
        do {
            gridHasChanged = false
            for (factory in algorithmFactories) {
                if (factory.instance(sudoku.regions, sudokuHintGrid).solve()) {
                    gridHasChanged = true
                    break
                }
            }
        } while (gridHasChanged && !sudoku.isCompleted())
        return sudoku
    }
}
