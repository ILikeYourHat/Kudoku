package com.github.ilikeyourhat.sudokusolver

import com.github.ilikeyourhat.sudokusolver.model.Sudoku
import com.github.ilikeyourhat.sudokusolver.parsing.text.SudokuTextFormatParser
import com.github.ilikeyourhat.sudokusolver.solving.SudokuSolver
import com.github.ilikeyourhat.sudokusolver.solving.sat.SatSolver

object Kudoku {

    fun defaultSolver(): SudokuSolver {
        return SatSolver()
    }

    fun satSolver(): SudokuSolver {
        return SatSolver()
    }

    fun sudokuFromString(string: String): Sudoku {
        return SudokuTextFormatParser().parseOne(string)
    }
}