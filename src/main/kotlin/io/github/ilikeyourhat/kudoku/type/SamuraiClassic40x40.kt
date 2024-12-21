package io.github.ilikeyourhat.kudoku.type

import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.model.dividers.RegionDivider

object SamuraiClassic40x40 : SudokuType {

    override val name = "samurai_classic_40x40"
    override val sizeX = 40
    override val sizeY = 40
    override val maxValue = 16

    override fun divider(): RegionDivider {
        return RegionDivider()
            .applySubSudoku(0, 0, Classic16x16)
            .applySubSudoku(24, 0, Classic16x16)
            .applySubSudoku(0, 24, Classic16x16)
            .applySubSudoku(12, 12, Classic16x16)
            .applySubSudoku(24, 24, Classic16x16)
    }
}
