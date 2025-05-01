package io.github.ilikeyourhat.kudoku.solving.deduction.algorithm

import io.github.ilikeyourhat.kudoku.model.Cell
import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.model.hint.SudokuHintGrid
import io.github.ilikeyourhat.kudoku.type.Classic4x4
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class HintEliminationAlgorithmTest {

    @Test
    fun test() {
        val sudoku = Sudoku(
            Classic4x4,
            listOf(
                0, 0, 0, 2,
                2, 4, 0, 3,
                0, 0, 0, 0,
                0, 1, 0, 0
            )
        )
        val hintGrid = SudokuHintGrid.create(sudoku)
        val algorithm = HintEliminationAlgorithm(sudoku.regions, hintGrid)

        val hasChanged = algorithm.solve()

        assertTrue(hasChanged)
        assertEquals(
            hintsOf(
                sudoku[0, 0] to setOf(1, 3),
                sudoku[1, 0] to setOf(3),
                sudoku[2, 0] to setOf(1, 4),
                sudoku[3, 0] to setOf(),
                sudoku[0, 1] to setOf(),
                sudoku[1, 1] to setOf(),
                sudoku[2, 1] to setOf(1),
                sudoku[3, 1] to setOf(),
                sudoku[0, 2] to setOf(3, 4),
                sudoku[1, 2] to setOf(2, 3),
                sudoku[2, 2] to setOf(1, 2, 3, 4),
                sudoku[3, 2] to setOf(1, 4),
                sudoku[0, 3] to setOf(3, 4),
                sudoku[1, 3] to setOf(),
                sudoku[2, 3] to setOf(2, 3, 4),
                sudoku[3, 3] to setOf(4),
            ),
            hintGrid
        )
    }

    private fun hintsOf(vararg hints: Pair<Cell, Set<Int>>): SudokuHintGrid {
        return SudokuHintGrid(
            hints.associate { (cell, set) -> cell.position to set.toMutableSet() }
        )
    }
}
