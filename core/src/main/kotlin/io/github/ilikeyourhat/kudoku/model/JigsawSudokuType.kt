package io.github.ilikeyourhat.kudoku.model

import io.github.ilikeyourhat.kudoku.model.dividers.RandomRegionsDivider
import io.github.ilikeyourhat.kudoku.model.dividers.RegionIdDivider
import kotlin.random.Random

abstract class JigsawSudokuType : SudokuType() {

    override fun createEmpty(random: Random): Sudoku {
        val board = Board(sizeX, sizeY)
        return Sudoku(
            type = this,
            board = board,
            constantRegions = divide(board),
            randomizedRegions = RandomRegionsDivider(this, random).divide(board)
        )
    }

    fun createEmpty(regionIds: List<Int>): Sudoku {
        val board = Board(sizeX, sizeY)
        return Sudoku(
            type = this,
            board = board,
            constantRegions = divide(board),
            randomizedRegions = RegionIdDivider(regionIds).divide(board)
        )
    }
}
