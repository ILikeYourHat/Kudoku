package io.github.ilikeyourhat.kudoku.model

import io.github.ilikeyourhat.kudoku.model.dividers.BoardDivider
import kotlin.random.Random

abstract class SudokuType {
    abstract val name: String
    abstract val sizeX: Int
    abstract val sizeY: Int
    abstract val maxValue: Int
    abstract val dividers: List<BoardDivider>

    fun divide(board: Board): List<Region> {
        return dividers
            .flatMap { it.divide(board) }
            .distinct()
    }

    open fun createEmpty(random: Random = Random): Sudoku {
        val board = Board(sizeX, sizeY)
        return Sudoku(
            type = this,
            board = board,
            constantRegions = divide(board),
            randomizedRegions = emptyList()
        )
    }
}
