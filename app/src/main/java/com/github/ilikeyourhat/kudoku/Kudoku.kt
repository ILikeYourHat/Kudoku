package com.github.ilikeyourhat.kudoku

import com.github.ilikeyourhat.kudoku.rating.Difficulty
import com.github.ilikeyourhat.kudoku.rating.DeductionBasedRater
import com.github.ilikeyourhat.kudoku.model.Sudoku
import com.github.ilikeyourhat.kudoku.parsing.text.SudokuTextFormatParser
import com.github.ilikeyourhat.kudoku.solving.SolutionCount
import com.github.ilikeyourhat.kudoku.solving.SudokuSolver
import com.github.ilikeyourhat.kudoku.solving.sat.SatSolver

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