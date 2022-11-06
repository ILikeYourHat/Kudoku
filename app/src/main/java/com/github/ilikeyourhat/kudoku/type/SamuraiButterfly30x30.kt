package com.github.ilikeyourhat.kudoku.type

import com.github.ilikeyourhat.kudoku.model.SudokuType
import com.github.ilikeyourhat.kudoku.model.dividers.RegionDivider

object SamuraiButterfly30x30 : SudokuType {

    override val name = "samurai_butterfly_30x30"
    override val sizeX = 30
    override val sizeY = 30
    override val possibleValues = 9

    override fun template() = """
        _,_,_ _,_,_ _,_,_ _,_,_ #,#,# #,#,# _,_,_ _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ _,_,_ #,#,# #,#,# _,_,_ _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ _,_,_ #,#,# #,#,# _,_,_ _,_,_ _,_,_ _,_,_

        _,_,_ _,_,_ _,_,_ _,_,_ #,#,# #,#,# _,_,_ _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ _,_,_ #,#,# #,#,# _,_,_ _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ _,_,_ #,#,# #,#,# _,_,_ _,_,_ _,_,_ _,_,_

        _,_,_ _,_,_ _,_,_ _,_,_ #,#,# #,#,# _,_,_ _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ _,_,_ #,#,# #,#,# _,_,_ _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ _,_,_ #,#,# #,#,# _,_,_ _,_,_ _,_,_ _,_,_
        
        _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_
        
        #,#,# #,#,# #,#,# _,_,_ _,_,_ _,_,_ _,_,_ #,#,# #,#,# #,#,#
        #,#,# #,#,# #,#,# _,_,_ _,_,_ _,_,_ _,_,_ #,#,# #,#,# #,#,#
        #,#,# #,#,# #,#,# _,_,_ _,_,_ _,_,_ _,_,_ #,#,# #,#,# #,#,#
        
        #,#,# #,#,# #,#,# _,_,_ _,_,_ _,_,_ _,_,_ #,#,# #,#,# #,#,#
        #,#,# #,#,# #,#,# _,_,_ _,_,_ _,_,_ _,_,_ #,#,# #,#,# #,#,#
        #,#,# #,#,# #,#,# _,_,_ _,_,_ _,_,_ _,_,_ #,#,# #,#,# #,#,#
                
        _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_

        _,_,_ _,_,_ _,_,_ _,_,_ #,#,# #,#,# _,_,_ _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ _,_,_ #,#,# #,#,# _,_,_ _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ _,_,_ #,#,# #,#,# _,_,_ _,_,_ _,_,_ _,_,_
        
        _,_,_ _,_,_ _,_,_ _,_,_ #,#,# #,#,# _,_,_ _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ _,_,_ #,#,# #,#,# _,_,_ _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ _,_,_ #,#,# #,#,# _,_,_ _,_,_ _,_,_ _,_,_
        
        _,_,_ _,_,_ _,_,_ _,_,_ #,#,# #,#,# _,_,_ _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ _,_,_ #,#,# #,#,# _,_,_ _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ _,_,_ #,#,# #,#,# _,_,_ _,_,_ _,_,_ _,_,_
    """.trimIndent()

    override fun divider(): RegionDivider {
        return RegionDivider()
            .applySubSudoku(0, 0, Butterfly12x12)
            .applySubSudoku(18, 0, Butterfly12x12)
            .applySubSudoku(0, 18, Butterfly12x12)
            .applySubSudoku(9, 9, Butterfly12x12)
            .applySubSudoku(18, 18, Butterfly12x12)
    }
}