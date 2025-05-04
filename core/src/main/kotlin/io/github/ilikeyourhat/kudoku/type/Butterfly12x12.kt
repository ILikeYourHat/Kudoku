package io.github.ilikeyourhat.kudoku.type

import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.model.dividers.OtherTypeDivider

object Butterfly12x12 : SudokuType() {

    override val name = "butterfly_12x12"
    override val sizeX = 12
    override val sizeY = 12
    override val maxValue = 9
    override val dividers = listOf(
        OtherTypeDivider(Classic9x9, startingPoint = 0 to 0),
        OtherTypeDivider(Classic9x9, startingPoint = 3 to 0),
        OtherTypeDivider(Classic9x9, startingPoint = 0 to 3),
        OtherTypeDivider(Classic9x9, startingPoint = 3 to 3)
    )
}
