package io.github.ilikeyourhat.kudoku.solving.deduction.algorithm

import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.model.hint.SudokuHintGrid
import io.github.ilikeyourhat.kudoku.type.Classic4x4
import io.kotest.matchers.collections.shouldContainExactly
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
        hintGrid.apply {
            forCell(sudoku[0, 0]).shouldContainExactly(1, 3)
            forCell(sudoku[1, 0]).shouldContainExactly(3)
            forCell(sudoku[2, 0]).shouldContainExactly(1, 4)
            forCell(sudoku[3, 0]).shouldContainExactly()
            forCell(sudoku[0, 1]).shouldContainExactly()
            forCell(sudoku[1, 1]).shouldContainExactly()
            forCell(sudoku[2, 1]).shouldContainExactly(1)
            forCell(sudoku[3, 1]).shouldContainExactly()
            forCell(sudoku[0, 2]).shouldContainExactly(3, 4)
            forCell(sudoku[1, 2]).shouldContainExactly(2, 3)
            forCell(sudoku[2, 2]).shouldContainExactly(1, 2, 3, 4)
            forCell(sudoku[3, 2]).shouldContainExactly(1, 4)
            forCell(sudoku[0, 3]).shouldContainExactly(3, 4)
            forCell(sudoku[1, 3]).shouldContainExactly()
            forCell(sudoku[2, 3]).shouldContainExactly(2, 3, 4)
            forCell(sudoku[3, 3]).shouldContainExactly(4)
        }
    }
}
