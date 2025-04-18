package io.github.ilikeyourhat.kudoku.type

import io.github.ilikeyourhat.kudoku.model.JigsawSudokuType
import io.github.ilikeyourhat.kudoku.model.dividers.RegionDivider

object Jigsaw9x9 : JigsawSudokuType {

    override val name = "jigsaw_9x9"
    override val sizeX = 9
    override val sizeY = 9
    override val maxValue = 9

    override fun divider(): RegionDivider {
        return RegionDivider()
            .divideByRows()
            .divideByColumns()
    }
}
