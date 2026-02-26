package io.github.ilikeyourhat.kudoku.solving.deduction.algorithm

import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.model.hint.SudokuHintGrid

fun Sudoku.prepareHintGrid(): SudokuHintGrid {
    val hintGrid = SudokuHintGrid.create(this)
    HintEliminationAlgorithm(regions, hintGrid).solve()
    return hintGrid
}

fun SudokuHintGrid.selectCells(vararg coordinates: Pair<Int, Int>): List<Set<Int>> {
    return coordinates.map { getHintsFor(it) }
}

fun at(x: Int, y: Int) = x to y
