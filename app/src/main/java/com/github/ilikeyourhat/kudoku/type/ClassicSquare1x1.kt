package com.github.ilikeyourhat.kudoku.type

import com.github.ilikeyourhat.kudoku.model.SudokuType
import com.github.ilikeyourhat.kudoku.model.dividers.RegionDivider

object ClassicSquare1x1 : SudokuType {

    override val sizeX = 1
    override val sizeY = 1
    override val regionSize = 1

    override fun divider(): RegionDivider {
        return RegionDivider()
            .allFields()
    }

    override fun template() = """
        _
    """.trimIndent()
}