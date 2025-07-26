package io.github.ilikeyourhat.kudoku.cli

import io.github.ilikeyourhat.kudoku.solving.SudokuSolver
import io.github.ilikeyourhat.kudoku.solving.bruteforce.BruteForceSolver
import io.github.ilikeyourhat.kudoku.solving.deduction.solver.DeductionSolverV3
import io.github.ilikeyourhat.kudoku.solving.sat.SatSolver

object CommandLineRegistry {

    fun getSolver(name: String): SudokuSolver? {
        return when (name.lowercase()) {
            "sat" -> SatSolver()
            "bruteforce" -> BruteForceSolver()
            "deduction" -> DeductionSolverV3()
            else -> null
        }
    }
}
