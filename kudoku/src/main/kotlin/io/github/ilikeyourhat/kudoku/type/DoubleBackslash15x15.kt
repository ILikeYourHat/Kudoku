package io.github.ilikeyourhat.kudoku.type

import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.model.dividers.RegionDivider

object DoubleBackslash15x15 : SudokuType {

    override val name = "double_backslash_15x15"
    override val sizeX = 15
    override val sizeY = 15
    override val possibleValues = 9

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
            .applySubSudoku(0, 0, Classic9x9)
            .applySubSudoku(6, 6, Classic9x9)
    }
}
