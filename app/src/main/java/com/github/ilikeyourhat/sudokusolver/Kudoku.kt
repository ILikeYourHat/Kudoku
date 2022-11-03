package com.github.ilikeyourhat.sudokusolver

import com.github.ilikeyourhat.sudokusolver.rating.Difficulty
import com.github.ilikeyourhat.sudokusolver.rating.DeductionBasedRater
import com.github.ilikeyourhat.sudokusolver.model.Sudoku
import com.github.ilikeyourhat.sudokusolver.parsing.text.SudokuTextFormatParser
import com.github.ilikeyourhat.sudokusolver.solving.SolutionCount
import com.github.ilikeyourhat.sudokusolver.solving.SudokuSolver
import com.github.ilikeyourhat.sudokusolver.solving.sat.SatSolver

object Kudoku {

    fun defaultSolver(): SudokuSolver {
        return SatSolver()
    }

    fun satSolver(): SudokuSolver {
        return SatSolver()
    }

    fun createFromString(string: String): Sudoku {
        return SudokuTextFormatParser().parseOne(string)
    }

    fun rate(sudoku: Sudoku): Difficulty {
        return DeductionBasedRater().rate(sudoku)
    }

    fun checkSolutions(sudoku: Sudoku): SolutionCount {
        return SatSolver().checkSolutions(sudoku)
    }
}