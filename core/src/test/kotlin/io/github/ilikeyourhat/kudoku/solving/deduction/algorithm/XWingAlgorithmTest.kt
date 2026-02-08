package io.github.ilikeyourhat.kudoku.solving.deduction.algorithm

import io.github.ilikeyourhat.kudoku.model.Cell
import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.model.hint.SudokuHintGrid
import io.github.ilikeyourhat.kudoku.type.Classic9x9
import io.kotest.inspectors.shouldForAll
import io.kotest.inspectors.shouldForNone
import io.kotest.matchers.collections.shouldContain
import org.junit.jupiter.api.Test

class XWingAlgorithmTest {

    @Test
    fun test() {
        val sudoku = Sudoku(
            Classic9x9, listOf(
                0, 0, 3, 8, 0, 0, 5, 1, 0,
                0, 0, 8, 7, 0, 0, 9, 3, 0,
                1, 0, 0, 3, 0, 5, 7, 2, 8,
                0, 0, 0, 2, 0, 0, 8, 4, 9,
                8, 0, 1, 9, 0, 6, 2, 5, 7,
                0, 0, 0, 5, 0, 0, 1, 6, 3,
                9, 6, 4, 1, 2, 7, 3, 8, 5,
                3, 8, 2, 6, 5, 9, 4, 7, 1,
                0, 1, 0, 4, 0, 0, 6, 9, 2
            )
        )

        val hintGrid = SudokuHintGrid.create(sudoku)
        HintEliminationAlgorithm(sudoku.regions, hintGrid).solve()

        hintGrid.selectCells(
            Cell(1, 0),
            Cell(1, 1),
            Cell(1, 2),
            Cell(1, 4),
            Cell(1, 5),
            Cell(4, 0),
            Cell(4, 1),
            Cell(4, 2),
            Cell(4, 4),
            Cell(4, 5),
        ).shouldForAll { it.shouldContain(4) }

        XWingAlgorithm().solve(sudoku, hintGrid)

        hintGrid.selectCells(
            Cell(1, 0),
            Cell(1, 1),
            Cell(1, 5),
            Cell(4, 0),
            Cell(4, 1),
            Cell(4, 5),
        ).shouldForNone { it.shouldContain(4) }
    }
}
