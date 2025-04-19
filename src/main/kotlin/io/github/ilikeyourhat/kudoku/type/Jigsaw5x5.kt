package io.github.ilikeyourhat.kudoku.type

import io.github.ilikeyourhat.kudoku.model.JigsawSudokuType
import io.github.ilikeyourhat.kudoku.model.dividers.RegionDivider

object Jigsaw5x5 : JigsawSudokuType {

    override val name = "jigsaw_5x5"
    override val sizeX = 5
    override val sizeY = 5
    override val maxValue = 5

    override fun divider(): RegionDivider {
        return RegionDivider()
            .divideByRows()
            .divideByColumns()
    }
}
