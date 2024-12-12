package io.github.ilikeyourhat.kudoku.solving.sat

import io.github.ilikeyourhat.kudoku.model.Field
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

        private val indexEncoder = IndexEncoder(sudoku.sizeX(), sudoku.sizeY(), sudoku.type.possibleValues)
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
            addCausesForFields(sudoku)
        }

        private fun Sudoku.applyModel(model: List<Int>) {
            model.filter { index -> index > 0 }
                .forEach { index ->
                    val (x, y) = indexEncoder.decodePoint(index)
                    val value = indexEncoder.decodeValue(index)
                    at(x, y)!!.set(value)
                }
        }

        private fun addCausesForFields(sudoku: Sudoku) {
            for (field in sudoku.allFields) {
                engine.addExactly(createValues(field))
                if (!field.isEmpty) {
                    val index = indexEncoder.encode(field.position(), field.value())
                    engine.addClause(listOf(index))
                }
            }
        }

        private fun addCausesForRegions(sudoku: Sudoku) {
            for (possibleValue in 1..sudoku.type.possibleValues) {
                for (region in sudoku.regions) {
                    val list = mutableListOf<Int>()
                    for ((position) in region) {
                        val index = indexEncoder.encode(position, possibleValue)
                        list.add(index)
                    }
                    engine.addExactly(list)
                }
            }
        }

        private fun createValues(field: Field): List<Int> {
            val list = mutableListOf<Int>()
            for (value in 1..sudoku.type.possibleValues) {
                val index = indexEncoder.encode(field.position(), value)
                list.add(index)
            }
            return list
        }
    }
}
