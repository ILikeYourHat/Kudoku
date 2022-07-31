package pl.laskowski.marcin.model.type

import pl.laskowski.marcin.model.dividers.RegionDivider
import pl.laskowski.marcin.type.ISudokuVariant

object TripleBackslash15x15 : ISudokuVariant {

    override val sizeX = 15
    override val sizeY = 15
    override val regionSize = 9

    override fun template() = """
        _,_,_ _,_,_ _,_,_ #,#,# #,#,#
        _,_,_ _,_,_ _,_,_ #,#,# #,#,#
        _,_,_ _,_,_ _,_,_ #,#,# #,#,#
        
        _,_,_ _,_,_ _,_,_ _,_,_ #,#,#
        _,_,_ _,_,_ _,_,_ _,_,_ #,#,#
        _,_,_ _,_,_ _,_,_ _,_,_ #,#,#
        
        _,_,_ _,_,_ _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ _,_,_ _,_,_
        
        #,#,# _,_,_ _,_,_ _,_,_ _,_,_
        #,#,# _,_,_ _,_,_ _,_,_ _,_,_
        #,#,# _,_,_ _,_,_ _,_,_ _,_,_
        
        #,#,# #,#,# _,_,_ _,_,_ _,_,_
        #,#,# #,#,# _,_,_ _,_,_ _,_,_
        #,#,# #,#,# _,_,_ _,_,_ _,_,_
    """.trimIndent()

    override fun divider(): RegionDivider {
        return RegionDivider()
            .applySubSudoku(0, 0, ClassicSquare9x9)
            .applySubSudoku(3, 3, ClassicSquare9x9)
            .applySubSudoku(6, 6, ClassicSquare9x9)
    }
}