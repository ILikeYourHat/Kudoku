package io.github.ilikeyourhat.kudoku.type

import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.model.dividers.RegionDivider

object SamuraiClassic21x21 : SudokuType {

    override val name = "samurai_classic_21x21"
    override val sizeX = 21
    override val sizeY = 21
    override val maxValue = 9

    override fun divider(): RegionDivider {
        return RegionDivider()
            .applySubSudoku(0, 0, Classic9x9)
            .applySubSudoku(12, 0, Classic9x9)
            .applySubSudoku(0, 12, Classic9x9)
            .applySubSudoku(6, 6, Classic9x9)
            .applySubSudoku(12, 12, Classic9x9)
    }
}
