package io.github.ilikeyourhat.kudoku.type

import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.model.dividers.SingleRegionDivider

object Square1x1 : SudokuType() {

    override val name = "square_1x1"
    override val sizeX = 1
    override val sizeY = 1
    override val maxValue = 1
    override val dividers = listOf(
        SingleRegionDivider()
    )
}
