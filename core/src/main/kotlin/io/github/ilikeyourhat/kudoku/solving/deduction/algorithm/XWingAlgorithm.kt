package io.github.ilikeyourhat.kudoku.solving.deduction.algorithm

import io.github.ilikeyourhat.kudoku.model.Cell
import io.github.ilikeyourhat.kudoku.model.Region
import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.model.hint.SudokuHintGrid

class XWingAlgorithm {

    @Suppress("LoopWithTooManyJumpStatements")
    fun solve(
        sudoku: Sudoku,
        hints: SudokuHintGrid
    ) {
        var detection: Detection? = null

        for (number in hints.missingNumbers()) {
            detection = searchForDetection(number, sudoku.rows(), hints, Direction.ROWS, sudoku)
            if (detection != null) break
            detection = searchForDetection(number, sudoku.columns(), hints, Direction.COLUMNS, sudoku)
            if (detection != null) break
        }

        if (detection != null) {
            applyDetection(hints, detection)
        }
    }

    private fun searchForDetection(
        number: Int,
        regions: List<Region>,
        hints: SudokuHintGrid,
        direction: Direction,
        sudoku: Sudoku
    ): Detection? {
        val interestingRegions = regions.filter { region ->
            countHints(number, region, hints) == 2
        }
        return interestingRegions.eachWithEachOther()
            .firstNotNullOfOrNull { (regionA, regionB) ->
                checkForXWing(number, regionA, regionB, direction, hints, sudoku)
            }
    }

    @Suppress("ReturnCount", "LongParameterList")
    private fun checkForXWing(
        number: Int,
        regionA: Region,
        regionB: Region,
        direction: Direction,
        hints: SudokuHintGrid,
        sudoku: Sudoku
    ): Detection? {
        val (a1, a2) = regionA.filter { hints.forCell(it).contains(number) }
        val (b1, b2) = regionB.filter { hints.forCell(it).contains(number) }

        when (direction) {
            Direction.ROWS -> {
                if (a1.x == b1.x && a2.x == b2.x) {
                    val interestingColumns = listOf(
                        sudoku.column(a1.x),
                        sudoku.column(b2.x)
                    )
                    if (interestingColumns.any { countHints(number, it, hints) > 2 }) {
                        return Detection(
                            number = number,
                            xWingCells = listOf(a1, a2, b1, b2),
                            regionsToClear = interestingColumns
                        )
                    }
                }
            }

            Direction.COLUMNS -> {
                if (a1.y == b1.y && a2.y == b2.y) {
                    val interestingRows = listOf(
                        sudoku.row(a1.y),
                        sudoku.row(b2.y)
                    )
                    if (interestingRows.any { countHints(number, it, hints) > 2 }) {
                        return Detection(
                            number = number,
                            xWingCells = listOf(a1, a2, b1, b2),
                            regionsToClear = interestingRows
                        )
                    }
                }
            }
        }
        return null
    }

    private fun countHints(number: Int, region: Region, hints: SudokuHintGrid): Int {
        return region.flatMap { hints.forCell(it) }.count { it == number }
    }

    private fun applyDetection(hints: SudokuHintGrid, detection: Detection) {
        detection.regionsToClear.flatten()
            .forEach { cell ->
                if (!detection.xWingCells.contains(cell)) {
                    hints.remove(cell, detection.number)
                }
            }
    }

    private class Detection(
        val number: Int,
        val xWingCells: List<Cell>,
        val regionsToClear: List<Region>
    )

    private enum class Direction {
        ROWS, COLUMNS
    }

    private fun <T> Iterable<T>.eachWithEachOther(): List<Pair<T, T>> {
        val list = mutableListOf<Pair<T, T>>()
        for (x in 0..<count()) {
            for (y in 0..<count()) {
                if (x < y) list += elementAt(x) to elementAt(y)
            }
        }
        return list
    }

    private fun Sudoku.column(x: Int): Region {
        return regions.first { region -> region.all { cell -> cell.x == x } }
    }

    private fun Sudoku.columns(): List<Region> {
        return regions
            .filter { region -> region.all { cell -> region.first().x == cell.x } }
    }

    private fun Sudoku.row(y: Int): Region {
        return regions.first { region -> region.all { cell -> cell.y == y } }
    }

    private fun Sudoku.rows(): List<Region> {
        return regions
            .filter { region -> region.all { cell -> region.first().y == cell.y } }
    }
}
