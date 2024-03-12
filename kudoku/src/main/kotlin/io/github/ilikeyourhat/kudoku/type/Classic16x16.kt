package io.github.ilikeyourhat.kudoku.type

import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.model.dividers.RegionDivider

object Classic16x16 : SudokuType {

    override val name = "classic_16x16"
    override val sizeX = 16
    override val sizeY = 16
    override val possibleValues = 16

    override fun template() = """
        _,_,_,_ _,_,_,_ _,_,_,_ _,_,_,_
        _,_,_,_ _,_,_,_ _,_,_,_ _,_,_,_
        _,_,_,_ _,_,_,_ _,_,_,_ _,_,_,_
        _,_,_,_ _,_,_,_ _,_,_,_ _,_,_,_
        
        _,_,_,_ _,_,_,_ _,_,_,_ _,_,_,_
        _,_,_,_ _,_,_,_ _,_,_,_ _,_,_,_
        _,_,_,_ _,_,_,_ _,_,_,_ _,_,_,_
        _,_,_,_ _,_,_,_ _,_,_,_ _,_,_,_
        
        _,_,_,_ _,_,_,_ _,_,_,_ _,_,_,_
        _,_,_,_ _,_,_,_ _,_,_,_ _,_,_,_
        _,_,_,_ _,_,_,_ _,_,_,_ _,_,_,_
        _,_,_,_ _,_,_,_ _,_,_,_ _,_,_,_
        
        _,_,_,_ _,_,_,_ _,_,_,_ _,_,_,_
        _,_,_,_ _,_,_,_ _,_,_,_ _,_,_,_
        _,_,_,_ _,_,_,_ _,_,_,_ _,_,_,_
        _,_,_,_ _,_,_,_ _,_,_,_ _,_,_,_
    """.trimIndent()

    override fun divider(): RegionDivider {
        return RegionDivider()
            .divideByRows()
            .divideByColumns()
            .divideByBlocks(4)
    }
}
