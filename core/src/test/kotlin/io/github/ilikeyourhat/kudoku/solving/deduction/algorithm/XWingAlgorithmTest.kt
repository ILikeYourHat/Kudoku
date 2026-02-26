package io.github.ilikeyourhat.kudoku.solving.deduction.algorithm

import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.model.hint.SudokuHintGrid
import io.github.ilikeyourhat.kudoku.type.Classic9x9
import io.kotest.inspectors.shouldForAll
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldNotContain
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
            at(1, 0),
            at(1, 1),
            at(1, 2),
            at(1, 4),
            at(1, 5),
            at(4, 0),
            at(4, 1),
            at(4, 2),
            at(4, 4),
            at(4, 5),
        ).shouldForAll { it.shouldContain(4) }

        XWingAlgorithm().solve(hintGrid)

        hintGrid.selectCells(
            at(1, 0),
            at(1, 1),
            at(1, 5),
            at(4, 0),
            at(4, 1),
            at(4, 5),
        ).shouldForAll { it.shouldNotContain(4) }
    }
}
