package io.github.ilikeyourhat.kudoku.solving

import io.github.ilikeyourhat.kudoku.model.Sudoku

/**
 * Common interface implemented by every solver algorithm.
 *
 * To accommodate different solving algorithms, each solver implementation must comply with the following rules:
 * * The solver would not alter the input Sudoku in any way.
 * * The solver would return a new Sudoku instance, that is a copy of the input Sudoku, with the solution applied.
 * * Not all solvers will be able to solve all Sudoku puzzles. In this case the solver would return the current
 * solving progress (if the solver is sure of it) or the copy of input Sudoku without changes.
 * * In case of a sudoku with multiple solutions, the solver should return the first found solution. Note that not all
 * solvers are able to solve a Sudoku with multiple solutions.
 * * In case of a sudoku with no solutions (including invalid ones), the solver should behave as if it could not find
 * a solution.
 * * In case of an already solved Sudoku, the solver should just return a copy of the input Sudoku.
 *
 * To check the number of solutions of a Sudoku, use the [SudokuSolutionChecker] interface.
 */
interface SudokuSolver {

    /**
     * Tries to solve the given Sudoku puzzle.
     *
     * @param sudoku The Sudoku puzzle to solve.
     * @return The output Sudoku, might be solved or not.
     */
    fun solve(sudoku: Sudoku): Sudoku
}
