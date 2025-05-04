package io.github.ilikeyourhat.kudoku.type

import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.model.dividers.OtherTypeDivider

object SamuraiClassic21x21 : SudokuType() {

    override val name = "samurai_classic_21x21"
    override val sizeX = 21
    override val sizeY = 21
    override val maxValue = 9
    override val dividers = listOf(
        OtherTypeDivider(Classic9x9, startingPoint = 0 to 0),
        OtherTypeDivider(Classic9x9, startingPoint = 12 to 0),
        OtherTypeDivider(Classic9x9, startingPoint = 0 to 12),
        OtherTypeDivider(Classic9x9, startingPoint = 6 to 6),
        OtherTypeDivider(Classic9x9, startingPoint = 12 to 12)
    )
}
