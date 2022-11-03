package com.github.ilikeyourhat.sudokusolver.rating

import com.github.ilikeyourhat.sudokusolver.model.Sudoku
import com.github.ilikeyourhat.sudokusolver.solving.SolutionCount
import com.github.ilikeyourhat.sudokusolver.solving.SudokuSolver
import com.github.ilikeyourhat.sudokusolver.solving.deduction.solver.DeductionSolverV1
import com.github.ilikeyourhat.sudokusolver.solving.deduction.solver.DeductionSolverV2
import com.github.ilikeyourhat.sudokusolver.solving.deduction.solver.DeductionSolverV3
import com.github.ilikeyourhat.sudokusolver.solving.sat.SatSolver

class DeductionBasedRater : SudokuRater {

    private val easySolver = DeductionSolverV1()
    private val mediumSolver = DeductionSolverV2()
    private val hardSolver = DeductionSolverV3()
    private val solutionsChecker = SatSolver()

    override fun rate(sudoku: Sudoku): Difficulty {
        return when {
            easySolver.canSolve(sudoku) -> Difficulty.EASY
            mediumSolver.canSolve(sudoku) -> Difficulty.MEDIUM
            hardSolver.canSolve(sudoku) -> Difficulty.HARD
            else -> {
                when(solutionsChecker.checkSolutions(sudoku)) {
                    SolutionCount.NONE -> Difficulty.INVALID
                    SolutionCount.ONE -> Difficulty.VERY_HARD
                    SolutionCount.MANY -> Difficulty.INVALID
                }
            }
        }
    }

    private fun SudokuSolver.canSolve(sudoku: Sudoku): Boolean {
        return solve(sudoku).isSolved()
    }
}