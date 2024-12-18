package io.github.ilikeyourhat.kudoku.solving.deduction.algorithm

import io.github.ilikeyourhat.kudoku.model.Point
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
                Point(0, 0) to setOf(1, 3),
                Point(1, 0) to setOf(3),
                Point(2, 0) to setOf(1, 4),
                Point(3, 0) to setOf(),
                Point(0, 1) to setOf(),
                Point(1, 1) to setOf(),
                Point(2, 1) to setOf(1),
                Point(3, 1) to setOf(),
                Point(0, 2) to setOf(3, 4),
                Point(1, 2) to setOf(2, 3),
                Point(2, 2) to setOf(1, 2, 3, 4),
                Point(3, 2) to setOf(1, 4),
                Point(0, 3) to setOf(3, 4),
                Point(1, 3) to setOf(),
                Point(2, 3) to setOf(2, 3, 4),
                Point(3, 3) to setOf(4),
            ),
            hintGrid
        )
    }

    private fun hintsOf(vararg points: Pair<Point, Set<Int>>): SudokuHintGrid {
        return SudokuHintGrid(
            points.associate { (p, s) -> p to s.toMutableSet() }
        )
    }
}
