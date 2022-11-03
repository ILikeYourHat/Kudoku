package com.github.ilikeyourhat.kudoku.solving.sat

import org.sat4j.core.VecInt
import org.sat4j.minisat.SolverFactory
import org.sat4j.specs.ContradictionException
import org.sat4j.specs.ISolver
import org.sat4j.specs.TimeoutException
import org.sat4j.tools.SingleSolutionDetector
import com.github.ilikeyourhat.kudoku.solving.SolutionCount
import com.github.ilikeyourhat.kudoku.model.Field
import com.github.ilikeyourhat.kudoku.model.Sudoku
import com.github.ilikeyourhat.kudoku.solving.SudokuSolutionChecker
import com.github.ilikeyourhat.kudoku.solving.SudokuSolver

class SatSolver: SudokuSolver, SudokuSolutionChecker {

    override fun solve(sudoku: Sudoku): Sudoku {
        return Command(sudoku).solve()
    }

    override fun checkSolutions(sudoku: Sudoku): SolutionCount {
        return Command(sudoku).checkSolutions()
    }

    private inner class Command(private val sudoku: Sudoku) {
        private val ie: IndexEncoder = IndexEncoder(sudoku.sizeX(), sudoku.sizeY())
        private val solver: ISolver = SolverFactory.newDefault()
            .apply { timeout = 60 }

        fun solve(): Sudoku {
            return try {
                initSolver()
                val model = solver.findModel()
                if (model != null) {
                    applyResult(model)
                } else {
                    sudoku.copy()
                }
            } catch (e: ContradictionException) {
                sudoku.copy()
            } catch (e: TimeoutException) {
                sudoku.copy()
            } finally {
                solver.reset()
            }
        }

        fun checkSolutions(): SolutionCount {
            return try {
                initSolver()
                val solutionDetector = SingleSolutionDetector(solver)
                if (solutionDetector.isSatisfiable) {
                    if (solutionDetector.hasASingleSolution()) {
                        SolutionCount.ONE
                    } else {
                        SolutionCount.MANY
                    }
                } else {
                    SolutionCount.NONE
                }
            } catch (e: ContradictionException) {
                SolutionCount.NONE
            } catch (e: TimeoutException) {
                SolutionCount.NONE
            } finally {
                solver.reset()
            }
        }

        @Throws(ContradictionException::class)
        private fun initSolver() {
            addCausesForRegions()
            addCausesForFields()
        }

        private fun applyResult(result: IntArray): Sudoku {
            val solution = sudoku.copy()
            for (index in result) {
                if (index > 0) {
                    val p = ie.decodePoint(index)
                    val v = ie.decodeValue(index)
                    solution.at(p)!!.set(v)
                }
            }
            return solution
        }

        @Throws(ContradictionException::class)
        private fun addCausesForFields() {
            for (field in sudoku.allFields) {
                solver.addExactly(createValues(field), 1)
                if (!field.isEmpty) {
                    val index = ie.encode(field.position(), field.value())
                    solver.addClause(VecInt(intArrayOf(index)))
                }
            }
        }

        @Throws(ContradictionException::class)
        private fun addCausesForRegions() {
            for (possibleValue in 1..sudoku.type.regionSize) {
                for (region in sudoku.regions) {
                    val vec = VecInt(region.size())
                    for ((position) in region) {
                        val index = ie.encode(position, possibleValue)
                        vec.push(index)
                    }
                    solver.addExactly(vec, 1)
                }
            }
        }

        private fun createValues(field: Field): VecInt {
            val vec = VecInt(sudoku.type.regionSize)
            for (value in 1..sudoku.type.regionSize) {
                val index = ie.encode(field.position(), value)
                vec.push(index)
            }
            return vec
        }
    }
}