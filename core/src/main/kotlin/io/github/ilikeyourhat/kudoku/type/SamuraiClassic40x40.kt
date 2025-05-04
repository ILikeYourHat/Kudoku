package io.github.ilikeyourhat.kudoku.type

import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.model.dividers.OtherTypeDivider

object SamuraiClassic40x40 : SudokuType() {

    override val name = "samurai_classic_40x40"
    override val sizeX = 40
    override val sizeY = 40
    override val maxValue = 16
    override val dividers = listOf(
        OtherTypeDivider(Classic16x16, startingPoint = 0 to 0),
        OtherTypeDivider(Classic16x16, startingPoint = 24 to 0),
        OtherTypeDivider(Classic16x16, startingPoint = 0 to 24),
        OtherTypeDivider(Classic16x16, startingPoint = 12 to 12),
        OtherTypeDivider(Classic16x16, startingPoint = 24 to 24)
    )
}
