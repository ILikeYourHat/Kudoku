package io.github.ilikeyourhat.kudoku.type

import io.github.ilikeyourhat.kudoku.model.JigsawSudokuType
import io.github.ilikeyourhat.kudoku.model.dividers.RegionDivider

object Jigsaw16x16 : JigsawSudokuType {

    override val name = "jigsaw_16x16"
    override val sizeX = 16
    override val sizeY = 16
    override val maxValue = 16

    override fun divider(): RegionDivider {
        return RegionDivider()
            .divideByRows()
            .divideByColumns()
    }
}
