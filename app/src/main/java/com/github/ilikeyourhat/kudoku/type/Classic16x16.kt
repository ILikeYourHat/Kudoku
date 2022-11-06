package com.github.ilikeyourhat.kudoku.type

import com.github.ilikeyourhat.kudoku.model.SudokuType
import com.github.ilikeyourhat.kudoku.model.dividers.RegionDivider

object Classic16x16 : SudokuType {

    override val sizeX = 16
    override val sizeY = 16
    override val regionSize = 16

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