package com.github.ilikeyourhat.kudoku.creating

import com.github.ilikeyourhat.kudoku.solving.sat.SatSolver
import com.github.ilikeyourhat.kudoku.model.Sudoku
import com.github.ilikeyourhat.kudoku.model.Field
import com.github.ilikeyourhat.kudoku.model.SudokuType
import com.github.ilikeyourhat.kudoku.rating.Difficulty
import com.github.ilikeyourhat.kudoku.rating.DeductionBasedRater
import com.github.ilikeyourhat.kudoku.solving.SolutionCount
import java.util.*
import java.util.function.Consumer

open class SudokuGenerator(
    private val variant: SudokuType,
    private val difficulty: Difficulty?
) {
    private val random = Random()
    private val solver = SatSolver()
    private val rater = DeductionBasedRater()

    fun generate(): Sudoku {
        val sudoku = filledSudoku
        generateHoles(sudoku)
        return sudoku
    }

    protected open val filledSudoku: Sudoku
        get() {
            val sudoku = Sudoku(variant)
            iterateOverFieldsInRandomOrder(sudoku) { randomField ->
                val possibilities = possibilities(variant)
                do {
                    val index = random.nextInt(possibilities.size)
                    val value: Int = possibilities.removeAt(index)
                    randomField.set(value)
                } while (solver.checkSolutions(sudoku) == SolutionCount.NONE)
            }
            return sudoku
        }

    private fun iterateOverFieldsInRandomOrder(sudoku: Sudoku, consumer: Consumer<Field>) {
        sudoku.allFields
            .shuffled(random)
            .forEach { consumer.accept(it) }
    }

    private fun possibilities(variant: SudokuType): MutableList<Int> {
        val values: MutableList<Int> = ArrayList()
        for (i in 1..variant.regionSize) {
            values.add(i)
        }
        return values
    }

    private fun generateHoles(sudoku: Sudoku) {
        val fields = sudoku.allFields.toMutableList()
        while (fields.isNotEmpty()) {
            val index = random.nextInt(fields.size)
            val field = fields.removeAt(index)
            val value = field.value
            field.clear()
            if (!hasOneSolution(sudoku) || isAboveMaxDifficulty(sudoku)) {
                field.set(value)
            }
        }
    }

    private fun hasOneSolution(sudoku: Sudoku): Boolean {
        return solver.checkSolutions(sudoku) == SolutionCount.ONE
    }

    private fun isAboveMaxDifficulty(sudoku: Sudoku): Boolean {
        return difficulty != null && rater.rate(sudoku).isHarderThan(difficulty)
    }
}