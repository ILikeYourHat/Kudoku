package io.github.ilikeyourhat.kudoku.solving.deduction.algorithm

import io.github.ilikeyourhat.kudoku.model.Cell
import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.model.hint.SudokuHintGrid

fun Sudoku.prepareHintGrid(): SudokuHintGrid {
    val hintGrid = SudokuHintGrid.create(this)
    HintEliminationAlgorithm(regions, hintGrid).solve()
    return hintGrid
}

fun SudokuHintGrid.selectCells(vararg cells: Cell): List<Set<Int>> {
    return cells.map { forCell(it) }
}
