package io.github.ilikeyourhat.kudoku.type

import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.model.dividers.RegionDivider

object Classic25x25 : SudokuType {

    override val name = "classic_25x25"
    override val sizeX = 25
    override val sizeY = 25
    override val maxValue = 25

    override fun divider(): RegionDivider {
        return RegionDivider()
            .divideByRows()
            .divideByColumns()
            .divideByBlocks(5)
    }
}
