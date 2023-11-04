package com.github.ilikeyourhat.kudoku.type

import com.github.ilikeyourhat.kudoku.model.SudokuType
import com.github.ilikeyourhat.kudoku.model.dividers.RegionDivider

object Classic9x9 : SudokuType {

    override val name = "classic_9x9"
    override val sizeX = 9
    override val sizeY = 9
    override val possibleValues = 9

    override fun template() = """
        _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_
        
        _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_
                
        _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_
    """.trimIndent()

    override fun divider(): RegionDivider {
        return RegionDivider()
            .divideByRows()
            .divideByColumns()
            .divideByBlocks(3)
    }
}
