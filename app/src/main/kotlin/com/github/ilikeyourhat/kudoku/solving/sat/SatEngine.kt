package com.github.ilikeyourhat.kudoku.solving.sat

import com.github.ilikeyourhat.kudoku.solving.SolutionCount
import org.sat4j.core.VecInt
import org.sat4j.minisat.SolverFactory
import org.sat4j.specs.ContradictionException
import org.sat4j.specs.ISolver
import org.sat4j.specs.TimeoutException
import org.sat4j.tools.SingleSolutionDetector

class SatEngine {

    private val exactlyList = mutableListOf<List<Int>>()
    private val clauseList = mutableListOf<List<Int>>()

    fun addExactly(literals: List<Int>) {
        exactlyList.add(literals)
    }

    fun addClause(literals: List<Int>) {
        clauseList.add(literals)
    }

    @Suppress("SwallowedException")
    fun findModel(): List<Int>? {
        return useSolver {
            try {
                init()
                findModel()?.asList()
            } catch (e: ContradictionException) {
                null
            } catch (e: TimeoutException) {
                null
            }
        }
    }

    @Suppress("SwallowedException")
    fun detectSolutions(): SolutionCount {
        return useSolver {
            try {
                init()
                SingleSolutionDetector(this).getSolutionCount()
            } catch (e: ContradictionException) {
                SolutionCount.NONE
            } catch (e: TimeoutException) {
                SolutionCount.NONE
            }
        }
    }

    private fun ISolver.init() {
        exactlyList.forEach { addExactly(VecInt(it.toIntArray()), 1) }
        clauseList.forEach { addClause(VecInt(it.toIntArray())) }
    }

    private fun SingleSolutionDetector.getSolutionCount(): SolutionCount {
        return when {
            !isSatisfiable -> SolutionCount.NONE
            hasASingleSolution() -> SolutionCount.ONE
            else -> SolutionCount.MANY
        }
    }

    private fun <T> useSolver(block: ISolver.() -> T): T {
        val solver = SolverFactory.newDefault()
            .apply { timeout = SOLVING_TIMEOUT_IN_SECONDS }
        try {
            return block.invoke(solver)
        } finally {
            solver.reset()
        }
    }

    companion object {
        private const val SOLVING_TIMEOUT_IN_SECONDS = 60
    }
}
