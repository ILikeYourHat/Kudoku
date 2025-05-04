package io.github.ilikeyourhat.kudoku.model.dividers

import io.github.ilikeyourhat.kudoku.model.Board
import io.github.ilikeyourhat.kudoku.model.Region

class BlocksDivider(
    private val blockSizeX: Int,
    private val blockSizeY: Int
) : BoardDivider {

    constructor(blockSize: Int) : this(blockSize, blockSize)

    override fun divide(board: Board): List<Region> {
        require(board.sizeX() % blockSizeX == 0) {
            "Board size ${board.sizeX()}x${board.sizeY()} is not divisible by block sizeX $blockSizeX"
        }
        require(board.sizeY() % blockSizeY == 0) {
            "Board size ${board.sizeX()}x${board.sizeY()} is not divisible by block sizeY $blockSizeY"
        }

        val regions = mutableListOf<Region>()
        for (x in 0 until board.sizeX() step blockSizeX) {
            for (y in 0 until board.sizeY() step blockSizeY) {
                regions += board.region(x, y, x + blockSizeX - 1, y + blockSizeY - 1)
            }
        }
        return regions
    }
}
