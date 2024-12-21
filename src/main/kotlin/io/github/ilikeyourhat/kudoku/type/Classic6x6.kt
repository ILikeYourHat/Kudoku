package io.github.ilikeyourhat.kudoku.type

import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.model.dividers.RegionDivider

object Classic6x6 : SudokuType {

    override val name = "classic_6x6"
    override val sizeX = 6
    override val sizeY = 6
    override val maxValue = 6

    override fun divider(): RegionDivider {
        return RegionDivider()
            .divideByRows()
            .divideByColumns()
            .divideByBlocks(3, 2)
    }
}
