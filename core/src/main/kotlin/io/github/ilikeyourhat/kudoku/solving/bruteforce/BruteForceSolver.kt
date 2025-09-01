package io.github.ilikeyourhat.kudoku.solving.bruteforce

import io.github.ilikeyourhat.kudoku.model.Cell
import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.solving.SudokuSolver
import kotlin.random.Random

class BruteForceSolver(
    private val random: Random? = null
) : SudokuSolver {

    override suspend fun solve(sudoku: Sudoku): Sudoku {
        val result = sudoku.copy()
        val lookup = RegionLookup(result)
        val cells = result.cells()
            .filter { it.isEmpty() }
            .applyRandomOrder()

        runAlgorithm(result.type, cells, lookup)
        return result
    }

    private fun runAlgorithm(
        type: SudokuType,
        cells: List<Cell>,
        lookup: RegionLookup
    ) {
        var position = 0
        loop@ while (cells.indices.contains(position)) {
            val cell = cells[position]
            for (value in cell.value..<type.maxValue) {
                cell.set(value + 1)
                if (lookup.isGridCorrectAfterChange(cell)) {
                    position++
                    continue@loop
                }
            }
            cell.clear()
            position--
        }
    }

    private fun List<Cell>.applyRandomOrder(): List<Cell> {
        return if (random != null) {
            this.shuffled(random)
        } else {
            this
        }
    }

    private class RegionLookup(sudoku: Sudoku) {

        private val regionsMap = sudoku.cells()
            .associate { cell ->
                cell.position to sudoku.regions.filter { region ->
                    region.contains(cell)
                }
            }

        fun isGridCorrectAfterChange(cell: Cell): Boolean {
            return regionsMap.getValue(cell.position)
                .all { it.isValid() }
        }
    }
}
