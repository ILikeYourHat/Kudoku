package com.github.ilikeyourhat.sudokusolver.model.type

import com.github.ilikeyourhat.sudokusolver.model.SudokuType
import com.github.ilikeyourhat.sudokusolver.model.dividers.RegionDivider

object DoubleBackslash15x15 : SudokuType {

    override val sizeX = 15
    override val sizeY = 15
    override val regionSize = 9

    override fun template() = """
        _,_,_ _,_,_ _,_,_ #,#,# #,#,#
        _,_,_ _,_,_ _,_,_ #,#,# #,#,#
        _,_,_ _,_,_ _,_,_ #,#,# #,#,#
        
        _,_,_ _,_,_ _,_,_ #,#,# #,#,#
        _,_,_ _,_,_ _,_,_ #,#,# #,#,#
        _,_,_ _,_,_ _,_,_ #,#,# #,#,#
        
        _,_,_ _,_,_ _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ _,_,_ _,_,_
        
        #,#,# #,#,# _,_,_ _,_,_ _,_,_
        #,#,# #,#,# _,_,_ _,_,_ _,_,_
        #,#,# #,#,# _,_,_ _,_,_ _,_,_
        
        #,#,# #,#,# _,_,_ _,_,_ _,_,_
        #,#,# #,#,# _,_,_ _,_,_ _,_,_
        #,#,# #,#,# _,_,_ _,_,_ _,_,_
    """.trimIndent()

    override fun divider(): RegionDivider {
        return RegionDivider()
            .applySubSudoku(0, 0, ClassicSquare9x9)
            .applySubSudoku(6, 6, ClassicSquare9x9)
    }
}