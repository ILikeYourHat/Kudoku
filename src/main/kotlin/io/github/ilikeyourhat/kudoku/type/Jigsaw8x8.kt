package io.github.ilikeyourhat.kudoku.type

import io.github.ilikeyourhat.kudoku.model.JigsawSudokuType
import io.github.ilikeyourhat.kudoku.model.dividers.ColumnsDivider
import io.github.ilikeyourhat.kudoku.model.dividers.RowsDivider

object Jigsaw8x8 : JigsawSudokuType() {

    override val name = "jigsaw_8x8"
    override val sizeX = 8
    override val sizeY = 8
    override val maxValue = 8
    override val dividers = listOf(
        RowsDivider(),
        ColumnsDivider()
    )
}
