package com.github.ilikeyourhat.kudoku

import com.github.ilikeyourhat.kudoku.generating.SudokuGenerator
import com.github.ilikeyourhat.kudoku.rating.Difficulty
import com.github.ilikeyourhat.kudoku.rating.DeductionBasedRater
import com.github.ilikeyourhat.kudoku.model.Sudoku
import com.github.ilikeyourhat.kudoku.model.SudokuType
import com.github.ilikeyourhat.kudoku.parsing.text.SudokuTextFormatParser
import com.github.ilikeyourhat.kudoku.solving.SolutionCount
import com.github.ilikeyourhat.kudoku.solving.SudokuSolver
import com.github.ilikeyourhat.kudoku.solving.sat.SatSolver
import com.github.ilikeyourhat.kudoku.type.BUILD_IN_TYPES
import kotlin.random.Random

object Kudoku {

    private val supportedTypes = mutableListOf<SudokuType>()
        .apply { addAll(BUILD_IN_TYPES) }

    fun defaultSolver(): SudokuSolver {
        return SatSolver()
    }

    fun satSolver(): SudokuSolver {
        return SatSolver()
    }

    fun create(
        type: SudokuType,
        difficulty: Difficulty? = null,
        random: Random = Random.Default
    ): Sudoku {
        return SudokuGenerator(
            solutionChecker = SatSolver(),
            rater = DeductionBasedRater(),
            random = random
        ).generate(type, difficulty)
    }

    fun createFromString(string: String): Sudoku {
        return SudokuTextFormatParser(supportedTypes).parseOne(string)
    }

    fun rate(sudoku: Sudoku): Difficulty {
        return DeductionBasedRater().rate(sudoku)
    }

    fun checkSolutions(sudoku: Sudoku): SolutionCount {
        return SatSolver().checkSolutions(sudoku)
    }

    fun registerType(sudokuType: SudokuType) {
        supportedTypes.add(sudokuType)
    }
}
