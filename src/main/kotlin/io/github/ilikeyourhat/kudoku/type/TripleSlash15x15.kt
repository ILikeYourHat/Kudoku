package io.github.ilikeyourhat.kudoku.type

import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.model.dividers.RegionDivider

object TripleSlash15x15 : SudokuType {

    override val name = "triple_slash_15x15"
    override val sizeX = 15
    override val sizeY = 15
    override val maxValue = 9

    override fun template() = """
        #,#,# #,#,# _,_,_ _,_,_ _,_,_
        #,#,# #,#,# _,_,_ _,_,_ _,_,_
        #,#,# #,#,# _,_,_ _,_,_ _,_,_
        
        #,#,# _,_,_ _,_,_ _,_,_ _,_,_
        #,#,# _,_,_ _,_,_ _,_,_ _,_,_
        #,#,# _,_,_ _,_,_ _,_,_ _,_,_
        
        _,_,_ _,_,_ _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ _,_,_ _,_,_
        
        _,_,_ _,_,_ _,_,_ _,_,_ #,#,#
        _,_,_ _,_,_ _,_,_ _,_,_ #,#,#
        _,_,_ _,_,_ _,_,_ _,_,_ #,#,#
        
        _,_,_ _,_,_ _,_,_ #,#,# #,#,#
        _,_,_ _,_,_ _,_,_ #,#,# #,#,#
        _,_,_ _,_,_ _,_,_ #,#,# #,#,#
    """.trimIndent()

    override fun divider(): RegionDivider {
        return RegionDivider()
            .applySubSudoku(6, 0, Classic9x9)
            .applySubSudoku(3, 3, Classic9x9)
            .applySubSudoku(0, 6, Classic9x9)
    }
}
