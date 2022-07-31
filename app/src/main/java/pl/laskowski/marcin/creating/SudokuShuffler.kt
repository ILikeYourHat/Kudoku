package pl.laskowski.marcin.creating

import pl.laskowski.marcin.model.Field
import pl.laskowski.marcin.model.Sudoku
import pl.laskowski.marcin.model.type.SudokuType
import java.util.*

class SudokuShuffler(
    private val random: Random = Random()
) {
    private val transformations = SudokuTransformations()

    fun shuffle(sudoku: Sudoku, variant: SudokuType): Sudoku {
        return sudoku.rotateAndMirror()
            .swapNumbers(variant.regionSize)
            .swapColumns(variant)
            .swapRows(variant)
    }

    fun shuffleFull(sudoku: Sudoku, variant: SudokuType): Sudoku {
        return sudoku.swapColumns(variant)
            .swapRows(variant)
    }

    private fun Sudoku.rotateAndMirror(): Sudoku {
        return when (random.nextInt(8)) {
            0 -> copy()
            1 -> transformations.rotateLeft(this)
            2 -> transformations.rotate180(this)
            3 -> transformations.rotateRight(this)
            4 -> transformations.mirrorByXAxis(this)
            5 -> transformations.mirrorByYAxis(this)
            6 -> transformations.mirrorByFirstDiagonal(this)
            7 -> transformations.mirrorBySecondDiagonal(this)
            else -> throw IllegalStateException()
        }
    }

    private fun Sudoku.swapNumbers(maxValue: Int): Sudoku {
//        val possibleValues = shuffledValues(maxValue)
//        forEach(Consumer { field: Field? ->
//            if (field != null && !field.isEmpty) {
//                val currentValue = field.value()
//                val newValue = possibleValues[currentValue - 1]
//                field.set(newValue)
//            }
//        })
        return this
    }

    private fun Sudoku.swapColumns(variant: SudokuType): Sudoku {
//        val shuffled: List<List<Field>> = shuffleGroups(columns().assumeNoNulls(), variant.blockWidth)
//        return copyWithIndexMapping { (x, y): Point ->
//            val f = shuffled[x][y]
//            Point(f.x, f.y)
//        }
        return this
    }

    private fun Sudoku.swapRows(variant: SudokuType): Sudoku {
//        val shuffled: List<List<Field>> = shuffleGroups(rows().assumeNoNulls(), variant.blockHeight)
//        return copyWithIndexMapping { (x, y): Point ->
//            val f = shuffled[y][x]
//            Point(f.x, f.y)
//        }
        return this
    }

    private fun List<List<Field?>>.assumeNoNulls(): List<List<Field>> {
        this.flatten()
            .forEach { nullableField ->
                if (nullableField == null) {
                    throw IllegalStateException("Unexpected nulls")
                }
            }
        @Suppress("UNCHECKED_CAST")
        return this as List<List<Field>>
    }

    private fun shuffledValues(to: Int): List<Int> {
        val values: MutableList<Int> = ArrayList()
        for (value in 1..to) {
            values.add(value)
        }
        values.shuffle(random)
        return values
    }

    private fun <T> shuffleGroups(origin: List<T>, size: Int): List<T> {
        val result: MutableList<T> = ArrayList()
        val groups: MutableList<MutableList<T>> = ArrayList()
        var i = 0
        while (i < origin.size) {
            groups.add(origin.subList(i, i + size).toMutableList())
            i += size
        }
        groups.shuffle(random)
        for (group in groups) {
            group.shuffle(random)
            result.addAll(group)
        }
        return result
    }
}