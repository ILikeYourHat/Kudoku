package com.github.ilikeyourhat.kudoku.type

import com.github.ilikeyourhat.kudoku.model.SudokuType
import com.github.ilikeyourhat.kudoku.model.dividers.RegionDivider

object DoubleDiagonal9x9 : SudokuType {

    override val sizeX = 9
    override val sizeY = 9
    override val regionSize = 9

    override fun template() = Classic9x9.template()

    override fun divider(): RegionDivider {
        return Classic9x9.divider()
            .primaryDiagonal()
            .antiDiagonal()
    }
}