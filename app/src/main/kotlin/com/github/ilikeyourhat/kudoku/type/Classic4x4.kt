package com.github.ilikeyourhat.kudoku.type

import com.github.ilikeyourhat.kudoku.model.SudokuType
import com.github.ilikeyourhat.kudoku.model.dividers.RegionDivider

object Classic4x4 : SudokuType {

    override val name = "classic_4x4"
    override val sizeX = 4
    override val sizeY = 4
    override val possibleValues = 4

    override fun template() = """
        _,_ _,_
        _,_ _,_

        _,_ _,_
        _,_ _,_
    """.trimIndent()

    override fun divider(): RegionDivider {
        return RegionDivider()
            .divideByRows()
            .divideByColumns()
            .divideByBlocks(2)
    }
}