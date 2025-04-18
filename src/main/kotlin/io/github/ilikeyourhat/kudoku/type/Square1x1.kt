package io.github.ilikeyourhat.kudoku.type

import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.model.dividers.RegionDivider

object Square1x1 : SudokuType {

    override val name = "square_1x1"
    override val sizeX = 1
    override val sizeY = 1
    override val maxValue = 1

    override fun divider(): RegionDivider {
        return RegionDivider()
            .allCells()
    }
}
