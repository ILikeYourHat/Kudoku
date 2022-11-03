package com.github.ilikeyourhat.sudokusolver.type

import com.github.ilikeyourhat.sudokusolver.model.SudokuType
import com.github.ilikeyourhat.sudokusolver.model.dividers.RegionDivider

object Butterfly12x12 : SudokuType {

    override val sizeX = 12
    override val sizeY = 12
    override val regionSize = 9

    override fun divider(): RegionDivider {
        return RegionDivider()
            .applySubSudoku(0, 0, ClassicSquare9x9)
            .applySubSudoku(3, 0, ClassicSquare9x9)
            .applySubSudoku(0, 3, ClassicSquare9x9)
            .applySubSudoku(3, 3, ClassicSquare9x9)
    }

    override fun template() = """
        _,_,_ _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ _,_,_
        
        _,_,_ _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ _,_,_
        
        _,_,_ _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ _,_,_
        
        _,_,_ _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ _,_,_
    """.trimIndent()
}