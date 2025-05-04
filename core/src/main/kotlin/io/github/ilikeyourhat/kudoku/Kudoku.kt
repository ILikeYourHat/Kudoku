package io.github.ilikeyourhat.kudoku

import io.github.ilikeyourhat.kudoku.generating.SudokuGenerator
import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.parsing.SingleLineSudokuParser
import io.github.ilikeyourhat.kudoku.parsing.SudokuTextFormatParser
import io.github.ilikeyourhat.kudoku.rating.DeductionBasedRater
import io.github.ilikeyourhat.kudoku.rating.Difficulty
import io.github.ilikeyourhat.kudoku.solving.SolutionCount
import io.github.ilikeyourhat.kudoku.solving.SudokuSolver
import io.github.ilikeyourhat.kudoku.solving.bruteforce.BruteForceSolver
import io.github.ilikeyourhat.kudoku.solving.sat.SatSolver
import io.github.ilikeyourhat.kudoku.type.BUILD_IN_TYPES
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

    fun bruteForceSolver(): SudokuSolver {
        return BruteForceSolver()
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

    fun createFromSingleLineString(string: String): Sudoku {
        return SingleLineSudokuParser().fromText(string)
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
