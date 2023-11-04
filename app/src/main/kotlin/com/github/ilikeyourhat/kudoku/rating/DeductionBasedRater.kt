package com.github.ilikeyourhat.kudoku.rating

import com.github.ilikeyourhat.kudoku.model.Sudoku
import com.github.ilikeyourhat.kudoku.solving.SolutionCount
import com.github.ilikeyourhat.kudoku.solving.SudokuSolutionChecker
import com.github.ilikeyourhat.kudoku.solving.SudokuSolver
import com.github.ilikeyourhat.kudoku.solving.deduction.solver.DeductionSolverV1
import com.github.ilikeyourhat.kudoku.solving.deduction.solver.DeductionSolverV2
import com.github.ilikeyourhat.kudoku.solving.deduction.solver.DeductionSolverV3
import com.github.ilikeyourhat.kudoku.solving.sat.SatSolver

class DeductionBasedRater(
    private val easySolver: SudokuSolver = DeductionSolverV1(),
    private val mediumSolver: SudokuSolver = DeductionSolverV2(),
    private val hardSolver: SudokuSolver = DeductionSolverV3(),
    private val solutionChecker: SudokuSolutionChecker = SatSolver()
) : SudokuRater {

    override fun rate(sudoku: Sudoku): Difficulty {
        return when {
            easySolver.canSolve(sudoku) -> Difficulty.EASY
            mediumSolver.canSolve(sudoku) -> Difficulty.MEDIUM
            hardSolver.canSolve(sudoku) -> Difficulty.HARD
            else -> {
                when(solutionChecker.checkSolutions(sudoku)) {
                    SolutionCount.ZERO -> Difficulty.UNSOLVABLE
                    SolutionCount.ONE -> Difficulty.VERY_HARD
                    SolutionCount.MANY -> Difficulty.UNSOLVABLE
                }
            }
        }
    }

    private fun SudokuSolver.canSolve(sudoku: Sudoku): Boolean {
        return solve(sudoku).isSolved()
    }
}
