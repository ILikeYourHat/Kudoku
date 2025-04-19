package io.github.ilikeyourhat.kudoku.type

import io.github.ilikeyourhat.kudoku.model.JigsawSudokuType
import io.github.ilikeyourhat.kudoku.model.dividers.RegionDivider

object Jigsaw4x4 : JigsawSudokuType {

    override val name = "jigsaw_4x4"
    override val sizeX = 4
    override val sizeY = 4
    override val maxValue = 4

    override fun divider(): RegionDivider {
        return RegionDivider()
            .divideByRows()
            .divideByColumns()
    }
}
