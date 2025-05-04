package io.github.ilikeyourhat.kudoku.type

import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.model.dividers.BlocksDivider
import io.github.ilikeyourhat.kudoku.model.dividers.ColumnsDivider
import io.github.ilikeyourhat.kudoku.model.dividers.RowsDivider

/**
 * WARNING: this sudoku type is very demanding for the computer resources. Generating and solving it would take
 * a considerable amount of time, measured in minutes or even hours. For this reason, it is not recommended to use it
 * unless you really know what you are doing.
 *
 * This class is not unit tested because of the above.
 *
 * "Your scientists were so preoccupied with whether or not they could, they didn't stop to think if they should."
 */
object Classic36x36 : SudokuType() {

    override val name = "classic_36x36"
    override val sizeX = 36
    override val sizeY = 36
    override val maxValue = 36
    override val dividers = listOf(
        RowsDivider(),
        ColumnsDivider(),
        BlocksDivider(6)
    )
}
