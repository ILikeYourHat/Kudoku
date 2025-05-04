package io.github.ilikeyourhat.kudoku.type

import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.model.dividers.BlocksDivider
import io.github.ilikeyourhat.kudoku.model.dividers.ColumnsDivider
import io.github.ilikeyourhat.kudoku.model.dividers.RowsDivider

object Classic9x9 : SudokuType() {

    override val name = "classic_9x9"
    override val sizeX = 9
    override val sizeY = 9
    override val maxValue = 9
    override val dividers = listOf(
        RowsDivider(),
        ColumnsDivider(),
        BlocksDivider(3)
    )
}
