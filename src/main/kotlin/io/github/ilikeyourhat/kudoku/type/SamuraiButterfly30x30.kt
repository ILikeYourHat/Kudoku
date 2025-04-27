package io.github.ilikeyourhat.kudoku.type

import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.model.dividers.OtherTypeDivider

object SamuraiButterfly30x30 : SudokuType() {

    override val name = "samurai_butterfly_30x30"
    override val sizeX = 30
    override val sizeY = 30
    override val maxValue = 9
    override val dividers = listOf(
        OtherTypeDivider(Butterfly12x12, startingPoint = 0 to 0),
        OtherTypeDivider(Butterfly12x12, startingPoint = 18 to 0),
        OtherTypeDivider(Butterfly12x12, startingPoint = 0 to 18),
        OtherTypeDivider(Butterfly12x12, startingPoint = 9 to 9),
        OtherTypeDivider(Butterfly12x12, startingPoint = 18 to 18),
    )
}
