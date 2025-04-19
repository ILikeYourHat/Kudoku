package io.github.ilikeyourhat.kudoku.type

import io.github.ilikeyourhat.kudoku.model.JigsawSudokuType
import io.github.ilikeyourhat.kudoku.model.dividers.RegionDivider

object Jigsaw6x6 : JigsawSudokuType {

    override val name = "jigsaw_6x6"
    override val sizeX = 6
    override val sizeY = 6
    override val maxValue = 6

    override fun divider(): RegionDivider {
        return RegionDivider()
            .divideByRows()
            .divideByColumns()
    }
}
