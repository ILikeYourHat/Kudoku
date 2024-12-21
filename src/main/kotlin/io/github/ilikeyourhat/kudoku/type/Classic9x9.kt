package io.github.ilikeyourhat.kudoku.type

import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.model.dividers.RegionDivider

object Classic9x9 : SudokuType {

    override val name = "classic_9x9"
    override val sizeX = 9
    override val sizeY = 9
    override val maxValue = 9

    override fun divider(): RegionDivider {
        return RegionDivider()
            .divideByRows()
            .divideByColumns()
            .divideByBlocks(3)
    }
}
