package io.github.ilikeyourhat.kudoku.model

import kotlin.random.Random

interface JigsawSudokuType : SudokuType {

    override fun createEmpty(random: Random): Sudoku {
        val board = Board(sizeX, sizeY)
        return Sudoku(
            type = this,
            board = board,
            regions = divider()
                .divideRandomly(random)
                .divide(this, board)
        )
    }

    fun createEmpty(regionIds: List<Int>): Sudoku {
        val board = Board(sizeX, sizeY)
        return Sudoku(
            type = this,
            board = board,
            regions = divider()
                .divideByRegionId(regionIds)
                .divide(this, board)
        )
    }
}
