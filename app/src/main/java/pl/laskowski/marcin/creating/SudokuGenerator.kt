package pl.laskowski.marcin.creating

import pl.laskowski.marcin.creating.SudokuRater.Difficulty
import pl.laskowski.marcin.solving.sat.SatSolver
import pl.laskowski.marcin.model.Sudoku
import pl.laskowski.marcin.model.Field
import pl.laskowski.marcin.type.ISudokuVariant
import java.util.*
import java.util.function.Consumer

open class SudokuGenerator(
    private val variant: ISudokuVariant,
    private val difficulty: Difficulty?,
    private val percentFilled: Float?
) {
    private val random = Random()
    private val solver = SatSolver()
    private val rater = SudokuRater()

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
                } while (solver.checkSolutions(sudoku) == SudokuSolutionCount.NONE)
            }
            return sudoku
        }

    private fun iterateOverFieldsInRandomOrder(sudoku: Sudoku, consumer: Consumer<Field>) {
        sudoku.allFields
            .shuffled(random)
            .forEach { consumer.accept(it) }
    }

    private fun possibilities(variant: ISudokuVariant): MutableList<Int> {
        val values: MutableList<Int> = ArrayList()
        for (i in 1..variant.regionSize) {
            values.add(i)
        }
        return values
    }

    private fun generateHoles(sudoku: Sudoku) {
        val fields = sudoku.allFields.toMutableList()
        while (fields.isNotEmpty() && !isPercentReached(sudoku)) {
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
        return solver.checkSolutions(sudoku) == SudokuSolutionCount.ONE
    }

    private fun isPercentReached(sudoku: Sudoku): Boolean {
        return percentFilled != null && percentFilled > rater.percentFilled(sudoku)
    }

    private fun isAboveMaxDifficulty(sudoku: Sudoku): Boolean {
        return difficulty != null && rater.rate(sudoku).harderThan(difficulty)
    }
}