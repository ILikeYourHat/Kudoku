package io.github.ilikeyourhat.kudoku.type

import io.github.ilikeyourhat.kudoku.model.JigsawSudokuType
import io.github.ilikeyourhat.kudoku.model.dividers.ColumnsDivider
import io.github.ilikeyourhat.kudoku.model.dividers.RowsDivider

object Jigsaw7x7 : JigsawSudokuType() {

    override val name = "jigsaw_7x7"
    override val sizeX = 7
    override val sizeY = 7
    override val maxValue = 7
    override val dividers = listOf(
        RowsDivider(),
        ColumnsDivider()
    )
}
