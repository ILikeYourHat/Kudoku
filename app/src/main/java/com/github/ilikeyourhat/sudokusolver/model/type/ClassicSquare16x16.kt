package com.github.ilikeyourhat.sudokusolver.model.type

import com.github.ilikeyourhat.sudokusolver.model.dividers.RegionDivider

object ClassicSquare16x16 : SudokuType {

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