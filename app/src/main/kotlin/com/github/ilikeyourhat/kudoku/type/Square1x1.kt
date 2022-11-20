package com.github.ilikeyourhat.kudoku.type

import com.github.ilikeyourhat.kudoku.model.SudokuType
import com.github.ilikeyourhat.kudoku.model.dividers.RegionDivider

object Square1x1 : SudokuType {

    override val name = "square_1x1"
    override val sizeX = 1
    override val sizeY = 1
    override val possibleValues = 1

    override fun divider(): RegionDivider {
        return RegionDivider()
            .allFields()
    }

    override fun template() = """
        _
    """.trimIndent()
}