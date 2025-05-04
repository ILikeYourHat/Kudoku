package io.github.ilikeyourhat.kudoku.type

import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.model.dividers.BlocksDivider
import io.github.ilikeyourhat.kudoku.model.dividers.ColumnsDivider
import io.github.ilikeyourhat.kudoku.model.dividers.RowsDivider

object Classic6x6Vertical : SudokuType() {

    override val name = "classic_6x6_vertical"
    override val sizeX = 6
    override val sizeY = 6
    override val maxValue = 6
    override val dividers = listOf(
        RowsDivider(),
        ColumnsDivider(),
        BlocksDivider(2, 3)
    )
}
