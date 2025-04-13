package io.github.ilikeyourhat.kudoku.model

import io.github.ilikeyourhat.kudoku.model.dividers.RegionDivider

interface SudokuType {
    val name: String
    val sizeX: Int
    val sizeY: Int
    val maxValue: Int
    fun divider(): RegionDivider

    fun createEmpty(): Sudoku {
        val board = Board(sizeX, sizeY)
        return Sudoku(
            type = this,
            board = board,
            regions = divider().divide(board)
        )
    }
}
