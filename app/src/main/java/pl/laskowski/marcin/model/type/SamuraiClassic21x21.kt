package pl.laskowski.marcin.model.type

import pl.laskowski.marcin.model.dividers.RegionDivider
import pl.laskowski.marcin.type.ISudokuVariant

object SamuraiClassic21x21 : ISudokuVariant {

    override val sizeX = 21
    override val sizeY = 21
    override val regionSize = 9

    override fun template() = """
        _,_,_ _,_,_ _,_,_ #,#,# _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ #,#,# _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ #,#,# _,_,_ _,_,_ _,_,_
        
        _,_,_ _,_,_ _,_,_ #,#,# _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ #,#,# _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ #,#,# _,_,_ _,_,_ _,_,_
        
        _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_
                
        #,#,# #,#,# _,_,_ _,_,_ _,_,_ #,#,# #,#,#
        #,#,# #,#,# _,_,_ _,_,_ _,_,_ #,#,# #,#,#
        #,#,# #,#,# _,_,_ _,_,_ _,_,_ #,#,# #,#,#
                
        _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_ _,_,_

        _,_,_ _,_,_ _,_,_ #,#,# _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ #,#,# _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ #,#,# _,_,_ _,_,_ _,_,_
        
        _,_,_ _,_,_ _,_,_ #,#,# _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ #,#,# _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_ #,#,# _,_,_ _,_,_ _,_,_
    """.trimIndent()

    override fun divider(): RegionDivider {
        return RegionDivider()
            .applySubSudoku(0, 0, ClassicSquare9x9)
            .applySubSudoku(12, 0, ClassicSquare9x9)
            .applySubSudoku(0, 12, ClassicSquare9x9)
            .applySubSudoku(6, 6, ClassicSquare9x9)
            .applySubSudoku(12, 12, ClassicSquare9x9)
    }
}