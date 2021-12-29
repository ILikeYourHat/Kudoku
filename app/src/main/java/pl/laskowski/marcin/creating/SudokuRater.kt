package pl.laskowski.marcin.creating

import pl.laskowski.marcin.model.Sudoku
import pl.laskowski.marcin.solving.deduction.solver.DeductionSolverV1
import pl.laskowski.marcin.solving.deduction.solver.DeductionSolverV2
import pl.laskowski.marcin.solving.deduction.solver.DeductionSolverV3
import pl.laskowski.marcin.type.SudokuVariant

class SudokuRater(variant: SudokuVariant) {

    private var easySolver = DeductionSolverV1(variant)
    private var mediumSolver = DeductionSolverV2(variant)
    private var hardSolver = DeductionSolverV3(variant)

    enum class Difficulty {
        EASY, MEDIUM, HARD, DIABOLIC;

        fun harderThan(difficulty: Difficulty): Boolean {
            return ordinal > difficulty.ordinal
        }
    }

    fun rate(sudoku: Sudoku?): Difficulty {
        return if (easySolver.solve(sudoku).isSolved) {
            Difficulty.EASY
        } else if (mediumSolver.solve(sudoku).isSolved) {
            Difficulty.MEDIUM
        } else if (hardSolver.solve(sudoku).isSolved) {
            Difficulty.HARD
        } else {
            Difficulty.DIABOLIC
        }
    }

    fun percentFilled(sudoku: Sudoku): Float {
        val total = sudoku.allFields.count()
        val notEmpty = sudoku.allFields.count { !it.isEmpty }
        return notEmpty.toFloat() / total
    }
}