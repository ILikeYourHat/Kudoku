package com.github.ilikeyourhat.kudoku.type

import com.github.ilikeyourhat.kudoku.model.SudokuType
import com.github.ilikeyourhat.kudoku.model.dividers.RegionDivider

object Square2x2 : SudokuType {

    override val name = "square_2x2"
    override val sizeX = 2
    override val sizeY = 2
    override val possibleValues = 2

    override fun template() = """
        _,_
        _,_
    """.trimIndent()

    override fun divider(): RegionDivider {
        return RegionDivider()
            .divideByRows()
            .divideByColumns()
    }
}