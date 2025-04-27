package io.github.ilikeyourhat.kudoku.type

import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.model.dividers.OtherTypeDivider

object TripleSlash15x15 : SudokuType() {

    override val name = "triple_slash_15x15"
    override val sizeX = 15
    override val sizeY = 15
    override val maxValue = 9
    override val dividers = listOf(
        OtherTypeDivider(Classic9x9, startingPoint = 6 to 0),
        OtherTypeDivider(Classic9x9, startingPoint = 3 to 3),
        OtherTypeDivider(Classic9x9, startingPoint = 0 to 6)
    )
}
