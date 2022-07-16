package pl.laskowski.marcin.solving

interface ConcurrentSudokuSolver : SudokuSolver {
    fun shutdown()
}