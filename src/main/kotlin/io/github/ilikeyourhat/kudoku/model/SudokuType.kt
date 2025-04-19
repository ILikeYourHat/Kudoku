package io.github.ilikeyourhat.kudoku.model

import io.github.ilikeyourhat.kudoku.model.dividers.RegionDivider
import kotlin.random.Random

interface SudokuType {
    val name: String
    val sizeX: Int
    val sizeY: Int
    val maxValue: Int
    fun divider(): RegionDivider

    fun createEmpty(random: Random = Random): Sudoku {
        val board = Board(sizeX, sizeY)
        return Sudoku(
            type = this,
            board = board,
            constantRegions = divider().divide(this, board),
            randomizedRegions = emptyList()
        )
    }
}
