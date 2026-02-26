package io.github.ilikeyourhat.kudoku.model.hint

import io.github.ilikeyourhat.kudoku.model.Cell
import io.github.ilikeyourhat.kudoku.model.Region
import io.github.ilikeyourhat.kudoku.model.Sudoku

data class SudokuHintGrid(
    val sudoku: Sudoku,
    private val hintMap: Map<Pair<Int, Int>, MutableSet<Int>>
) {

    private val rows = sudoku.regions
        .filter { region -> region.all { cell -> region.first().y == cell.y } }
        .sortedBy { it.cells.first().x }

    private val columns = sudoku.regions
        .filter { region -> region.all { cell -> region.first().x == cell.x } }
        .sortedBy { it.cells.first().y }

    fun forCell(cell: Cell): Set<Int> {
        return getFor(cell).toSet()
    }

    fun forRegion(region: Region): Set<Int> {
        return region.cells
            .fold(emptySet()) { set, cell -> set + getFor(cell) }
    }

    fun isEmpty(cell: Cell): Boolean {
        return getFor(cell).isEmpty()
    }

    fun clear(cell: Cell) {
        getFor(cell).clear()
    }

    fun remove(cell: Cell, value: Int): Boolean {
        return getFor(cell).remove(value)
    }

    fun remove(region: Region, value: Int): Boolean {
        var removed = false
        for (cell in region) {
            removed = removed or getFor(cell).remove(value)
        }
        return removed
    }

    fun contains(cell: Cell, value: Int): Boolean {
        return getFor(cell).contains(value)
    }

    fun replace(cell: Cell, hints: Set<Int>) {
        getFor(cell).apply {
            clear()
            addAll(hints)
        }
    }

    fun removeAll(cell: Cell, hintsToRemove: Set<Int>): Boolean {
        return getFor(cell).removeAll(hintsToRemove)
    }

    private fun getFor(cell: Cell): MutableSet<Int> {
        return hintMap.getValue(cell.position)
    }

    fun missingNumbers(): List<Int> {
        return hintMap.values
            .flatten()
            .distinct()
            .sorted()
    }

    fun column(x: Int) = columns[x]

    fun columns() = columns

    fun row(y: Int) = rows[y]

    fun rows() = rows

    companion object {

        fun create(sudoku: Sudoku): SudokuHintGrid {
            val possibleValues = 1..sudoku.type.maxValue

            val hintMap = sudoku.cells()
                .map { it.position }
                .associateWith { possibleValues.toMutableSet() }

            return SudokuHintGrid(sudoku, hintMap)
        }
    }
}
