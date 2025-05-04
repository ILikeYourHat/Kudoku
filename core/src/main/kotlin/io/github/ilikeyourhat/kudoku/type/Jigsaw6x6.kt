package io.github.ilikeyourhat.kudoku.type

import io.github.ilikeyourhat.kudoku.model.JigsawSudokuType
import io.github.ilikeyourhat.kudoku.model.dividers.ColumnsDivider
import io.github.ilikeyourhat.kudoku.model.dividers.RowsDivider

object Jigsaw6x6 : JigsawSudokuType() {

    override val name = "jigsaw_6x6"
    override val sizeX = 6
    override val sizeY = 6
    override val maxValue = 6
    override val dividers = listOf(
        RowsDivider(),
        ColumnsDivider()
    )
}
