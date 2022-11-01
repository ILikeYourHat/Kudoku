package com.github.ilikeyourhat.sudokusolver.creating

import com.github.ilikeyourhat.sudokusolver.model.Sudoku
import com.github.ilikeyourhat.sudokusolver.solving.SudokuSolver
import com.github.ilikeyourhat.sudokusolver.solving.deduction.solver.DeductionSolverV1
import com.github.ilikeyourhat.sudokusolver.solving.deduction.solver.DeductionSolverV2
import com.github.ilikeyourhat.sudokusolver.solving.deduction.solver.DeductionSolverV3

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
        return when {
            easySolver.canSolve(sudoku) -> Difficulty.EASY
            mediumSolver.canSolve(sudoku) -> Difficulty.MEDIUM
            hardSolver.canSolve(sudoku) -> Difficulty.HARD
            else -> Difficulty.DIABOLIC
        }
    }

    private fun SudokuSolver.canSolve(sudoku: Sudoku): Boolean {
        return solve(sudoku).isSolved()
    }

    fun percentFilled(sudoku: Sudoku): Float {
        val fields = sudoku.allFields
        val total = fields.count()
        val notEmpty = fields.count { !it.isEmpty }
        return notEmpty.toFloat() / total
    }
}