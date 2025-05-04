package io.github.ilikeyourhat.kudoku.solving

import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.solving.sat.SatSolver

fun Sudoku.Companion.defaultSolver(): SudokuSolver {
    return SatSolver()
}

fun Sudoku.Companion.defaultSolutionChecker(): SudokuSolutionChecker {
    return SatSolver()
}
