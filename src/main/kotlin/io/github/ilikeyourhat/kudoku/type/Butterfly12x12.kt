package io.github.ilikeyourhat.kudoku.type

import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.model.dividers.RegionDivider

object Butterfly12x12 : SudokuType {

    override val name = "butterfly_12x12"
    override val sizeX = 12
    override val sizeY = 12
    override val maxValue = 9

    override fun divider(): RegionDivider {
        return RegionDivider()
            .applySubSudoku(0, 0, Classic9x9)
            .applySubSudoku(3, 0, Classic9x9)
            .applySubSudoku(0, 3, Classic9x9)
            .applySubSudoku(3, 3, Classic9x9)
    }
}
