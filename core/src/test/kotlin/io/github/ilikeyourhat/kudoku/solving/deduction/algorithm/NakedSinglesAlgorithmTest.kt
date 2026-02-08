package io.github.ilikeyourhat.kudoku.solving.deduction.algorithm

import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.type.Classic4x4
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class NakedSinglesAlgorithmTest {

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
        val hintGrid = sudoku.prepareHintGrid()

        val algorithm = NakedSinglesAlgorithm(sudoku.regions, hintGrid)

        val hasChanged = algorithm.solve()

        val expected = Sudoku(
            Classic4x4,
            listOf(
                0, 3, 0, 2,
                2, 4, 0, 3,
                0, 0, 0, 0,
                0, 1, 0, 0
            )
        )

        assertEquals(expected, sudoku)
        assertTrue(hasChanged)
    }
}
