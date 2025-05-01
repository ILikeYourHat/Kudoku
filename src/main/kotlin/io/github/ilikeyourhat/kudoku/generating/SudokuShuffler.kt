package io.github.ilikeyourhat.kudoku.generating

import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.type.CLASSIC_TYPES
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
        val previousValues = 1..type.maxValue
        val newValues = previousValues.shuffled(random)

        cells()
            .filter { !it.isEmpty() }
            .forEach { cell ->
                val previousValueIndex = previousValues.indexOf(cell.value)
                cell.value = newValues[previousValueIndex]
            }
    }

    private fun Sudoku.shuffleIndexes(origin: Sudoku) {
        val sizeSqrt = origin.sizeSqrt()
        val shuffledX = randomIndexMapping(sizeSqrt)
        val shuffledY = randomIndexMapping(sizeSqrt)
        origin.cells().forEach { cell ->
            val targetX = shuffledX[cell.x]
            val targetY = shuffledY[cell.y]
            this[targetX, targetY] = cell.value
        }
    }

    private fun Sudoku.sizeSqrt(): Int {
        return sqrt(sizeX().toDouble()).toInt()
    }

    private fun randomIndexMapping(groupSize: Int): List<Int> {
        return IntRange(0, groupSize * groupSize - 1)
            .toList()
            .groupBy { it / groupSize }.values
            .shuffled(random)
            .map { it.shuffled(random) }
            .flatten()
    }
}
