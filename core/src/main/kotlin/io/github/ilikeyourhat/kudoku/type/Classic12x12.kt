package io.github.ilikeyourhat.kudoku.type

import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.model.dividers.BlocksDivider
import io.github.ilikeyourhat.kudoku.model.dividers.ColumnsDivider
import io.github.ilikeyourhat.kudoku.model.dividers.RowsDivider

object Classic12x12 : SudokuType() {

    override val name = "classic_12x12"
    override val sizeX = 12
    override val sizeY = 12
    override val maxValue = 12
    override val dividers = listOf(
        RowsDivider(),
        ColumnsDivider(),
        BlocksDivider(4, 3)
    )
}
