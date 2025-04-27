package io.github.ilikeyourhat.kudoku.type

import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.model.dividers.ColumnsDivider
import io.github.ilikeyourhat.kudoku.model.dividers.RowsDivider

object Square2x2 : SudokuType() {

    override val name = "square_2x2"
    override val sizeX = 2
    override val sizeY = 2
    override val maxValue = 2
    override val dividers = listOf(
        RowsDivider(),
        ColumnsDivider()
    )
}
