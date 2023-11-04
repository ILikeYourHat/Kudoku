package com.github.ilikeyourhat.kudoku.type

import com.github.ilikeyourhat.kudoku.model.SudokuType
import com.github.ilikeyourhat.kudoku.model.dividers.RegionDivider

object DoubleDiagonal9x9 : SudokuType {

    override val name = "double_diagonal_9x9"
    override val sizeX = 9
    override val sizeY = 9
    override val possibleValues = 9

    override fun template() = Classic9x9.template()

    override fun divider(): RegionDivider {
        return Classic9x9.divider()
            .primaryDiagonal()
            .antiDiagonal()
    }
}
