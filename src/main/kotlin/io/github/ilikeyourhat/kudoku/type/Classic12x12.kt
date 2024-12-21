package io.github.ilikeyourhat.kudoku.type

import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.model.dividers.RegionDivider

object Classic12x12 : SudokuType {

    override val name = "classic_12x12"
    override val sizeX = 12
    override val sizeY = 12
    override val maxValue = 12

    override fun template() = """
        _,_,_,_ _,_,_,_ _,_,_,_
        _,_,_,_ _,_,_,_ _,_,_,_
        _,_,_,_ _,_,_,_ _,_,_,_
        
        _,_,_,_ _,_,_,_ _,_,_,_
        _,_,_,_ _,_,_,_ _,_,_,_
        _,_,_,_ _,_,_,_ _,_,_,_
        
        _,_,_,_ _,_,_,_ _,_,_,_
        _,_,_,_ _,_,_,_ _,_,_,_
        _,_,_,_ _,_,_,_ _,_,_,_
        
        _,_,_,_ _,_,_,_ _,_,_,_
        _,_,_,_ _,_,_,_ _,_,_,_
        _,_,_,_ _,_,_,_ _,_,_,_
    """.trimIndent()

    override fun divider(): RegionDivider {
        return RegionDivider()
            .divideByRows()
            .divideByColumns()
            .divideByBlocks(4, 3)
    }
}
