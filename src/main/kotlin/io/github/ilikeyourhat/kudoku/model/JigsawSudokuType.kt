package io.github.ilikeyourhat.kudoku.model

import io.github.ilikeyourhat.kudoku.model.dividers.RegionDivider
import kotlin.random.Random

interface JigsawSudokuType : SudokuType {

    override fun createEmpty(random: Random): Sudoku {
        val board = Board(sizeX, sizeY)
        return Sudoku(
            type = this,
            board = board,
            constantRegions = divider()
                .divide(this, board),
            randomizedRegions = RegionDivider()
                .divideRandomly(random)
                .divide(this, board)
        )
    }

    fun createEmpty(regionIds: List<Int>): Sudoku {
        val board = Board(sizeX, sizeY)
        return Sudoku(
            type = this,
            board = board,
            constantRegions = divider()
                .divide(this, board),
            randomizedRegions = RegionDivider()
                .divideByRegionId(regionIds)
                .divide(this, board)
        )
    }
}
