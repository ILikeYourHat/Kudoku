package io.github.ilikeyourhat.kudoku.type

import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.model.dividers.RegionDivider

object DoubleDiagonal9x9 : SudokuType {

    override val name = "double_diagonal_9x9"
    override val sizeX = 9
    override val sizeY = 9
    override val maxValue = 9

    override fun template() = Classic9x9.template()

    override fun divider(): RegionDivider {
        return Classic9x9.divider()
            .primaryDiagonal()
            .antiDiagonal()
    }
}
