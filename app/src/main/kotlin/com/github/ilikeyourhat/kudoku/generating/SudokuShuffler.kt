package com.github.ilikeyourhat.kudoku.generating

import com.github.ilikeyourhat.kudoku.model.Sudoku
import com.github.ilikeyourhat.kudoku.type.CLASSIC_TYPES
import kotlin.math.sqrt
import kotlin.random.Random

class SudokuShuffler(
    private val random: Random = Random.Default
) {

    fun shuffleClassic(sudoku: Sudoku): Sudoku {
        require(CLASSIC_TYPES.contains(sudoku.type)) {
            "${sudoku.type.name} not supported"
        }

        return sudoku.copy().apply {
            swapNumbers()
            shuffleIndexes(sudoku)
        }
    }

    private fun Sudoku.swapNumbers() {
        val previousValues = type.allPossibleValues()
        val newValues = previousValues.shuffled(random)

        allFields
            .filter { !it.isEmpty }
            .forEach { field ->
                val previousValueIndex = previousValues.indexOf(field.value)
                field.value = newValues[previousValueIndex]
            }
    }

    private fun Sudoku.shuffleIndexes(origin: Sudoku) {
        val sizeSqrt = origin.sizeSqrt()
        val shuffledX = randomIndexMapping(sizeSqrt)
        val shuffledY = randomIndexMapping(sizeSqrt)
        origin.allFields.forEach { field ->
            val targetX = shuffledX[field.x]
            val targetY = shuffledY[field.y]
            at(targetX, targetY)!!.set(field.value)
        }
    }

    private fun Sudoku.sizeSqrt(): Int {
        return sqrt(sizeX().toDouble()).toInt()
    }

    private fun randomIndexMapping(groupSize: Int): List<Int> {
        return IntRange(0, groupSize * groupSize - 1)
            .toList()
            .groupBy { it / groupSize }.values
            .shuffled()
            .map { it.shuffled() }
            .flatten()
    }
}
