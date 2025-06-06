package io.github.ilikeyourhat.kudoku.type

import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.model.dividers.BlocksDivider
import io.github.ilikeyourhat.kudoku.model.dividers.ColumnsDivider
import io.github.ilikeyourhat.kudoku.model.dividers.RowsDivider

object Classic4x4 : SudokuType() {

    override val name = "classic_4x4"
    override val sizeX = 4
    override val sizeY = 4
    override val maxValue = 4
    override val dividers = listOf(
        RowsDivider(),
        ColumnsDivider(),
        BlocksDivider(2)
    )
}
