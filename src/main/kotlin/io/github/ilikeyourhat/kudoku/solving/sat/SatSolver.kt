package io.github.ilikeyourhat.kudoku.solving.sat

import io.github.ilikeyourhat.kudoku.model.Cell
import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.solving.SolutionCount
import io.github.ilikeyourhat.kudoku.solving.SudokuSolutionChecker
import io.github.ilikeyourhat.kudoku.solving.SudokuSolver

class SatSolver : SudokuSolver, SudokuSolutionChecker {

    override fun solve(sudoku: Sudoku): Sudoku {
        return Command(sudoku).solve()
    }

    override fun checkSolutions(sudoku: Sudoku): SolutionCount {
        return Command(sudoku).checkSolutions()
    }

    private class Command(private val sudoku: Sudoku) {

        private val indexEncoder = IndexEncoder(sudoku.sizeX(), sudoku.sizeY(), sudoku.type.maxValue)
        private val engine = SatEngine()

        fun solve(): Sudoku {
            initEngine(sudoku)
            val model = engine.findModel()
            val result = sudoku.copy()
            if (model != null) {
                result.applyModel(model)
            }
            return result
        }

        fun checkSolutions(): SolutionCount {
            initEngine(sudoku)
            return engine.detectSolutions()
        }

        private fun initEngine(sudoku: Sudoku) {
            addCausesForRegions(sudoku)
            addCausesForCells(sudoku)
        }

        private fun Sudoku.applyModel(model: List<Int>) {
            model.filter { index -> index > 0 }
                .forEach { index ->
                    val (x, y) = indexEncoder.decodePosition(index)
                    this[x, y] = indexEncoder.decodeValue(index)
                }
        }

        private fun addCausesForCells(sudoku: Sudoku) {
            for (cell in sudoku.cells()) {
                engine.addExactly(createValues(cell))
                if (!cell.isEmpty()) {
                    val index = indexEncoder.encode(cell.position, cell.value)
                    engine.addClause(listOf(index))
                }
            }
        }

        private fun addCausesForRegions(sudoku: Sudoku) {
            for (possibleValue in 1..sudoku.type.maxValue) {
                for (region in sudoku.regions) {
                    val list = mutableListOf<Int>()
                    for (cell in region) {
                        val index = indexEncoder.encode(cell.position, possibleValue)
                        list.add(index)
                    }
                    engine.addExactly(list)
                }
            }
        }

        private fun createValues(cell: Cell): List<Int> {
            val list = mutableListOf<Int>()
            for (value in 1..sudoku.type.maxValue) {
                val index = indexEncoder.encode(cell.position, value)
                list.add(index)
            }
            return list
        }
    }
}
