package com.github.ilikeyourhat.sudokusolver.solving

interface ConcurrentSudokuSolver : SudokuSolver {
    fun shutdown()
}