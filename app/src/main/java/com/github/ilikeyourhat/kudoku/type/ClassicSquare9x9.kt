package com.github.ilikeyourhat.kudoku.type

import com.github.ilikeyourhat.kudoku.model.SudokuType
import com.github.ilikeyourhat.kudoku.model.dividers.RegionDivider

object ClassicSquare9x9 : SudokuType {

    override val sizeX = 9
    override val sizeY = 9
    override val regionSize = 9

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