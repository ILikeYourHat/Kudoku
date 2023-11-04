package com.github.ilikeyourhat.kudoku.type

import com.github.ilikeyourhat.kudoku.model.SudokuType
import com.github.ilikeyourhat.kudoku.model.dividers.RegionDivider

object Classic6x6Horizontal : SudokuType {

    override val name = "classic_6x6_horizontal"
    override val sizeX = 6
    override val sizeY = 6
    override val possibleValues = 6

    override fun template() = """
        _,_,_ _,_,_
        _,_,_ _,_,_

        _,_,_ _,_,_
        _,_,_ _,_,_
        
        _,_,_ _,_,_
        _,_,_ _,_,_
    """.trimIndent()

    override fun divider(): RegionDivider {
        return RegionDivider()
            .divideByRows()
            .divideByColumns()
            .divideByBlocks(3, 2)
    }
}
