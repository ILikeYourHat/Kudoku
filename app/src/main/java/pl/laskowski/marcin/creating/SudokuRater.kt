package pl.laskowski.marcin.creating

import pl.laskowski.marcin.model.Sudoku
import pl.laskowski.marcin.solving.deduction.solver.DeductionSolverV1
import pl.laskowski.marcin.solving.deduction.solver.DeductionSolverV2
import pl.laskowski.marcin.solving.deduction.solver.DeductionSolverV3

class SudokuRater {

    private var easySolver = DeductionSolverV1()
    private var mediumSolver = DeductionSolverV2()
    private var hardSolver = DeductionSolverV3()

    enum class Difficulty {
        EASY, MEDIUM, HARD, DIABOLIC;

        fun harderThan(difficulty: Difficulty): Boolean {
            return ordinal > difficulty.ordinal
        }
    }

    fun rate(sudoku: Sudoku): Difficulty {
        return if (easySolver.solve(sudoku).isSolved()) {
            Difficulty.EASY
        } else if (mediumSolver.solve(sudoku).isSolved()) {
            Difficulty.MEDIUM
        } else if (hardSolver.solve(sudoku).isSolved()) {
            Difficulty.HARD
        } else {
            Difficulty.DIABOLIC
        }
    }

    fun percentFilled(sudoku: Sudoku): Float {
        val fields = sudoku.allFields
        val total = fields.count()
        val notEmpty = fields.count { !it.isEmpty }
        return notEmpty.toFloat() / total
    }
}